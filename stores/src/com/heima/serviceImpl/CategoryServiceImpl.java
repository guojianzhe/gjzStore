package com.heima.serviceImpl;

import java.sql.SQLException;
import java.util.List;

import com.heima.dao.CategoryDao;
import com.heima.daoImpl.CategoryDaoImpl;
import com.heima.domain.CateGory;
import com.heima.service.CategoryService;
import com.heima.utils.JedisPoolUtils;

import net.sf.json.JSONArray;
import redis.clients.jedis.Jedis;

public class CategoryServiceImpl implements CategoryService{

	@Override
	public String findCategoryAll() throws SQLException {
		
		CategoryDao categoryDao = new CategoryDaoImpl();
		List<CateGory> list = categoryDao.findCategoryAll();
		
		JSONArray json = JSONArray.fromObject(list);
		
		return json.toString();
	}

	@Override
	//从redis中获取数据
	public String fromByRedis() throws SQLException {
		
		Jedis jedis = JedisPoolUtils.getJedis();
		String value = jedis.get("dht");
		
		if(value==null) {
			value = findCategoryAll();
			jedis.set("dht", value);
		}
		return value;
	}

}
