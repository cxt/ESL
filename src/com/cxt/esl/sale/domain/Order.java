package com.cxt.esl.sale.domain;

import java.io.Serializable;
import java.util.Date;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName="esls_order")
public class Order implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5176186463588659259L;
	@DatabaseField(columnName="order_id",generatedId=true)
	private int orderId;
	@DatabaseField(columnName="operator",defaultValue="")
	private String operator;
	@DatabaseField(columnName="all_price",defaultValue="0.00")
	private float allPrice;
	@DatabaseField(columnName="user_pay",defaultValue="0.00")
	private float userPay;
	@DatabaseField(columnName="change",defaultValue="0.00")
	private float change;
	@DatabaseField(columnName="goods",defaultValue="",dataType=DataType.LONG_STRING)
	private String goods;
	@DatabaseField(columnName="create_date")
	private Date createDate;
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public float getAllPrice() {
		return allPrice;
	}
	public void setAllPrice(float allPrice) {
		this.allPrice = allPrice;
	}
	public float getUserPay() {
		return userPay;
	}
	public void setUserPay(float userPay) {
		this.userPay = userPay;
	}
	public float getChange() {
		return change;
	}
	public void setChange(float change) {
		this.change = change;
	}
	public String getGoods() {
		return goods;
	}
	public void setGoods(String goods) {
		this.goods = goods;
	}
	public Order() {
		super();
	}
	
	
}
