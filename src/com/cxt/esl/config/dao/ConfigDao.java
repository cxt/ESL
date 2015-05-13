package com.cxt.esl.config.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cxt.esl.config.domain.Config;
import com.j256.ormlite.dao.Dao;

public class ConfigDao {
	private Dao<Config, Integer> dao;

	public ConfigDao(Dao<Config, Integer> dao) {
		super();
		this.dao = dao;
	}
	
	public List<Config> queryAll() throws SQLException{
		return dao.queryForAll();
	}
	
	public List<Config> queryForCtypeOrCkey(int  ctype, String ckey) throws SQLException{
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("ctype", ctype);
		if(ckey.length() > 0){
			map.put("ckey", ckey);
		}
		return dao.queryForFieldValues(map);
	}
	
	public void insert(Config config) throws SQLException{
		dao.create(config);
	}
	public void delete(Config config) throws SQLException{
		dao.delete(config);
	}
	public void update(Config config) throws SQLException{
		dao.update(config);
	}
}
