package com.cxt.esl.label.domain;

import java.io.Serializable;
import java.util.Date;

import com.cxt.esl.good.domain.Good;
import com.cxt.esl.model.domain.Model;
import com.cxt.esl.pattern.domain.Pattern;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;


@DatabaseTable(tableName = "esls_esl")
public class Label implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1556651053720332922L;
	/**
	 * 
	 */
	
	@DatabaseField(columnName = "esl_id", generatedId = true, canBeNull = false)
	private int eslId; // EID
	@DatabaseField(columnName = "mac_id", defaultValue = "", canBeNull = false,unique=true)
	private String macId; // MAC地址
	@DatabaseField(columnName = "inch")
	private float inch; // 屏幕尺寸（英寸）
	@DatabaseField(columnName = "esl_width")
	private int eslWidth; // 宽（像素）
	@DatabaseField(columnName = "esl_height")
	private int eslHeight;// 高（像素）
	@DatabaseField(columnName = "bpp")
	private int bpp;// 每像素所占位
	@DatabaseField(columnName = "kinds_id")
	private String kindsId;// （所属分类id）
	@DatabaseField(columnName = "goods_id")
	private int goodsId;// 关联商品id
	@DatabaseField(columnName = "pattern_id")
	private int patternId;// 使用模板id,
	@DatabaseField(columnName = "ap_id",defaultValue="")
	private String apId;// 所属AP,
	@DatabaseField(columnName = "user_id")
	private int userId;// 操作人id,
	@DatabaseField(columnName = "sleep_period")
	private int sleepPeriod;// 休眠时长，完成本次操作后，ESL该休眠的时间长度（ms）,
	@DatabaseField(columnName = "sleep_mode")
	private int sleepMode;// 休眠模式
	@DatabaseField(columnName = "gate_mac")
	private String gateMac;// 网关mac
	@DatabaseField(columnName = "gate_ip")
	private String gateIp;// 网关ip,
	@DatabaseField(columnName = "rout_mac")
	private String routMac;// 路由器mac
	@DatabaseField(columnName = "device_id")
	private int deviceId;// 设备id
	@DatabaseField(columnName = "last_comm_time")
	private Date lastCommTime;// 最后通信时间
	@DatabaseField(columnName = "power")
	private int power;// 电池电量
	@DatabaseField(columnName = "op_time")
	private Date opTime;// 调入时间
	@DatabaseField(columnName = "esl_status", defaultValue = "0", canBeNull = false)
	private int eslStatus;// ESL工作状态，0-有效 1-暂停使用 2-失效（损坏，撤离等）,
	@DatabaseField(columnName = "remarks",defaultValue="")
	private String remarks;// 备注
	@DatabaseField(columnName = "signal")
	private int signal;// 通信信号强度
	@DatabaseField(columnName = "model_id")
	private int modelId;// 所属型号id
	@DatabaseField(columnName = "mycode",defaultValue="")
	private String mycode;// 用户自定义编号,
	@DatabaseField(columnName = "work_status", defaultValue = "0", canBeNull = false)
	private int workStatus;// 工作状态，-1 未知异常，0 初始，1 正常，2 生成图片，3 下发图片没feedback，4
							// 下发图片有feedback，5 esl 不在线,
	@DatabaseField(columnName = "show_data_url")
	private String showDataUrl;// 目前显示内容的图片的地址,
	@DatabaseField(columnName = "has_nogenerate_data", defaultValue = "0")
	private int hasNogenerateData;// 是否有未生成的图片 0 没有 1 有,
	@DatabaseField(columnName = "has_nosend_data", defaultValue = "0")
	private int hasNosendData;// 是否有未下发的图片 0 没有 1 有
	@DatabaseField(columnName = "has_nofeedback_data", defaultValue = "0")
	private int hasNofeedbackData;// 是否有未反馈的图片 0 没有 1 有

	private Good good;
	private Pattern pattern;
	private Model model;
	public Good getGood() {
		return good;
	}

	public void setGood(Good good) {
		this.good = good;
	}

	public Pattern getPattern() {
		return pattern;
	}

	public void setPattern(Pattern pattern) {
		this.pattern = pattern;
	}

	public Model getModel() {
		return model;
	}

	public void setModel(Model model) {
		this.model = model;
	}

	public Label(int eslId, String macId, float inch, int eslWidth,
			int eslHeight, int bpp, String kindsId, int goodsId, int patternId,
			String apId, int userId, int sleepPeriod, int sleepMode,
			String gateMac, String gateIp, String routMac, int deviceId,
			Date lastCommTime, int power, Date opTime, int eslStatus,
			String remarks, int signal, int modelId, String mycode,
			int workStatus, String showDataUrl, int hasNogenerateData,
			int hasNosendData, int hasNofeedbackData) {
		super();
		this.eslId = eslId;
		this.macId = macId;
		this.inch = inch;
		this.eslWidth = eslWidth;
		this.eslHeight = eslHeight;
		this.bpp = bpp;
		this.kindsId = kindsId;
		this.goodsId = goodsId;
		this.patternId = patternId;
		this.apId = apId;
		this.userId = userId;
		this.sleepPeriod = sleepPeriod;
		this.sleepMode = sleepMode;
		this.gateMac = gateMac;
		this.gateIp = gateIp;
		this.routMac = routMac;
		this.deviceId = deviceId;
		this.lastCommTime = lastCommTime;
		this.power = power;
		this.opTime = opTime;
		this.eslStatus = eslStatus;
		this.remarks = remarks;
		this.signal = signal;
		this.modelId = modelId;
		this.mycode = mycode;
		this.workStatus = workStatus;
		this.showDataUrl = showDataUrl;
		this.hasNogenerateData = hasNogenerateData;
		this.hasNosendData = hasNosendData;
		this.hasNofeedbackData = hasNofeedbackData;
	}

	public Label() {
		// TODO Auto-generated constructor stub
	}

	public int getEslId() {
		return eslId;
	}

	public void setEslId(int eslId) {
		this.eslId = eslId;
	}

	public String getMacId() {
		return macId;
	}

	public void setMacId(String macId) {
		this.macId = macId;
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

	public int getBpp() {
		return bpp;
	}

	public void setBpp(int bpp) {
		this.bpp = bpp;
	}

	public String getKindsId() {
		return kindsId;
	}

	public void setKindsId(String kindsId) {
		this.kindsId = kindsId;
	}

	public int getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(int goodsId) {
		this.goodsId = goodsId;
	}

	public int getPatternId() {
		return patternId;
	}

	public void setPatternId(int patternId) {
		this.patternId = patternId;
	}

	public String getApId() {
		return apId;
	}

	public void setApId(String apId) {
		this.apId = apId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getSleepPeriod() {
		return sleepPeriod;
	}

	public void setSleepPeriod(int sleepPeriod) {
		this.sleepPeriod = sleepPeriod;
	}

	public int getSleepMode() {
		return sleepMode;
	}

	public void setSleepMode(int sleepMode) {
		this.sleepMode = sleepMode;
	}

	public String getGateMac() {
		return gateMac;
	}

	public void setGateMac(String gateMac) {
		this.gateMac = gateMac;
	}

	public String getGateIp() {
		return gateIp;
	}

	public void setGateIp(String gateIp) {
		this.gateIp = gateIp;
	}

	public String getRoutMac() {
		return routMac;
	}

	public void setRoutMac(String routMac) {
		this.routMac = routMac;
	}

	public int getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(int deviceId) {
		this.deviceId = deviceId;
	}

	public Date getLastCommTime() {
		return lastCommTime;
	}

	public void setLastCommTime(Date lastCommTime) {
		this.lastCommTime = lastCommTime;
	}

	public int getPower() {
		return power;
	}

	public void setPower(int power) {
		this.power = power;
	}

	public Date getOpTime() {
		return opTime;
	}

	public void setOpTime(Date opTime) {
		this.opTime = opTime;
	}

	public int getEslStatus() {
		return eslStatus;
	}

	public void setEslStatus(int eslStatus) {
		this.eslStatus = eslStatus;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public int getSignal() {
		return signal;
	}

	public void setSignal(int signal) {
		this.signal = signal;
	}

	public int getModelId() {
		return modelId;
	}

	public void setModelId(int modelId) {
		this.modelId = modelId;
	}

	public String getMycode() {
		return mycode;
	}

	public void setMycode(String mycode) {
		this.mycode = mycode;
	}

	public int getWorkStatus() {
		return workStatus;
	}

	public void setWorkStatus(int workStatus) {
		this.workStatus = workStatus;
	}

	public String getShowDataUrl() {
		return showDataUrl;
	}

	public void setShowDataUrl(String showDataUrl) {
		this.showDataUrl = showDataUrl;
	}

	public int getHasNogenerateData() {
		return hasNogenerateData;
	}

	public void setHasNogenerateData(int hasNogenerateData) {
		this.hasNogenerateData = hasNogenerateData;
	}

	public int getHasNosendData() {
		return hasNosendData;
	}

	public void setHasNosendData(int hasNosendData) {
		this.hasNosendData = hasNosendData;
	}

	public int getHasNofeedbackData() {
		return hasNofeedbackData;
	}

	public void setHasNofeedbackData(int hasNofeedbackData) {
		this.hasNofeedbackData = hasNofeedbackData;
	}

}
