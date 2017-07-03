package com.gaoling.shop.order.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gaoling.shop.common.AppConstant;
import com.gaoling.shop.common.DataUtil;
import com.gaoling.shop.common.DateUtil;
import com.gaoling.shop.goods.pojo.Goods;
import com.gaoling.shop.goods.service.GoodsService;
import com.gaoling.shop.order.dao.OrderDao;
import com.gaoling.shop.order.pojo.Order;
import com.gaoling.shop.pay.pojo.PayRefundSummary;
import com.gaoling.shop.pay.pojo.UserTradeLog;
import com.gaoling.shop.pay.service.PayRefundSummaryService;
import com.gaoling.shop.pay.service.PayService;
import com.gaoling.shop.pay.service.UserTradeLogService;
import com.gaoling.shop.system.pojo.PayParam;
import com.gaoling.shop.system.pojo.Result;
import com.gaoling.shop.system.service.CommonService;
import com.gaoling.shop.tribe.pojo.Tribe;
import com.gaoling.shop.tribe.pojo.TribeMember;
import com.gaoling.shop.tribe.service.TribeMemberService;
import com.gaoling.shop.tribe.service.TribeService;
import com.gaoling.shop.user.pojo.Address;
import com.gaoling.shop.user.pojo.ShoppingCar;
import com.gaoling.shop.user.pojo.User;
import com.gaoling.shop.user.service.AddressService;
import com.gaoling.shop.user.service.ShoppingCarService;
import com.gaoling.shop.user.service.UserService;

@Service
public class OrderService extends CommonService{

	@Autowired
	private OrderDao orderDao;
	@Autowired
	private ShoppingCarService shoppingCarService;
	@Autowired
	private GoodsService goodsService;
	@Autowired
	private UserService userService;
	@Autowired
	private PayService payService;
	@Autowired
	private UserTradeLogService userTradeLogService;
	@Autowired
	private PayRefundSummaryService payRefundSummaryService;
	@Autowired
	private AddressService addressService;
	@Autowired
	private TribeService tribeService;
	@Autowired
	private TribeMemberService tribeMemberService;
	
	//查询订单详情
	public Result queryOrderDetail(String uuid,int orderId){
		//检查参数
		if(StringUtils.isEmpty(uuid)){
			return putResult(AppConstant.PARAM_IS_NULL);
		}
		//加载用户
		User user=userService.getUserByUUID(uuid);
		if(null==user){
			return putResult(AppConstant.USER_NOT_EXISTS);
		}
		Order order=getOrder(orderId, false);
		if(null==order||order.getUserId()!=user.getId()){
			return putResult(AppConstant.NOT_MYSELF_OPERATE);
		}
		return putResult(DataUtil.mapOf("order",order));
	}
	
	//查询订单列表
	public Result queryOrderList(String uuid,int state){
		//检查参数
		if(StringUtils.isEmpty(uuid)){
			return putResult(AppConstant.PARAM_IS_NULL);
		}
		//加载用户
		User user=userService.getUserByUUID(uuid);
		if(null==user){
			return putResult(AppConstant.USER_NOT_EXISTS);
		}
		List<Map<String,Object>> orders=orderDao.queryOrderList(DataUtil.mapOf("userId",user.getId(),"states",Arrays.asList(state)));
		//按照订单号分组订单
		Map<String,List<Map<String,Object>>> orderMap=orders.stream().collect(Collectors
				.groupingBy(r->r.get("tradeNo").toString(),Collectors.toList()));
		List<Map<Object,Object>> result=orderMap.entrySet().stream().map(k->{
			Map<String,Object> mainOrder=k.getValue().stream().filter(v->Integer.parseInt(v.get("refId").toString())==0).findFirst().get();
			List<Map<Object,Object>> goods=k.getValue().stream().map(v->DataUtil.mapOf("id",v.get("goodsId"),"name"
					,v.get("goodsName"),"headImg",AppConstant.OSS_CDN_SERVER+v.get("headImg"),"amount",v.get("amount"))).collect(Collectors.toList());
			float totalPrice=k.getValue().stream().map(v->{
				float price=Float.parseFloat(v.get("listPrice").toString());
				int point=Integer.parseInt(v.get("point").toString());
				int coin=Integer.parseInt(v.get("coin").toString());
				return price-point/getInteger("point_to_cash_rate")-coin/getInteger("coin_to_cash_rate");
			}).reduce((a,b)->a+b).get();
			return DataUtil.mapOf("orderId",mainOrder.get("orderId"),"createTime",mainOrder.get("createTime")
					,"tradeNo",k.getKey(),"totalPrice",totalPrice,"goods",goods,"state",mainOrder.get("state")
					,"point",mainOrder.get("point"),"coin",mainOrder.get("coin"),"payWay",mainOrder.get("payWay"));
		}).sorted((a,b)->b.get("createTime").toString().compareTo(a.get("createTime").toString())).collect(Collectors.toList());
		return putResult(DataUtil.mapOf("orders",result));
	}
	
