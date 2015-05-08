package com.cxt.esl.good.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cxt.esl.good.domain.Good;
import com.j256.ormlite.dao.Dao;

public class GoodDao {
	private Dao<Good,Integer> goodDao;

	public GoodDao(Dao<Good, Integer> goodDao) {
		super();
		this.goodDao = goodDao;
	}
	
	public List<Good> queryAll() throws SQLException{
		return goodDao.queryForAll();
	}
	
	public List<Good> queryForBarCodeOrPosNameOrStatusOrKindId(String barCode,String posName, int status, int kindId ) throws SQLException{
		Map<String, Object> map = new HashMap<String, Object>();
		if(barCode.length() > 0)
			map.put("bar_code", barCode);
		if(posName.length() > 0)
			map.put("pos_name", posName);
		if(status==0 || status ==1)
			map.put("status", status);
		if(kindId>0)
			map.put("kind_id", kindId);
		return goodDao.queryForFieldValues(map);
	}
	
	public void delete(Good good) throws SQLException{
		goodDao.delete(good);
	}
	
	public void insert(Good good) throws SQLException{
		goodDao.create(good);
	}
	
	public void update(Good good) throws SQLException{
		goodDao.update(good);
	}
}
