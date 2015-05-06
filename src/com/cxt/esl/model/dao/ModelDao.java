package com.cxt.esl.model.dao;

import java.sql.SQLException;
import java.util.List;

import com.cxt.esl.model.domain.Model;
import com.j256.ormlite.dao.Dao;

public class ModelDao {
	public ModelDao(Dao<Model, Integer> modelDao) {
		super();
		this.modelDao = modelDao;
	}

	private Dao<Model, Integer> modelDao;

	public List<Model> queryAll() throws SQLException{
		return modelDao.queryForAll();
	}
	
	
	public void delete(Model model) throws SQLException{
		modelDao.delete(model);
	}
	
	public void insert(Model model) throws SQLException{
		modelDao.create(model);
	}
	
	public void update(Model model) throws SQLException{
		modelDao.update(model);
	}
}