	//用户下单
	@Transactional
	public Result newOrder(String itemIds,String uuid,Order param,String ip)throws Exception{
		//检查参数
		if(StringUtils.isEmpty(itemIds)||StringUtils.isEmpty(uuid)){
			return putResult(AppConstant.PARAM_IS_NULL);
		}
		//加载用户
		User user=userService.getUserByUUID(uuid);
		if(null==user){
			return putResult(AppConstant.USER_NOT_EXISTS);
		}
		//查询购物车内容
		List<Integer> ids=Arrays.asList(itemIds.split(",")).stream().map(i->Integer.parseInt(i)).collect(Collectors.toList());
		List<ShoppingCar> cars=shoppingCarService.queryShoppingCars(DataUtil.mapOf("goodsIds",ids,"userId",user.getId()));
		if(cars.size()<=0){
			return putResult(AppConstant.GOODS_NOT_EXISTS);
		}
		//检查部落番号
		if(param.getTribeId()>0){
			Tribe t=tribeService.getTribe(param.getTribeId());
			if(null==t){
				return putResult(AppConstant.TRIBE_NOT_EXISTS);
			}
		}
		//拼装订单信息
		List<Order> orders=new ArrayList<Order>();
		Order order=null;
		Goods goods=null;
		String prefix=getString("goods_trade_no_prefix");
		String body=getString("goods_pay_body");
		for(ShoppingCar car:cars){
			//检查商品是否可购买
			goods=goodsService.getGoods(car.getGoodsId());
			if(null==goods||goods.getState()!=Goods.STATE_TYPE_ENUM.PASSED.getState()){
				return putResult(AppConstant.GOODS_NOT_EXISTS);
			}
			//判断用户是否可添加该商品
			int buyAmount=goodsService.getEnableBuyAmount(user.getId(), goods.getId());
			if(buyAmount>=0&&car.getAmount()>buyAmount){
				return putResult(AppConstant.OUT_OF_BOUNDS,new Object[]{goodsService.getTotalBuyAmount(user.getId(), goods.getId())});
			}
			order=new Order();
			order.setAddressId(param.getAddressId());
			order.setAmount(car.getAmount());
			order.setCreateTime(DateUtil.nowDate());
			order.setGoodsId(car.getGoodsId());
			order.setTribeId(param.getTribeId());
			order.setListPrice(goods.getPrice()*car.getAmount());
			order.setPayWay(AppConstant.WEIXIN_PAY_WAY);
			order.setPrice(goods.getPointEnable()>0||goods.getCoinEnable()>0
					?Math.round(goods.getPrice()*goods.getCashDiscount())*order.getAmount()
					:goods.getPrice()*order.getAmount());
			if(orders.size()<=0){
				order.setCoin(param.getCoin());
				order.setPoint(param.getPoint());
			}
			order.setTradeNo(prefix+DateUtil.getCurrentTime("yyyyMMddHHmmssSSS")+DataUtil.createNums(3));
			order.setUserId(user.getId());
			order.setState(Order.STATE_TYPE_ENUM.NOPAY.getState());
			orders.add(order);
		}
		//检查应付金额是否正确
		float totalMiniPrice=orders.stream().map(t->t.getPrice()).reduce((a,b)->a+b).get();
		float totalPrice=orders.stream().map(t->t.getListPrice()).reduce((a,b)->a+b).get();
		int coinCash=param.getCoin()/getInteger("coin_to_cash_rate");
		int pointCash=param.getPoint()/getInteger("point_to_cash_rate");
		//检查账户是否充足
		if(user.getCoin()<param.getCoin()||user.getPoint()<param.getPoint()){
			return putResult(AppConstant.ACCOUNT_BALANCE_INADEQUATE);
		}
		if(param.getPrice()<totalMiniPrice||(param.getPrice()+coinCash+pointCash!=totalPrice)){
			return putResult(AppConstant.PAY_PRICE_INCORRECT);
		}
		//预生成支付信息
		Map<String,Object> payInfo=null;
		if(param.getPrice()>0){
			PayParam pay=new PayParam();
			pay.setAmount(Math.round(param.getPrice()));
			pay.setBody(body);
			pay.setIp(ip);
			pay.setNonceStr(DataUtil.createLetters(32));
			pay.setOpenId(user.getOpenId());
			pay.setOperator(AppConstant.USERMP_MCH_ID);
			pay.setPayWay(AppConstant.WEIXIN_PAY_WAY);
			pay.setStartTime(DateUtil.getCurrentTime("yyyyMMddHHmmss"));
			pay.setTimestamp(String.valueOf(new Date().getTime()/1000));
			pay.setTradeNo(orders.get(0).getTradeNo());
			pay.setTradeType(AppConstant.WEIXIN_TRADE_TYPE_JSAPI);
			payInfo=payService.operateUserPayRequest(pay);
		}else{
			orders.stream().forEach(o->o.setState(Order.STATE_TYPE_ENUM.NOSEND.getState()));
		}
		//保存订单信息
		Order mainOrder=orders.get(0);
		addOrder(mainOrder);
		orders.stream().forEach(o->{
			if(o.getId()<=0){
				o.setRefId(mainOrder.getId());
				addOrder(o);
			}
		});
		if(mainOrder.getState()==Order.STATE_TYPE_ENUM.NOSEND.getState()){
			goodsPaySuccess(mainOrder.getId(),"",param.getPrice());
		}
		return null!=payInfo?putResult(DataUtil.mapOf("payInfo",payInfo,"orderId",mainOrder.getId()))
				:putResult(DataUtil.mapOf("orderId",mainOrder.getId()));
	}
	
