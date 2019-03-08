package com.heima.utils;

import java.util.ResourceBundle;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisPoolUtils {
	
	private static JedisPoolConfig jc = null;
	private static int maxTotal = 0;
	private static long maxWaitMillis = 0;
	private static String host = null;
	private static int port =0;
	private static JedisPool pool = null;
	static {
		jc = new JedisPoolConfig();
		//ªÒ»°properties
		ResourceBundle re = ResourceBundle.getBundle("jedis");
		maxTotal = Integer.parseInt(re.getString("maxTotal"));
		maxWaitMillis = Long.parseLong(re.getString("maxWaitMillis")); 
		jc.setMaxTotal(maxTotal);
		jc.setMaxWaitMillis(maxWaitMillis);
		host = re.getString("host");
		port = Integer.parseInt(re.getString("port"));
		pool = new JedisPool(jc,host,port);
		
	}
	
	
	public static Jedis getJedis () {
		return pool.getResource();
	}
}
