package com.cxt.esl.sale.dao;

import java.sql.SQLException;
import java.util.List;

import com.cxt.esl.sale.domain.Order;
import com.j256.ormlite.dao.Dao;

public class OrderDao {
	public OrderDao(Dao<Order, Integer> dao) {
		super();
		this.dao = dao;
	}

	Dao<Order, Integer> dao;

	public Dao<Order, Integer> getDao() {
		return dao;
	}

	public void setDao(Dao<Order, Integer> dao) {
		this.dao = dao;
	}
	
	public List<Order> queryAll() throws SQLException{
		return dao.queryForAll();
	}
	
	public void delete(Order order) throws SQLException{
		dao.delete(order);
	}
	
	
	public void insert(Order arg0) throws SQLException{
		
		dao.create(arg0);
	}
	
}
