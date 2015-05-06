package com.cxt.esl.pattern.dao;

import java.sql.SQLException;
import java.util.List;

import com.cxt.esl.pattern.domain.Pattern;
import com.j256.ormlite.dao.Dao;

public class PatternDao {
	private Dao<Pattern, Integer> patternDao;

	
	
	public PatternDao(Dao<Pattern, Integer> patternDao) {
		super();
		this.patternDao = patternDao;
	}

	public Dao<Pattern, Integer> getPatternDao() {
		return patternDao;
	}

	public void setPatternDao(Dao<Pattern, Integer> patternDao) {
		this.patternDao = patternDao;
	}

	public List<Pattern> queryAll() throws SQLException{
		return patternDao.queryForAll();
	}
	
	
	
	public void delete(Pattern p) throws SQLException{
		patternDao.delete(p);
	}
	
	public void insert(Pattern p) throws SQLException{
		patternDao.create(p);
	}
	
	public void update(Pattern p) throws SQLException{
		patternDao.update(p);
	}
}
