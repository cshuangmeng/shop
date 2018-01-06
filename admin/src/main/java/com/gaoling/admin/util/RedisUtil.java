package com.gaoling.admin.util;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Protocol;

public class RedisUtil {
	
	private static JedisPool jedisPool = null;
	// set方式,当key不存在时才set
	private static final String NX = "NX";
	// set方式,当key存在时才set
	private static final String XX = "XX";
	// 过期时间单位,秒
	private static final String EX = "EX";
	// 过期时间单位,毫秒
	private static final String PX = "PX";

	// 初始化Redis连接池
	static {
		try {
			String host = PropertiesUtil.getProperty("redis.host");
			Integer port = Integer.parseInt(PropertiesUtil.getProperty("redis.port"));
			String password = PropertiesUtil.getProperty("redis.password");
			JedisPoolConfig config = new JedisPoolConfig();
			// 连接耗尽时是否阻塞, false报异常,ture阻塞直到超时, 默认true
			config.setBlockWhenExhausted(true);
			// 设置的逐出策略类名, 默认DefaultEvictionPolicy(当连接超过最大空闲时间,或连接数超过最大空闲连接数)
			config.setEvictionPolicyClassName("org.apache.commons.pool2.impl.DefaultEvictionPolicy");
			// 是否启用pool的jmx管理功能, 默认true
			config.setJmxEnabled(true);
			// 最大空闲连接数, 默认8个 控制一个pool最多有多少个状态为idle(空闲的)的jedis实例。
			config.setMaxIdle(8);
			// 最大连接数, 默认8个
			config.setMaxTotal(200);
			// 表示当borrow(引入)一个jedis实例时，最大的等待时间，如果超过等待时间，则直接抛出JedisConnectionException；
			config.setMaxWaitMillis(1000 * 100);
			// 在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；
			config.setTestOnBorrow(true);
			jedisPool = new JedisPool(config, host, port, Protocol.DEFAULT_TIMEOUT, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 获取Jedis实例
	public synchronized static Jedis getJedis() {
		try {
			if (jedisPool != null) {
				Jedis resource = jedisPool.getResource();
				return resource;
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	// 加锁
	public static void lock(String key, String value) {
		long lock = 0;
		Jedis j = getJedis();
		if (null != j) {
			while (lock != 1) {
				lock = j.setnx(key, value);
			}
			j.expire(key, 5);
			close(j);
		}
	}

	// 解锁
	public static void unlock(String key) {
		Jedis j = getJedis();
		if (null != j) {
			j.del(key);
			close(j);
		}
	}

	// 删除
	public static void delete(String key) {
		Jedis j = getJedis();
		if (null != j) {
			j.del(key);
			close(j);
		}
	}

	// 设置值
	public static void set(String key, String value, int seconds) {
		Jedis j = getJedis();
		if (null != j) {
			j.set(key, value, j.exists(key) ? XX : NX, EX, seconds);
			close(j);
		}
	}

	// 设置值
	public static void set(String key, String value, long time) {
		Jedis j = getJedis();
		if (null != j) {
			j.set(key, value, j.exists(key) ? XX : NX, PX, time);
			close(j);
		}
	}

	// 设置值
	public static void set(String key, String value) {
		Jedis j = getJedis();
		j.set(key, value);
		close(j);
	}

	// 设置值(可选库)
	public static void setDbKey(String key, String value, Integer db) {
		Jedis j = getJedis();
		if (null != db && db >= 0 && db <= 15) {
			j.select(db);
		}
		j.set(key, value);
		close(j);
	}

	// 获取值(指定库)
	public static String getDbKey(Integer db, String key) {
		String value = null;
		Jedis j = getJedis();
		if (null != db && db >= 0 && db <= 15) {
			j.select(db);
		}
		if (null != j) {
			value = j.get(key);
			close(j);
		}
		return value;
	}

	// 获取值
	public static String get(String key) {
		String value = null;
		Jedis j = getJedis();
		if (null != j) {
			value = j.get(key);
			close(j);
		}
		return value;
	}

	// 获取值
	public static Integer getInteger(String key) {
		String value = null;
		Jedis j = getJedis();
		if (null != j) {
			value = j.get(key);
			close(j);
		}
		return StringUtils.isNotEmpty(value) ? Integer.parseInt(value) : null;
	}

	// 获取值
	public static Date getDate(String key) {
		String value = null;
		Jedis j = getJedis();
		if (null != j) {
			value = j.get(key);
			close(j);
		}
		return StringUtils.isNotEmpty(value) ? DateUtil.parseDate(value) : null;
	}

	// 释放jedis资源
	public static void close(final Jedis jedis) {
		if (jedis != null) {
			jedis.close();
		}
	}

}
