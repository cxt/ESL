package com.cxt.esl.config.domain;

import java.io.Serializable;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "esls_sys_config")
public class Config implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@DatabaseField(columnName = "id", generatedId = true, canBeNull = false)
	private int id;// int(11) NOT NULL AUTO_INCREMENT,
	@DatabaseField(columnName = "ctype", defaultValue = "0")
	private int ctype;// varchar(255) DEFAULT NULL,
	@DatabaseField(columnName = "ckey", defaultValue = "")
	private String ckey;// varchar(100) DEFAULT NULL COMMENT 'ֵ',
	@DatabaseField(columnName = "cvalue", defaultValue = "")
	private String cvalue;// varchar(255) DEFAULT NULL COMMENT 'vaule',
	@DatabaseField(columnName = "note", defaultValue = "")
	private String note;// varchar(255) DEFAULT NULL,

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCtype() {
		return ctype;
	}
	public void setCtype(int ctype) {
		this.ctype = ctype;
	}
	public String getCkey() {
		return ckey;
	}
	public void setCkey(String ckey) {
		this.ckey = ckey;
	}
	public String getCvalue() {
		return cvalue;
	}
	public void setCvalue(String cvalue) {
		this.cvalue = cvalue;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public Config(int id, int ctype, String ckey, String cvalue, String note) {
		super();
		this.id = id;
		this.ctype = ctype;
		this.ckey = ckey;
		this.cvalue = cvalue;
		this.note = note;
	}
	public Config() {
		// TODO Auto-generated constructor stub
	}
}