	@Transactional
	public Result orderPay(String uuid,int orderId,String ip)throws Exception{
		//检查参数
		if(orderId<=0||StringUtils.isEmpty(uuid)){
			return putResult(AppConstant.PARAM_IS_NULL);
		}
		//加载用户
		User user=userService.getUserByUUID(uuid);
		if(null==user){
			return putResult(AppConstant.USER_NOT_EXISTS);
		}
		List<Order> orders=queryOrders(DataUtil.mapOf("allOrders",true,"mainOrderId",orderId));
		Order mainOrder=orders.size()>0?orders.stream().filter(o->o.getRefId()==0).findFirst().get():null;
		if(null==mainOrder||mainOrder.getUserId()!=user.getId()){
			return putResult(AppConstant.NOT_MYSELF_OPERATE);
		}
		//判断用户是否可添加该商品
		for(Order order:orders){
			int buyAmount=goodsService.getEnableBuyAmount(user.getId(), order.getGoodsId());
			if(buyAmount>=0&&order.getAmount()>buyAmount){
				return putResult(AppConstant.OUT_OF_BOUNDS,new Object[]{goodsService.getTotalBuyAmount(user.getId(), order.getGoodsId())});
			}
		}
		//计算应付金额
		float totalListPrice=orders.stream().map(o->o.getListPrice()).reduce((a,b)->a+b).get();
		float totalPrice=totalListPrice-mainOrder.getCoin()/getInteger("coin_to_cash_rate")-mainOrder.getPoint()/getInteger("point_to_cash_rate");
		//预生成支付信息
		PayParam pay=new PayParam();
		pay.setAmount(Math.round(totalPrice));
		pay.setBody(getString("goods_pay_body"));
		pay.setIp(ip);
		pay.setNonceStr(DataUtil.createLetters(32));
		pay.setOpenId(user.getOpenId());
		pay.setOperator(AppConstant.USERMP_MCH_ID);
		pay.setPayWay(AppConstant.WEIXIN_PAY_WAY);
		pay.setStartTime(DateUtil.getCurrentTime("yyyyMMddHHmmss"));
		pay.setTimestamp(String.valueOf(new Date().getTime()/1000));
		pay.setTradeNo(orders.get(0).getTradeNo());
		pay.setTradeType(AppConstant.WEIXIN_TRADE_TYPE_JSAPI);
		Map<String,Object> payInfo=payService.operateUserPayRequest(pay);
		return putResult(DataUtil.mapOf("payInfo",payInfo,"orderId",mainOrder.getId()));
	}
	
