package com.cxt.esl.user.domain;

import java.io.Serializable;
import java.util.Date;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "sys_user")
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6803873332391089421L;
	
	@DatabaseField(columnName = "user_id", generatedId = true, canBeNull = false)
	private int userId;// int(11) NOT NULL AUTO_INCREMENT,
	@DatabaseField(columnName = "user_code", canBeNull = false, defaultValue = "")
	private String userCode;// varchar(100) NOT NULL,
	@DatabaseField(columnName = "user_name", canBeNull = false, defaultValue = "")
	private String userName;// varchar(200) NOT NULL,
	@DatabaseField(columnName = "status", canBeNull = false, defaultValue = "0")
	private int status;// varchar(10) NOT NULL,
	@DatabaseField(columnName = "password", canBeNull = false, defaultValue = "")
	private String password;// varchar(50) NOT NULL,
	@DatabaseField(columnName = "create_date", canBeNull = false)
	private Date createDate;// date NOT NULL,
	@DatabaseField(columnName = "create_by")
	private int createBy;// int(11) NOT NULL,
	@DatabaseField(columnName = "last_update_date",canBeNull = false)
	private Date lastUpdateDate;// date NOT NULL,
	@DatabaseField(columnName = "last_update_by")
	private int lastUpdateBy;// int(11) NOT NULL,
	@DatabaseField(columnName = "role_name",defaultValue="0")
	private int roleName;// int(11) DEFAULT NULL,
	public User() {
		super();
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public int getCreateBy() {
		return createBy;
	}
	public void setCreateBy(int createBy) {
		this.createBy = createBy;
	}
	public Date getLastUpdateDate() {
		return lastUpdateDate;
	}
	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}
	public int getLastUpdateBy() {
		return lastUpdateBy;
	}
	public void setLastUpdateBy(int lastUpdateBy) {
		this.lastUpdateBy = lastUpdateBy;
	}
	public int getRoleName() {
		return roleName;
	}
	public void setRoleName(int roleName) {
		this.roleName = roleName;
	}
	
}
