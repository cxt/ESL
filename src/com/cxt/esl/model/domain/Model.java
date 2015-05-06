package com.cxt.esl.model.domain;

import java.io.Serializable;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;


@DatabaseTable(tableName="esls_esl_model")
public class Model implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6768250261519580952L;
	@DatabaseField(columnName = "model_id", generatedId = true, canBeNull = false)
	private int modelId;// smallint(6) NOT NULL AUTO_INCREMENT COMMENT '型号ID',
	@DatabaseField(columnName = "model_name")
	private String modelName;// varchar(50) DEFAULT NULL COMMENT '规格型号（出厂）',
	@DatabaseField(columnName = "model_note")
	private String modelNote;// varchar(40) DEFAULT NULL COMMENT '型号特征',
	@DatabaseField(columnName = "inch")
	private float inch;// decimal(10,2) DEFAULT NULL COMMENT '屏幕尺寸（英寸）',
	@DatabaseField(columnName = "esl_width")
	private int eslWidth;// smallint(6) DEFAULT NULL COMMENT '宽（像素）',
	@DatabaseField(columnName = "esl_height")
	private int eslHeight;// smallint(6) DEFAULT NULL COMMENT '高（像素）',
	@DatabaseField(columnName = "rotate")
	private int rotate;// smallint(6) DEFAULT NULL COMMENT '翻转角度',
	@DatabaseField(columnName = "bpp")
	private  int bpp;// tinyint(4) DEFAULT NULL COMMENT '每像素所占位（灰阶）',
	@DatabaseField(columnName = "bpp_red")
	private  int bppRed;//smallint(6) DEFAULT NULL COMMENT '红色bpp',
	@DatabaseField(columnName = "bpp_green")
	private int bppGreen;// smallint(6) DEFAULT NULL COMMENT '绿色bpp',
	@DatabaseField(columnName = "bpp_blue")
	private  int bppBlue;// smallint(6) DEFAULT NULL COMMENT '蓝色bpp',
	@DatabaseField(columnName = "remarks")
	private  String remarks;// varchar(200) DEFAULT NULL COMMENT '备注',
	public int getModelId() {
		return modelId;
	}
	public void setModelId(int modelId) {
		this.modelId = modelId;
	}
	public String getModelName() {
		return modelName;
	}
	public void setModelName(String modelName) {
		this.modelName = modelName;
	}
	public String getModelNote() {
		return modelNote;
	}
	public void setModelNote(String modelNote) {
		this.modelNote = modelNote;
	}
	public float getInch() {
		return inch;
	}
	public void setInch(float inch) {
		this.inch = inch;
	}
	public int getEslWidth() {
		return eslWidth;
	}
	public void setEslWidth(int eslWidth) {
		this.eslWidth = eslWidth;
	}
	public int getEslHeight() {
		return eslHeight;
	}
	public void setEslHeight(int eslHeight) {
		this.eslHeight = eslHeight;
	}
	public int getRotate() {
		return rotate;
	}
	public void setRotate(int rotate) {
		this.rotate = rotate;
	}
	public int getBpp() {
		return bpp;
	}
	public void setBpp(int bpp) {
		this.bpp = bpp;
	}
	public int getBppRed() {
		return bppRed;
	}
	public void setBppRed(int bppRed) {
		this.bppRed = bppRed;
	}
	public int getBppGreen() {
		return bppGreen;
	}
	public void setBppGreen(int bppGreen) {
		this.bppGreen = bppGreen;
	}
	public int getBppBlue() {
		return bppBlue;
	}
	public void setBppBlue(int bppBlue) {
		this.bppBlue = bppBlue;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	public Model(int modelId, String modelName, String modelNote, float inch,
			int eslWidth, int eslHeight, int rotate, int bpp, int bppRed,
			int bppGreen, int bppBlue, String remarks) {
		super();
		this.modelId = modelId;
		this.modelName = modelName;
		this.modelNote = modelNote;
		this.inch = inch;
		this.eslWidth = eslWidth;
		this.eslHeight = eslHeight;
		this.rotate = rotate;
		this.bpp = bpp;
		this.bppRed = bppRed;
		this.bppGreen = bppGreen;
		this.bppBlue = bppBlue;
		this.remarks = remarks;
	}
	public Model() {
		super();
	}
	
	
}