	//订单确认页数据加载
	public Result confirmOrder(String itemIds,String uuid){
		//检查参数
		if(StringUtils.isEmpty(itemIds)||StringUtils.isEmpty(uuid)){
			return putResult(AppConstant.PARAM_IS_NULL);
		}
		//加载用户
		User user=userService.getUserByUUID(uuid);
		if(null==user){
			return putResult(AppConstant.USER_NOT_EXISTS);
		}
		//查询购物车内容
		List<Integer> goodsIds=Arrays.asList(itemIds.split(",")).stream().map(i->Integer.parseInt(i)).collect(Collectors.toList());
		List<Map<String,Object>> goods=shoppingCarService.queryMyShoppingCar(user.getId(),goodsIds);
		if(goods.size()<=0){
			return putResult(AppConstant.GOODS_NOT_EXISTS);
		}
		for(Map<String,Object> g:goods){
			StringBuffer urls = new StringBuffer();
			if (!DataUtil.isEmpty(g.get("headImg"))) {
				for (String images : g.get("headImg").toString().split(",")) {
					urls.append(urls.length() > 0 ? "," : "");
					urls.append(AppConstant.OSS_CDN_SERVER + images);
				}
			}
			g.put("headImg", urls.toString().length() > 0 ? urls.toString() : g.get("headImg").toString());
		}
		//按照店铺分组
		Map<String,List<Map<String,Object>>> shopMap=goods.stream().collect(Collectors
				.groupingBy(r->r.get("shopName")+"_"+r.get("shopId"),Collectors.toList()));
		List<Map<Object,Object>> car=shopMap.entrySet().stream().map(k->{
			float totalPrice=k.getValue().stream().map(v->Float.parseFloat(v.get("price").toString())
					*Float.parseFloat(v.get("amount").toString())).reduce((a,b)->a+b).get();
			float totalPoint=k.getValue().stream().map(v->{
				int typeId=Integer.parseInt(v.get("typeId").toString());
				float price=0;
				//欢迎大礼包不赠送部落分
				if(typeId!=Goods.GOODS_TYPE_ENUM.WELCOME.getType()){
					price=Float.parseFloat(v.get("price").toString());
				}
				return price*Float.parseFloat(v.get("amount").toString());
			}).reduce((a,b)->a+b).get();
			return DataUtil.mapOf("shopName",k.getKey().split("_")[0],"shopId",k.getKey().split("_")[1]
					,"goods",k.getValue(),"totalPrice",totalPrice,"totalPoint",totalPoint);
		}).collect(Collectors.toList());
		//获取用户的默认地址
		Address address=addressService.getDefaultAddresses(user.getId());
		//拼装其他参数
		float totalMiniPrice=goods.stream().map(g->{
			float cashDiscount=Float.parseFloat(g.get("cashDiscount").toString());
			float price=Float.parseFloat(g.get("price").toString());
			int amount=Integer.parseInt(g.get("amount").toString());
			int coinEnable=Integer.parseInt(g.get("coinEnable").toString());
			int pointEnable=Integer.parseInt(g.get("pointEnable").toString());
			return coinEnable>0||pointEnable>0?Math.round(cashDiscount*price)*amount:price*amount;
		}).reduce((a,b)->a+b).get();
		int coinRate=goods.stream().filter(g->Integer.parseInt(g.get("coinEnable").toString())>0).findAny().isPresent()
				?getInteger("coin_to_cash_rate"):0;
		int pointRate=goods.stream().filter(g->Integer.parseInt(g.get("pointEnable").toString())>0).findAny().isPresent()
				?getInteger("point_to_cash_rate"):0;
		Map<String,Object> result=DataUtil.mapOf("goods",car,"address",address,"totalMiniPrice",totalMiniPrice
			,"point",user.getPoint(),"coin",user.getCoin(),"pointRate",pointRate,"coinRate",coinRate);
		return putResult(result);
	}
	
