package com.cxt.esl.user.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cxt.esl.user.domain.User;
import com.j256.ormlite.dao.Dao;

public class UserDao {
	Dao<User, Integer> dao;

	public UserDao(Dao<User, Integer> dao) {
		super();
		this.dao = dao;
	}

	public List<User> queryAll() throws SQLException {
		return dao.queryForAll();
	}

	public List<User> queryForCodeOrName(String userCode, String userName)
			throws SQLException {
		Map<String, Object> map = new HashMap<String, Object>();
		if (userCode.length() > 0) {
			map.put("user_code", userCode);
		}
		if (userName.length() > 0) {
			map.put("user_name", userName);
		}
		if (map.size() > 0) {
			return dao.queryForFieldValues(map);
		}
		return this.queryAll();
	}

	public boolean findUser(String userCode) throws SQLException {
		Map<String, Object> map = new HashMap<String, Object>();
		if (userCode.length() > 0) {
			map.put("user_code", userCode);
		}
		List<User> users = dao.queryForFieldValues(map);
		if (users.size() > 0) {
			return true;
		}
		return false;
	}

	public boolean findUser(String userCode, String password)
			throws SQLException {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user_code", userCode);
		map.put("password", password);
		List<User> users = dao.queryForFieldValues(map);
		if (users.size() > 0) {
			return true;
		}
		return false;
	}
	
	public void insert(User user) throws SQLException{
		dao.create(user);
	}
	
	public void update(User user) throws SQLException{
		dao.update(user);
	}
	public void delete(User user) throws SQLException{
		dao.delete(user);
	}
}
