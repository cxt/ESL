package com.cxt.esl.good.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cxt.esl.good.domain.GoodUpdateHistory;
import com.j256.ormlite.dao.Dao;

public class GoodUpdateHistoryDao {
	private Dao<GoodUpdateHistory,Integer> goodUpdateHistoryDao;

	public GoodUpdateHistoryDao(Dao<GoodUpdateHistory, Integer> goodUpdateHistoryDao) {
		super();
		this.goodUpdateHistoryDao = goodUpdateHistoryDao;
	}
	
	public List<GoodUpdateHistory> queryAll() throws SQLException{
		return goodUpdateHistoryDao.queryForAll();
	}
	
	public List<GoodUpdateHistory> queryForBarCodeOrPosNameOrStatus(String barCode,String posName, int status) throws SQLException{
		Map<String, Object> map = new HashMap<String, Object>();
		if(barCode.length() > 0)
			map.put("bar_code", barCode);
		if(posName.length() > 0)
			map.put("pos_name", posName);
		if(status==0 || status ==1 || status == 3)
			map.put("status", status);
		return goodUpdateHistoryDao.queryForFieldValues(map);
	}
	
	public void delete(GoodUpdateHistory goodUpdateHistory) throws SQLException{
		goodUpdateHistoryDao.delete(goodUpdateHistory);
	}
	
	public void insert(GoodUpdateHistory goodUpdateHistory) throws SQLException{
		goodUpdateHistoryDao.create(goodUpdateHistory);
	}
	
	public void update(GoodUpdateHistory goodUpdateHistory) throws SQLException{
		goodUpdateHistoryDao.update(goodUpdateHistory);
	}
}
