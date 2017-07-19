//wx1be0f2a1f512729a
var addresses ="https://api.tangseng.shop/shop/";
//jssdk签名(url=http://weixin.tangseng.shop/index.html)
var sign = addresses+"wx/sign";
//获取openId(code=4567890)
var oa = addresses+"wx/oa";
//下发验证码(mobile=18910701047)
var code = addresses+"user/code";
//用户注册(mobile=18910701047&code=1095&openId=1234567890)
var register = addresses+"user/register";
//用户登录(openId=1234567890)
var login = addresses+"user/login";
//加载商品列表(type=1)
var goodslist = addresses+"goods/list";
//加载商铺详情(id=1)
var shopinfo = addresses+"shop/info";
//加载商品详情(id=1)
var goodsinfo = addresses+"goods/info";
//地区列表(parentId=100000)
var arealist = addresses+"area/list";
//添加用户地址(uuid=100000)
var addresssave = addresses+"address/save";
//加载用户地址(uuid=100000)
var addressmy = addresses+"address/my";
//更新用户地址(uuid=100000)
var addressupdate = addresses+"address/update";
//获取用户详细地址
var addressdetail = addresses+"address/detail";
//申请加入
var shopcooperate = addresses+"shop/cooperate";
//合作商铺列表
var shopstars = addresses+"shop/stars";
//合作商铺详情(id=1)
var shopinfo = addresses+"shop/info";
//关注店铺(shopId=1&uuid=78a6771771f19b951017e100cc6cf1b6)
var shopsub = addresses+"shop/sub";
//取消关注店铺(shopId=1&uuid=78a6771771f19b951017e100cc6cf1b6)
var shopunsub = addresses+"shop/unsub";
//添加购物车(uuid=78a6771771f19b951017e100cc6cf1b6&goodsId=1)
var useraddGoods = addresses+"user/addGoods";
//我的购物车(uuid=78a6771771f19b951017e100cc6cf1b6)
var usercar = addresses+"user/car";
//删除购物车(uuid=78a6771771f19b951017e100cc6cf1b6&goodsId=1)
var userdropGoods = addresses+"user/dropGoods";
//用户下单(uuid=6d4722642c554a4aa75e417fafea6c76&itemIds=4&price=83&addressId=4&coin=10&point=0)
var ordernew = addresses+"order/new";
//用户流水记录查询(uuid=6d4722642c554a4aa75e417fafea6c76)
var usertradeLog = addresses+"user/tradeLog";
//批量保存商品(uuid=45678&items=1,1_2,3)
var useraddMultiGoods = addresses+"user/addMultiGoods";
//订单确认页面（uuid=6d4722642c554a4aa75e417fafea6c76&itemIds=4,5,6）
var orderconfirm = addresses+"order/confirm";
//支付结果查询（uuid=6d4722642c554a4aa75e417fafea6c76&orderId=1）
var orderinfo = addresses+"order/info";
//加载部落成员（uuid=6d4722642c554a4aa75e417fafea6c76）
var tribeinfo = addresses+"tribe/info";
//订单列表(uuid=6d4722642c554a4aa75e417fafea6c76&state=0)(0：待付款;1：待发货;2：待收货)
var orderlist = addresses+"order/list";
//更新部落名称（uuid=wthgfgg&name=新名字）
var triberename = addresses+"tribe/rename";
//待支付（uuid=dhhggg&orderId=123）
var orderpay = addresses+"order/pay";
//商品详情添加购物车（uuid=fghjkghjk&goodsId=2&amount=1）
var useraddGoods = addresses+"user/addGoods";
//分享配置
var wxshare = addresses+"wx/share";
///wx/share?action=23&uuid=6d4722642c554a4aa75e417fafea6c76&goodsId=1
//action：页面埋点（1.添加地址 2.地址列表 3.绑定手机号 4.待发货 5.待付款 6.待收货 7.店铺主页 8.订单确认 9.购物车 10.欢迎大礼包列表 11.合作商户详情 12.交易明细 13.家庭健康列表 14.支付结果状态页面 15.品质生活列表 16.品牌集群 17.企业家专访 18.酋长之路 19.商城介绍 20.商品详情 21.申请加入 22.体验中心 23.我的部落 24.我的族群 25.新品预售）
//uuid：用户UUID，action=23时必填
//goodsId：商品ID，action=20时必填
//shopId：店铺ID，action=7或action=11时必填
//删除订单(orderId=1&uuid=ghjklghjkl)
var orderdelete = addresses+"order/delete";