	//处理支付回调请求
	@Transactional
	public void operatePayNotify(String tradeNo,String outTradeNo,float amount)throws Exception{
		List<Order> orders=queryOrders(DataUtil.mapOf("tradeNo",tradeNo));
		Order mainOrder=orders.stream().filter(o->o.getRefId()<=0).findFirst().get();
		goodsPaySuccess(mainOrder.getId(), outTradeNo, amount);
	}
	
	//商品订单支付成功
	@Transactional
	public boolean goodsPaySuccess(int orderId,String outTradeNo,float amount)throws Exception{
		List<Order> orders=queryOrders(DataUtil.mapOf("allOrders",true,"mainOrderId",orderId));
		//获取主订单
		Order mainOrder=orders.stream().filter(o->o.getRefId()<=0).findFirst().get();
		//对比现金支付部分是否正确
		float totalListPrice=orders.stream().map(o->o.getListPrice()).reduce((a,b)->a+b).get();
		float totalPrice=totalListPrice-mainOrder.getPoint()/getInteger("point_to_cash_rate")-mainOrder.getCoin()/getInteger("coin_to_cash_rate");
		//检查订单状态是否是待支付
		if(mainOrder.getState()!=Order.STATE_TYPE_ENUM.NOPAY.getState()){
			Logger.getLogger("file").info("goodsPaySuccess | 订单状态不是待支付状态 | orderId="+orderId+",outTradeNo="
					+outTradeNo+",state="+mainOrder.getState());
			return false;
		}
		//检查订单金额是否正确
		if(totalPrice!=amount){
			Logger.getLogger("file").info("goodsPaySuccess | 订单应付金额不正确 | orderId="+orderId+",outTradeNo="
					+outTradeNo+",state="+mainOrder.getState());
			return false;
		}
		//扣除部落分、部落币
		userService.operateUserAccount(mainOrder.getUserId(), -mainOrder.getPoint(), -mainOrder.getCoin());
		//记录流水
		UserTradeLog log=new UserTradeLog();
		log.setAmount(-totalListPrice);
		log.setCash(-totalPrice);
		log.setCoin(-mainOrder.getCoin());
		log.setCreateTime(DateUtil.nowDate());
		log.setPayWay(mainOrder.getPayWay());
		log.setPoint(-mainOrder.getPoint());
		log.setRemark("");
		log.setTradeId(mainOrder.getId());
		log.setTradeNo(mainOrder.getTradeNo().replaceAll(getString("goods_trade_no_prefix"), ""));
		log.setTradeType(PayRefundSummary.TRADE_TYPE_ENUM.GOODSPAY.getState());
		log.setUserId(mainOrder.getUserId());
		userTradeLogService.addUserTradeLog(log);
		//记录支付退款汇总信息
		PayRefundSummary summary=new PayRefundSummary();
		summary.setAmount(Math.abs(log.getAmount()));
		summary.setCash(Math.abs(log.getCash()));
		summary.setCoin(Math.abs(log.getCoin()));
		summary.setPoint(Math.abs(log.getPoint()));
		summary.setTradeType(log.getTradeType());
		summary.setTradeId(log.getTradeId());
		payRefundSummaryService.addPayRefundSummary(summary);
		//更新订单状态
		for(Order order:orders){
			order.setOutTradeNo(outTradeNo);
			order.setState(Order.STATE_TYPE_ENUM.NOSEND.getState());
			updateOrder(order);
		}
		//赠送部落币
		List<Integer> ids=orders.stream().map(o->o.getGoodsId()).collect(Collectors.toList());
		List<Goods> goods=goodsService.queryGoods(DataUtil.mapOf("ids",ids));
		List<Goods> welcomes=goods.stream().filter(o->o.getTypeId()==Goods.GOODS_TYPE_ENUM.WELCOME.getType()).collect(Collectors.toList());
		List<Order> wos=orders.stream().filter(o->{
			Optional<Goods> ip=welcomes.stream().filter(g->g.getId()==o.getGoodsId()).findAny();
			return ip.isPresent();
		}).collect(Collectors.toList());
		if(welcomes.size()>0){
			giveInviteReward(mainOrder.getUserId(), mainOrder.getTribeId());
		}
		//计算赠送的部落币,去除掉购买欢迎大礼包所花的现金金额
		amount-=wos.size()>0?wos.stream().map(o->o.getListPrice()).reduce((a,b)->a+b).get():0;
		if(amount>0){
			userService.operateUserAccount(mainOrder.getUserId(), Math.round(amount), 0);
			//记录流水
			log=new UserTradeLog();
			log.setAmount(amount);
			log.setCash(0);
			log.setCoin(0);
			log.setCreateTime(DateUtil.nowDate());
			log.setPayWay(AppConstant.POINT_PAY_WAY);
			log.setPoint(Math.round(amount));
			log.setRemark("");
			log.setTradeId(0);
			log.setTradeNo(DateUtil.getCurrentTime("yyyyMMddHHmmssSSS")+DataUtil.createNums(3));
			log.setTradeType(PayRefundSummary.TRADE_TYPE_ENUM.REWARD.getState());
			log.setUserId(mainOrder.getUserId());
			userTradeLogService.addUserTradeLog(log);
		}
		//将商品从购物车内清除
		List<Integer> goodsIds=orders.stream().map(o->o.getGoodsId()).collect(Collectors.toList());
		shoppingCarService.removeGoodsFromShoppingCar(mainOrder.getUserId(), goodsIds);
		return true;
	}
	
