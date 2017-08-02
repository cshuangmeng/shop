package com.gaoling.webshop.common;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.danga.MemCached.MemCachedClient;
import com.danga.MemCached.SockIOPool;

public class MemcachedUtil {

	private MemCachedClient client;
	private static MemcachedUtil memcached;

	private MemcachedUtil() {
		client = new MemCachedClient();
		String[] addr = { AppConstant.MEMCACHED_ADDR };
		Integer[] weights = { 3 };
		SockIOPool pool = SockIOPool.getInstance();
		pool.setServers(addr);
		pool.setWeights(weights);
		pool.setInitConn(5);
		pool.setMinConn(5);
		pool.setMaxConn(200);
		pool.setMaxIdle(1000 * 30 * 30);
		pool.setMaintSleep(30);
		pool.setNagle(false);
		pool.setSocketTO(30);
		pool.initialize();
		Logger.getLogger("file").info(DateUtil.getCurrentTime()+" memcached pool inited!");
	}

	public synchronized static MemcachedUtil getInstance() {
		if (null == memcached) {
			memcached = new MemcachedUtil();
		}
		return memcached;
	}

	// 存储
	public void setData(String key, Object value) {
		try {
			client.set(key, value);
			Logger.getLogger("file").info(DateUtil.getCurrentTime()+" "+key+" seted!");
		} catch (Exception e) {
			Logger.getLogger("file").info(DateUtil.getCurrentTime()+" "+key+" set failure! "+e.getMessage());
			e.printStackTrace();
		}
	}
	
	// 存储
	public void setData(String key, Object value, int mins) {
		try {
			boolean result=client.set(key, value, mins);
			System.out.println(result);
			Logger.getLogger("file").info(DateUtil.getCurrentTime()+" "+key+" seted!mins is "+mins);
		} catch (Exception e) {
			Logger.getLogger("file").info(DateUtil.getCurrentTime()+" "+key+" set failure! "+e.getMessage());
			e.printStackTrace();
		}
	}
	
	// 获取
	public Object getData(String key) {
		try {
			Object obj=StringUtils.isNotEmpty(key)?client.get(key):null;
			Logger.getLogger("file").info(DateUtil.getCurrentTime()+" "+key+" get "+obj);
			return obj;
		} catch (Exception e) {
			Logger.getLogger("file").info(DateUtil.getCurrentTime()+" "+key+" get failure! "+e.getMessage());
			e.printStackTrace();
		}
		return null;
	}
	
	// 获取
	public String getData(String key,String def) {
		try {
			Object obj=StringUtils.isNotEmpty(key)?client.get(key):null;
			Logger.getLogger("file").info(DateUtil.getCurrentTime()+" "+key+" get "+obj);
			return null!=obj?obj.toString():def;
		} catch (Exception e) {
			Logger.getLogger("file").info(DateUtil.getCurrentTime()+" "+key+" get failure! "+e.getMessage());
			e.printStackTrace();
		}
		return def;
	}
	
	public static void main(String[] args) {
		//MemcachedUtil.getInstance().setData("pc_189107010471", "0000", 10);
		MemcachedUtil.getInstance().getData("pc_189107010471");
	}

}
