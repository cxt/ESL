package com.cxt.esl.label.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cxt.esl.good.domain.Good;
import com.cxt.esl.label.domain.Label;
import com.cxt.esl.model.domain.Model;
import com.cxt.esl.pattern.domain.Pattern;
import com.j256.ormlite.dao.Dao;

public class LabelDao {
	private Dao<Label, Integer> labelDao;
	private Dao<Pattern, Integer> patternDao;
	private Dao<Model, Integer> modelDao;
	private Dao<Good, Integer> goodDao;

	public LabelDao(Dao<Label, Integer> labelDao,
			Dao<Pattern, Integer> patternDao, Dao<Model, Integer> modelDao,
			Dao<Good, Integer> goodDao) {
		super();
		this.labelDao = labelDao;
		this.patternDao = patternDao;
		this.modelDao = modelDao;
		this.goodDao = goodDao;
	}

	public Dao<Good, Integer> getGoodDao() {
		return goodDao;
	}

	public void setGoodDao(Dao<Good, Integer> goodDao) {
		this.goodDao = goodDao;
	}

	public Dao<Pattern, Integer> getPatternDao() {
		return patternDao;
	}

	public void setPatternDao(Dao<Pattern, Integer> patternDao) {
		this.patternDao = patternDao;
	}

	public Dao<Model, Integer> getModelDao() {
		return modelDao;
	}

	public void setModelDao(Dao<Model, Integer> modelDao) {
		this.modelDao = modelDao;
	}

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

	public List<Label> queryAll() throws SQLException {
		List<Label> list = labelDao.queryForAll();
		for (Label l : list) {
			l.setGood(this.getGood(l.getGoodsId()));
			l.setPattern(this.getPattern(l.getPatternId()));
			l.setModel(this.getModel(l.getModelId()));
		}
		return list;
	}

	public List<Label> queryByEslIdOrWorkStatus(int eslId, int workStatus)
			throws SQLException {
		Map<String, Object> map = new HashMap<String, Object>();
		if (eslId != -1) {
			map.put("esl_id", eslId);
		}
		if (workStatus != -2) {
			map.put("work_status", workStatus);
		}
		if (map.size() == 0)
			return this.queryAll();
		else {

			List<Label> list = labelDao.queryForFieldValues(map);
			for (Label l : list) {
				l.setGood(this.getGood(l.getGoodsId()));
				l.setPattern(this.getPattern(l.getPatternId()));
				l.setModel(this.getModel(l.getModelId()));
			}
			return list;
		}
	}

	public void delete(Label label) throws SQLException {
		labelDao.delete(label);
	}

	public void insert(Label label) throws SQLException {
		labelDao.create(label);
	}

	public void update(Label label) throws SQLException {
		labelDao.update(label);
	}

	public Label findById(int id) throws SQLException {
		return labelDao.queryForId(id);
	}

	private Good getGood(int goodId) throws SQLException {
		return goodDao.queryForId(goodId);
	}

	private Pattern getPattern(int patternId) throws SQLException {
		return patternDao.queryForId(patternId);
	}

	private Model getModel(int modelId) throws SQLException {
		return modelDao.queryForId(modelId);
	}

	public List<Label> findLabelByGoodId(int goodId) throws SQLException {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("goods_id", goodId);
		return labelDao.queryForFieldValues(map);
	}
}