	//查找上级推荐关系
	public List<Integer> findInviter(int userId,int tribeId){
		List<Integer> users=new ArrayList<Integer>();
		//查找推荐人
		if(tribeId>0){
			Tribe t=tribeService.getTribe(tribeId);
			TribeMember member=tribeMemberService.getRecommender(t.getUserId());
			if(null!=member){
				users=findInviter(t.getUserId(),member.getTribeId());
			}
			users.add(t.getUserId());
		}
		return users;
	}
	
	//删除订单
	public Result deleteOrder(String uuid,int orderId){
		//检查参数
		if(StringUtils.isEmpty(uuid)||orderId<=0){
			return putResult(AppConstant.PARAM_IS_NULL);
		}
		//加载用户
		User user=userService.getUserByUUID(uuid);
		if(null==user){
			return putResult(AppConstant.USER_NOT_EXISTS);
		}
		Order order=getOrder(orderId, false);
		//判断是否本人操作
		if(null==order||order.getUserId()!=user.getId()){
			return putResult(AppConstant.NOT_MYSELF_OPERATE);
		}
		//只有待处理订单才可以删除
		if(order.getState()!=Order.STATE_TYPE_ENUM.NOPAY.getState()){
			return putResult(AppConstant.ORDER_STATE_INCORRECT);
		}
		order.setState(Order.STATE_TYPE_ENUM.DELETED.getState());
		updateOrder(order);
		return putResult();
	}
	
