package com.cxt.esl.kind.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cxt.esl.kind.domain.Kind;
import com.j256.ormlite.dao.Dao;

public class KindDao {
	private Dao<Kind,Integer> kindDao;

	public KindDao(Dao<Kind, Integer> kindDao) {
		super();
		this.kindDao = kindDao;
	}
	
	public List<Kind> queryAll() throws SQLException{
		return kindDao.queryForAll();
	}
	
	public List<Kind> queryForKindName(String kindName) throws SQLException{
		Map<String, Object> map = new HashMap<String, Object>();
		if(kindName.length() > 0){
			map.put("kind_name", kindName);
			return kindDao.queryForFieldValues(map);
		}
		return kindDao.queryForAll();
	}
	
	public void delete(Kind kind) throws SQLException{
		kindDao.delete(kind);
	}
	
	public void insert(Kind kind) throws SQLException{
		kindDao.create(kind);
	}
	
	public void update(Kind kind) throws SQLException{
		kindDao.update(kind);
	}
}
