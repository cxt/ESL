package com.cxt.esl.pattern.domain;

import java.io.Serializable;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "esls_patterns")
public class Pattern implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@DatabaseField(columnName = "pattern_id", generatedId = true, canBeNull = false)
	private int patternId;//
	@DatabaseField(columnName = "pattern_name")
	private String patternName;// varchar(50) DEFAULT NULL,
	@DatabaseField(columnName = "pattern_width")
	private int patternWidth;// int(11) DEFAULT NULL,
	@DatabaseField(columnName = "pattern_height")
	private int patternHeight;// int(11) DEFAULT NULL,
	@DatabaseField(columnName = "inch")
	private float inch;// decimal(10,2) DEFAULT NULL
	@DatabaseField(columnName = "bpp")
	private int bpp;// smallint(6) DEFAULT NULL,
	@DatabaseField(columnName = "model_id")
	private int modelId;// smallint(6) DEFAULT NULL,
	@DatabaseField(columnName = "model")
	private String model;// varchar(50) DEFAULT NULL,
	@DatabaseField(columnName = "code", dataType = DataType.LONG_STRING)
	private String code; // longtext,
	@DatabaseField(columnName = "image_url")
	private String imageUrl;// varchar(50) DEFAULT NULL,
	@DatabaseField(columnName = "image_Examp")
	private String imageExamp;// longblob,
	@DatabaseField(columnName = "readme")
	private String readme;// varchar(200) DEFAULT NULL
	@DatabaseField(columnName = "remarks")
	private String remarks;// varchar(200) DEFAULT NULL,
	@DatabaseField(columnName = "order_num", defaultValue = "0")
	private int orderNum;// int(10) DEFAULT '0',
	public int getPatternId() {
		return patternId;
	}

	public void setPatternId(int patternId) {
		this.patternId = patternId;
	}

	public String getPatternName() {
		return patternName;
	}

	public Pattern(int patternId, String patternName, int patternWidth,
			int patternHeight, float inch, int bpp, int modelId, String model,
			String code, String imageUrl, String imageExamp, String readme,
			String remarks, int orderNum) {
		super();
		this.patternId = patternId;
		this.patternName = patternName;
		this.patternWidth = patternWidth;
		this.patternHeight = patternHeight;
		this.inch = inch;
		this.bpp = bpp;
		this.modelId = modelId;
		this.model = model;
		this.code = code;
		this.imageUrl = imageUrl;
		this.imageExamp = imageExamp;
		this.readme = readme;
		this.remarks = remarks;
		this.orderNum = orderNum;
	}

	public void setPatternName(String patternName) {
		this.patternName = patternName;
	}

	public int getPatternWidth() {
		return patternWidth;
	}

	public void setPatternWidth(int patternWidth) {
		this.patternWidth = patternWidth;
	}

	public int getPatternHeight() {
		return patternHeight;
	}

	public void setPatternHeight(int patternHeight) {
		this.patternHeight = patternHeight;
	}

	public float getInch() {
		return inch;
	}

	public void setInch(float inch) {
		this.inch = inch;
	}

	public Pattern() {
	}

	public int getBpp() {
		return bpp;
	}

	public void setBpp(int bpp) {
		this.bpp = bpp;
	}

	public int getModelId() {
		return modelId;
	}

	public void setModelId(int modelId) {
		this.modelId = modelId;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getImageExamp() {
		return imageExamp;
	}

	public void setImageExamp(String imageExamp) {
		this.imageExamp = imageExamp;
	}

	public String getReadme() {
		return readme;
	}

	public void setReadme(String readme) {
		this.readme = readme;
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
