package com.cxt.esl.kind.domain;

import java.io.Serializable;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
@DatabaseTable(tableName="esls_kinds")
public class Kind implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8275219371167018847L;
	
	@DatabaseField(columnName = "kind_id", generatedId = true, canBeNull = false)
	private int kindId;// smallint(6) NOT NULL AUTO_INCREMENT COMMENT '分类id',
	@DatabaseField(columnName = "kind_name",defaultValue="")
	private String kindName;// varchar(50) DEFAULT NULL COMMENT '分类名',
	@DatabaseField(columnName = "father_id")
	private int fatherId;// smallint(6) DEFAULT NULL COMMENT '父分类id',
	@DatabaseField(columnName = "remarks",defaultValue="")
	private String remarks;// varchar(200) DEFAULT NULL COMMENT '备注',
	@DatabaseField(columnName = "order_num",defaultValue="0")
	private  int orderNum;// int(4) DEFAULT '0' COMMENT '排序',
	public Kind() {
		super();
	}
	public int getKindId() {
		return kindId;
	}
	public void setKindId(int kindId) {
		this.kindId = kindId;
	}
	public String getKindName() {
		return kindName;
	}
	public void setKindName(String kindName) {
		this.kindName = kindName;
	}
	public int getFatherId() {
		return fatherId;
	}
	public void setFatherId(int fatherId) {
		this.fatherId = fatherId;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public int getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(int orderNum) {
		this.orderNum = orderNum;
	}
	
	
}