	//给予相关推荐人部落币奖励
	@Transactional
	public boolean giveInviteReward(int userId,int tribeId)throws Exception{
		//检查参数是否正确
		if(userId<=0){
			Logger.getLogger("file").info("giveInviteReward | userId="+userId+",tribeId="+tribeId+" 参数错误");
			return false;
		}
		//检查是否已经发放过奖励
		if(null!=tribeService.getTribeByUserId(userId)){
			Logger.getLogger("file").info("giveInviteReward | 用户已已领取过礼包,放弃操作");
			return false;
		}
		//创建自己的部落
		Tribe tribe=new Tribe();
		tribe.setCoin(0);
		tribe.setCreateTime(DateUtil.nowDate());
		tribe.setHeadImg("");
		tribe.setLevel(0);
		tribe.setNickname(getString("tribe_default_name"));
		tribe.setPoint(0);
		tribe.setState(Tribe.STATE_TYPE_ENUM.ACTIVATED.getState());
		tribe.setUserId(userId);
		tribeService.addTribe(tribe);
		//只有用户填写了部落ID时,才发放部落币奖励
		if(tribeId>0){
			//发放部落币奖励
			List<Integer> users=findInviter(userId, tribeId);
			users.add(userId);
			int level=getInteger("invite_reward_level");
			int coin=getInteger("invite_reward_coin_amount");
			for(int i=0;i<level;i++){
				if(i>users.size()-1){
					break;
				}
				userService.operateUserAccount(users.get(users.size()-1-i), 0, coin);
				//记录流水
				UserTradeLog log=new UserTradeLog();
				log.setAmount(coin);
				log.setCash(0);
				log.setCoin(coin);
				log.setCreateTime(DateUtil.nowDate());
				log.setPayWay(AppConstant.COIN_PAY_WAY);
				log.setPoint(0);
				log.setRemark("");
				log.setTradeId(0);
				log.setTradeNo(DateUtil.getCurrentTime("yyyyMMddHHmmssSSS")+DataUtil.createNums(3));
				log.setTradeType(users.size()>1?PayRefundSummary.TRADE_TYPE_ENUM.INVITE.getState()
						:PayRefundSummary.TRADE_TYPE_ENUM.REWARD.getState());
				log.setUserId(userId);
				userTradeLogService.addUserTradeLog(log);
			}
			//加入部落
			TribeMember member=new TribeMember();
			member.setUserId(userId);
			member.setTribeId(tribeId);
			member.setState(TribeMember.STATE_TYPE_ENUM.JOINED.getState());
			member.setCreateTime(DateUtil.nowDate());
			member.setJoinTime(DateUtil.nowDate());
			tribeMemberService.addTribeMember(member);
			//增加推荐人部落的部落币
			tribeService.operateTribeAccount(tribeId, 0, coin);
		}
		return true;
	}

	//查询订单信息
	public Order getOrder(int id,boolean lock){
		return orderDao.getOrder(id, lock);
	}
	
	//查询订单信息
	public List<Order> queryOrders(Map<Object,Object> param){
		return orderDao.queryOrders(param);
	}
	
	//保存订单信息
	public void addOrder(Order order){
		orderDao.addOrder(order);
	}
	
	//修改订单信息
	public void updateOrder(Order order){
		orderDao.updateOrder(order);
	}
	
}
