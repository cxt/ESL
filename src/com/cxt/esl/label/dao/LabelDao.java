package com.cxt.esl.label.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cxt.esl.label.domain.Label;
import com.j256.ormlite.dao.Dao;

public class LabelDao{
	private Dao<Label, Integer> labelDao;

	public Dao<Label, Integer> getLabelDao() {
		return labelDao;
	}

	public LabelDao(Dao<Label, Integer> labelDao) {
		super();
		this.labelDao = labelDao;
	}

	public void setLabelDao(Dao<Label, Integer> labelDao) {
		this.labelDao = labelDao;
	}
	
	public List<Label> queryAll() throws SQLException{
		return labelDao.queryForAll();
	}
	
	public List<Label> queryByEslIdOrWorkStatus(long eslId, int workStatus) throws SQLException{
		Map<String, Object> map = new HashMap<String, Object>();
		if(eslId != -1){
			map.put("esl_id", eslId);
		}
		if(workStatus != -2){
			map.put("work_status", workStatus);
		}
		if(map.size()==0)
			return this.queryAll();
		else
			return labelDao.queryForFieldValues(map);
	}
	
	public void delete(Label label) throws SQLException{
		labelDao.delete(label);
	}
	
	public void insert(Label label) throws SQLException{
		labelDao.create(label);
	}
	
	public void update(Label label) throws SQLException{
		labelDao.update(label);
	}
}
