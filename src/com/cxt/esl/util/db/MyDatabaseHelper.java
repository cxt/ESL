package com.cxt.esl.util.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class MyDatabaseHelper extends SQLiteOpenHelper {
	private Context mContext;
	public static final String ESLS_AP = "CREATE TABLE 'esls_ap' ("
			+ "'ap_id' TEXT(40) NOT NULL /*设备id*/,"
			+ "'model' TEXT(41) DEFAULT NULL,"
			+ "'note_tec' TEXT(200) DEFAULT NULL,"
			+ "'note_func' TEXT(200) DEFAULT NULL,"
			+ "'work_mode' INTEGER DEFAULT NULL /*ap工作模式*/,"
			+ "'shelf_id' INTEGER DEFAULT NULL,"
			+ "'sn' TEXT(50) NOT NULL DEFAULT '' /*序列号*/,"
			+ "'ver' TEXT(50) DEFAULT NULL /*软件，硬件版本号*/,"
			+ "'mac_id' TEXT(20) NOT NULL DEFAULT '' /*MAC地址*/,"
			+ "'multipath' INTEGER DEFAULT NULL /*所控电源路数*/,"
			+ "'gate_mac' TEXT(20) DEFAULT NULL /*所属网关MAC*/,"
			+ "'gate_ip' TEXT(50) DEFAULT NULL /*所属网关ip*/,"
			+ "'rout_mac' TEXT(20) DEFAULT NULL /*所属路由器MAC*/,"
			+ "'remarks' TEXT(200) DEFAULT NULL /*备注*/,"
			+ "'sleep_period' INTEGER DEFAULT NULL /*AP的睡眠时长，有线版本才用*/,"
			+ "'router_num' INTEGER DEFAULT NULL," + "PRIMARY KEY ('ap_id')"
			+ ")";

	public static final String ESLS_CONFIG = "CREATE TABLE 'esls_config' ("
			+ "'good_syn_time' TEXT NOT NULL DEFAULT '1999-01-01 00:00:00',"
			+ "'price_syn_time' TEXT DEFAULT NULL,"
			+ "'title' TEXT(50) DEFAULT NULL,"
			+ "'copyright' TEXT(200) DEFAULT NULL,"
			+ "'unit' TEXT(50) DEFAULT NULL,"
			+ "'unit_id' TEXT(50) DEFAULT NULL,"
			+ "'retry_times' INTEGER(4) DEFAULT NULL,"
			+ "'blk_crea_period' INTEGER(20) DEFAULT NULL,"
			+ "'check_period' INTEGER(20) DEFAULT NULL,"
			+ "'goods_syn_period' INTEGER(20) DEFAULT NULL,"
			+ "'exceed_period' INTEGER(20) DEFAULT NULL,"
			+ "'wire_exceed_period' INTEGER(20) DEFAULT NULL,"
			+ "'up_down' INTEGER(4) DEFAULT NULL,"
			+ "'upload_period' INTEGER(20) DEFAULT NULL,"
			+ "'threshold_off_warn' INTEGER(20) DEFAULT NULL,"
			+ "'threshold_off_err' INTEGER(20) DEFAULT NULL,"
			+ "'threshold_low_warn' INTEGER(20) DEFAULT NULL,"
			+ "'threshold_low_err' INTEGER(20) DEFAULT NULL,"
			+ "'sys_type' INTEGER(4) DEFAULT NULL,"
			+ "'guide_type' INTEGER(4) DEFAULT NULL /*系统是否支持（上级）指导价*/,"
			+ "'smart_upd_mode' INTEGER(4) DEFAULT NULL /*智能更新模式,0-普通更新模式，全屏更新（默认）,1-智能更新模式，分块更新*/,"
			+ "'upd_comfirm' INTEGER(4) DEFAULT NULL /*更新esl是否需要用户确认*/,"
			+ "'up_ips' TEXT(100) DEFAULT NULL /*服务器地址列表*/,"
			+ "'conn_url' TEXT /*数据库连接字符串*/,"
			+ "'conn_username' TEXT(100) DEFAULT NULL /*用户名*/,"
			+ "'conn_password' TEXT(100) DEFAULT NULL /*密码*/" + ")";

	public static final String ESLS_DEVICE = "CREATE TABLE 'esls_devices' ("
			+ "'device_id' INTEGER PRIMARY KEY AUTOINCREMENT /*设备id*/,"
			+ "'sn' TEXT(50) DEFAULT NULL /*序列号*/,"
			+ "'ver' TEXT(50) DEFAULT NULL /*软件版本号*/,"
			+ "'model' TEXT(50) DEFAULT NULL /*规格型号*/,"
			+ "'mac_id' TEXT(20) DEFAULT NULL /*MAC地址*/,"
			+ "'device_type' INTEGER DEFAULT NULL /*设备类型，1-LAN应用网关 2-WAN应用网关 3-无线路由器 4-移动监控终端 5-手持机（安卓）*/,"
			+ "'user_id' INTEGER DEFAULT NULL /*操作人id*/,"
			+ "'op_time' TEXT DEFAULT NULL /*调入时间*/,"
			+ "'remarks' TEXT(200) DEFAULT NULL /*备注*/" + ")";

	public static final String ESLS_ESL = "CREATE TABLE 'esls_esl' ("
			+ "'esl_id' INTEGER(20) NOT NULL /*EID*/,"
			+ "'mac_id' TEXT(50) NOT NULL DEFAULT '' /*MAC地址*/,"
			+ "'inch' REAL(10,2) DEFAULT NULL /*屏幕尺寸（英寸）*/,"
			+ "'esl_width' INTEGER DEFAULT NULL /*宽（像素）*/,"
			+ "'esl_height' INTEGER DEFAULT NULL /*高（像素）*/,"
			+ "'bpp' INTEGER(3) DEFAULT NULL /*每像素所占位*/,"
			+ "'kinds_id' TEXT(50) DEFAULT NULL /*（所属分类id）*/,"
			+ "'goods_id' INTEGER DEFAULT NULL /*关联商品id*/,"
			+ "'pattern_id' INTEGER DEFAULT NULL /*使用模板id*/,"
			+ "'ap_id' INTEGER DEFAULT NULL /*所属AP*/,"
			+ "'user_id' INTEGER DEFAULT NULL /*操作人id*/,"
			+ "'sleep_period' INTEGER DEFAULT NULL /*休眠时长，完成本次操作后，ESL该休眠的时间长度（ms）*/,"
			+ "'sleep_mode' INTEGER DEFAULT NULL /*休眠模式*/,"
			+ "'gate_mac' TEXT(20) DEFAULT NULL /*网关mac*/,"
			+ "'gate_ip' TEXT(20) DEFAULT NULL /*网关ip*/,"
			+ "'rout_mac' TEXT(20) DEFAULT NULL /*路由器mac*/,"
			+ "'device_id' INTEGER DEFAULT NULL /*设备id*/,"
			+ "'last_comm_time' TEXT DEFAULT NULL /*最后通信时间*/,"
			+ "'power' INTEGER DEFAULT NULL /*电池电量*/,"
			+ "'op_time' TEXT DEFAULT NULL /*调入时间*/,"
			+ "'esl_status' INTEGER NOT NULL DEFAULT '0' /*ESL工作状态，0-有效 1-暂停使用 2-失效（损坏，撤离等）*/,"
			+ "'remarks' TEXT(200) DEFAULT NULL /*备注*/,"
			+ "'signal' INTEGER DEFAULT NULL /*通信信号强度*/,"
			+ "'model_id' INTEGER DEFAULT NULL /*所属型号id*/,"
			+ "'mycode' TEXT(20) DEFAULT NULL /*用户自定义编号*/,"
			+ "'work_status' INTEGER DEFAULT '0' /*工作状态，-1 未知异常，0 初始，1 正常，2 生成图片，3 下发图片没feedback，4 下发图片有feedback，5 esl 不在线*/,"
			+ "'show_data_url' TEXT(200) DEFAULT NULL /*目前显示内容的图片的地址*/,"
			+ "'has_nogenerate_data' INTEGER DEFAULT '0' /*是否有未生成的图片 0 没有 1 有*/,"
			+ "'has_nosend_data' INTEGER DEFAULT '0' /*是否有未下发的图片 0 没有 1 有*/,"
			+ "'has_nofeedback_data' INTEGER DEFAULT '0' /*是否有未反馈的图片 0 没有 1 有*/,"
			+ "PRIMARY KEY ('esl_id')," + "UNIQUE ('mac_id')" + ")";

	public static final String ESLS_ESL_DISP_HIS = "CREATE TABLE 'esls_esl_disp_his' ("
			+ "'disp_id' INTEGER PRIMARY KEY AUTOINCREMENT /*显示内容id*/,"
			+ "'mac_id' TEXT(50) DEFAULT NULL /*所属ESL的MAC*/,"
			+ "'width' INTEGER DEFAULT NULL /*宽*/,"
			+ "'height' INTEGER DEFAULT NULL /*高*/,"
			+ "'data' BLOB /*更新数据（二进制数据流）*/,"
			+ "'goods_id' INTEGER DEFAULT NULL /*商品id（备用）*/,"
			+ "'bar_code' TEXT(50) DEFAULT NULL /*商品（条码）编号*/,"
			+ "'pos_name' TEXT(50) DEFAULT NULL /*pos系统商品名*/,"
			+ "'esl_name' TEXT(50) DEFAULT NULL /*ESL系统商品名*/,"
			+ "'kind_id' TEXT(50) DEFAULT NULL /*所属分类id（备用）*/,"
			+ "'kind_name' TEXT(50) DEFAULT NULL /*所属分类名*/,"
			+ "'orig_price' REAL(15,2) DEFAULT NULL /*原价*/,"
			+ "'pres_price' REAL(15,2) DEFAULT NULL /*现价*/,"
			+ "'op_time' TEXT DEFAULT NULL /*操作（显示成功）时间*/,"
			+ "'up_time' TEXT DEFAULT NULL /*数据上传成功时间*/,"
			+ "'remarks' TEXT(50) DEFAULT NULL /*备注*/" + ") ";

	public static final String ESLS_ESL_MODEL = "CREATE TABLE 'esls_esl_model' ("
			+ "'model_id' INTEGER PRIMARY KEY AUTOINCREMENT /*型号ID*/,"
			+ "'model_name' VARCHAR(50) DEFAULT NULL /*规格型号（出厂）*/,"
			+ "'model_note' VARCHAR(40) DEFAULT NULL /*型号特征*/,"
			+ "'inch' REAL(10,2) DEFAULT NULL /*屏幕尺寸（英寸）*/,"
			+ "'esl_width' INTEGER DEFAULT NULL /*宽（像素）*/,"
			+ "'esl_height' INTEGER DEFAULT NULL /*高（像素）*/,"
			+ "'rotate' INTEGER DEFAULT NULL /*翻转角度*/,"
			+ "'bpp' tinyint(4) DEFAULT NULL /*每像素所占位（灰阶）*/,"
			+ "'bpp_red' INTEGER DEFAULT NULL /*红色bpp*/,"
			+ "'bpp_green' INTEGER DEFAULT NULL /*绿色bpp*/,"
			+ "'bpp_blue' INTEGER DEFAULT NULL /*蓝色bpp*/,"
			+ "'remarks' VARCHAR(200) DEFAULT NULL /*备注*/" + ") ";

	public static final String ESLS_ESL_STATE_KIND = "CREATE TABLE 'esls_esl_state_kind' ("
			+ "'state_kind_id' VARCHAR(50) NOT NULL DEFAULT '' /*状态编码id*/,"
			+ "'state_descr' VARCHAR(50) DEFAULT NULL /*状态描述*/,"
			+ "'remarks' VARCHAR(50) DEFAULT NULL /*备注*/" + ")";

	public static final String ESLS_ESL_STATE_LOG = "CREATE TABLE 'esls_esl_state_log' ("
			+ "'state_id' VARCHAR(50) NOT NULL DEFAULT '' /*状态id*/,"
			+ "'mac_id' VARCHAR(50) DEFAULT NULL /*对应ESL的MAC*/,"
			+ "'state_kind_id' VARCHAR(50) DEFAULT NULL /*状态编码*/,"
			+ "'state_time' VARCHAR(50) DEFAULT NULL /*状态返回时间*/,"
			+ "'remarks' VARCHAR(50) DEFAULT NULL /*备注*/,"
			+ "PRIMARY KEY ('state_id')" + ")";

	public static final String ESLS_ESL_WORKLOG = "CREATE TABLE 'esls_esl_worklog' ("
			+ "'log_id' INTEGER PRIMARY KEY  AUTOINCREMENT,"
			+ "'mac_id' VARCHAR(50) DEFAULT NULL,"
			+ "'esl_id' VARCHAR(20) DEFAULT NULL,"
			+ "'signal' INTEGER DEFAULT NULL /*通信信号强度*/,"
			+ "'work_time' TEXT DEFAULT NULL,"
			+ "'power' INTEGER DEFAULT NULL /*本次通信电池电量*/,"
			+ "'work_type' INTEGER DEFAULT NULL /*0-唤醒（request）\n1-反馈（feedback）\n2-其它（预留）*/,"
			+ "'pack_detail' BLOB /*工作详细内容*/,"
			+ "'ap_id' VARCHAR(20) DEFAULT NULL /*所用AP*/,"
			+ "'log_str' VARCHAR(500) DEFAULT NULL /*log字符串*/" + ") ";

	public static final String ESLS_GOODS = "CREATE TABLE 'esls_goods' ("
			+ "'goods_id' INTEGER PRIMARY KEY AUTOINCREMENT /*商品id*/,"
			+ "'bar_code' VARCHAR(100) NOT NULL /*商品（条码）编号（同pos系统）*/,"
			+ "'pos_name' VARCHAR(100) DEFAULT NULL /*商品名（同pos系统）*/,"
			+ "'esl_name' VARCHAR(100) DEFAULT NULL /*显示名（ESL系统）*/,"
			+ "'goods_series' VARCHAR(50) DEFAULT NULL /*商品系列（同pos系统）*/,"
			+ "'goods_desc' VARCHAR(200) DEFAULT NULL /*商品描述（同pos系统）*/,"
			+ "'orig_price' REAL(15,2) NOT NULL DEFAULT '0.00' /*原价（同pos系统）*/,"
			+ "'pres_price' REAL(15,2) DEFAULT '0.00' /*现价（同pos系统）*/,"
			+ "'rate' INTEGER DEFAULT NULL /*折扣率（同pos系统）*/,"
			+ "'prod_area' VARCHAR(100) DEFAULT NULL /*产地（同pos系统）*/,"
			+ "'model' VARCHAR(50) DEFAULT NULL /*规格型号*/,"
			+ "'stock' INTEGER DEFAULT NULL /*库存:与POS同步，清点用*/,"
			+ "'salable' INTEGER  DEFAULT NULL /*上架数量*/,"
			+ "'saled' INTEGER  DEFAULT NULL /*已售数量*/,"
			+ "'remarks' VARCHAR(200) DEFAULT NULL /*备注*/,"
			+ "'upd_time' TEXT DEFAULT NULL,"
			+ "'kind_id' INTEGER  DEFAULT NULL /*所属分类*/,"
			+ "'guid' VARCHAR(40) DEFAULT NULL,"
			+ "'promote1' VARCHAR(50) DEFAULT NULL /*促销信息1*/,"
			+ "'promote2' VARCHAR(50) DEFAULT NULL /*促销信息2*/,"
			+ "'status' INTEGER  NOT NULL DEFAULT '0' /*0-正常（默认，商品信息与ESL正确对应）\n1-商品信息修改，更新显示中（该商品相关的ESL更新显示未完成，暂不能销售）*/,"
			+ "'user_modi_time' TEXT DEFAULT NULL /*MIS中用户最后一次修改的时间*/,"
			+ "'pos_modi_time' TEXT DEFAULT NULL /*初定库存，上架数量，已售数量由POS写入*/,"
			+ "'memb_price' REAL(15,2) DEFAULT NULL /*会员价*/,"
			+ "'memb_rate' INTEGER  DEFAULT NULL /*会员折扣*/,"
			+ "'tax' REAL(10,2) DEFAULT NULL /*税费*/,"
			+ "'tax_rate' INTEGER  DEFAULT NULL /*税率*/,"
			+ "'order_num' INTEGER  DEFAULT '0' /*序号*/,"
			+ "'product_date' TEXT DEFAULT NULL /*生产日期*/,"
			+ "'good_bar_code' VARCHAR(12) DEFAULT NULL /*国家统一编码*/,"
			+ "'price_unit' VARCHAR(20) DEFAULT NULL /*计价单位*/,"
			+ "'level' VARCHAR(20) DEFAULT NULL /*等级*/,"
			+ "'pm' VARCHAR(100) DEFAULT NULL /*物价员*/,"
			+ "'promote_start_time' TEXT DEFAULT NULL /*促销开始日期*/,"
			+ "'promote_end_time' TEXT DEFAULT NULL /*促销结束日期*/,"
			+ "'price_down_flag' INTEGER DEFAULT '0' /*优惠直降 点亮操作 1点亮 0不点亮*/,"
			+ "'memb_owner' INTEGER DEFAULT '0' /*会员专享 点亮操作 1点亮 0不点*/,"
			+ "UNIQUE ('bar_code')" + ") ";

	public static final String ESLS_GOOD_IMPORT = "CREATE TABLE 'esls_goods_import' ("
			+ "'goods_id' INTEGER PRIMARY KEY AUTOINCREMENT /*商品id*/,"
			+ "'bar_code' VARCHAR(100) NOT NULL /*商品（条码）编号（同pos系统）*/,"
			+ "'pos_name' VARCHAR(100) DEFAULT NULL /*商品名（同pos系统）*/,"
			+ "'esl_name' VARCHAR(100) DEFAULT NULL /*显示名（ESL系统）*/,"
			+ "'goods_series' VARCHAR(50) DEFAULT NULL /*商品系列（同pos系统）*/,"
			+ "'goods_desc' VARCHAR(200) DEFAULT NULL /*商品描述（同pos系统）*/,"
			+ "'orig_price' REAL(15,2) NOT NULL DEFAULT '0.00' /*原价（同pos系统）*/,"
			+ "'pres_price' REAL(15,2) DEFAULT '0.00' /*现价（同pos系统）*/,"
			+ "'rate' INTEGER DEFAULT NULL /*折扣率（同pos系统）*/,"
			+ "'prod_area' VARCHAR(100) DEFAULT NULL /*产地（同pos系统）*/,"
			+ "'model' VARCHAR(50) DEFAULT NULL /*规格型号*/,"
			+ "'stock' INTEGER DEFAULT NULL /*库存:与POS同步，清点用*/,"
			+ "'salable' INTEGER DEFAULT NULL /*上架数量*/,"
			+ "'saled' INTEGER DEFAULT NULL /*已售数量*/,"
			+ "'remarks' VARCHAR(200) DEFAULT NULL /*备注*/,"
			+ "'upd_time' TEXT DEFAULT NULL,"
			+ "'kind_id' VARCHAR(255) DEFAULT NULL /*所属分类*/,"
			+ "'guid' VARCHAR(40) DEFAULT NULL,"
			+ "'promote1' VARCHAR(50) DEFAULT NULL /*促销信息1*/,"
			+ "'promote2' VARCHAR(50) DEFAULT NULL /*促销信息2*/,"
			+ "'status' INTEGER NOT NULL DEFAULT '0' /*0-正常（默认，商品信息与ESL正确对应）\n1-商品信息修改，更新显示中（该商品相关的ESL更新显示未完成，暂不能销售）*/,"
			+ "'user_modi_time' TEXT DEFAULT NULL /*MIS中用户最后一次修改的时间*/,"
			+ "'pos_modi_time' TEXT DEFAULT NULL /*初定库存，上架数量，已售数量由POS写入*/,"
			+ "'memb_price' REAL(15,2) DEFAULT NULL /*会员价*/,"
			+ "'memb_rate' INTEGER DEFAULT NULL /*会员折扣*/,"
			+ "'tax' REAL(10,2) DEFAULT NULL /*税费*/,"
			+ "'tax_rate' INTEGER DEFAULT NULL /*税率*/,"
			+ "'order_num' INTEGER DEFAULT '0' /*序号*/,"
			+ "'product_date' TEXT DEFAULT NULL /*生产日期*/,"
			+ "'good_bar_code' VARCHAR(12) DEFAULT NULL /*国际统一条码*/,"
			+ "'user_id' VARCHAR(16) DEFAULT NULL /*操作用户*/,"
			+ "'handle_status' INTEGER NOT NULL DEFAULT '1' /*1 未处理,0已经处理*/,"
			+ "'import_time' TEXT NOT NULL  /*导入时间,当前时间*/" + ")";

	public static final String ESLS_GOODS_UPDATE = "CREATE TABLE 'esls_goods_update' ("
			+ "'goods_upd_id' INTEGER PRIMARY KEY AUTOINCREMENT /*商品信息修改记录id*/,"
			+ "'goods_id' INTEGER DEFAULT NULL /*商品id*/,"
			+ "'bar_code' VARCHAR(100) DEFAULT NULL /*商品（条码）编号,*/,"
			+ "'pos_name' VARCHAR(100) DEFAULT NULL /*商品名*/,"
			+ "'esl_name' VARCHAR(100) DEFAULT NULL /*显示名（ESL系统）*/,"
			+ "'goods_series' VARCHAR(50) DEFAULT NULL /*商品系列*/,"
			+ "'goods_desc' VARCHAR(200) DEFAULT NULL /*商品描述*/,"
			+ "'orig_price' REAL(15,2) DEFAULT NULL /*原价*/,"
			+ "'pres_price' REAL(15,2) DEFAULT NULL /*现价*/,"
			+ "'rate' INTEGER DEFAULT NULL /*折扣率*/,"
			+ "'prod_area' VARCHAR(50) DEFAULT NULL /*产地*/,"
			+ "'model' VARCHAR(50) DEFAULT NULL /*规格型号*/,"
			+ "'upd_time' TEXT DEFAULT NULL /*最后同步时间,与POS同步时间*/,"
			+ "'status' INTEGER DEFAULT NULL /*更新块生成情况,0-未生成，1-已生成，2-作废，3 显示成功*/,"
			+ "'stock' INTEGER DEFAULT NULL /*库存*/,"
			+ "'salable' INTEGER DEFAULT NULL /*上架数量*/,"
			+ "'saled' INTEGER DEFAULT NULL /*已售数量*/,"
			+ "'reason' INTEGER DEFAULT NULL /*0-商品信息修改 1-ESL显示名修改 2-显示模板修改 3-ESL-商品关联操作*/,"
			+ "'remarks' VARCHAR(200) DEFAULT NULL /*备注*/,"
			+ "'data_flag' VARCHAR(200) DEFAULT '0' /*数据修改项对应值*/,"
			+ "'promote1' VARCHAR(50) DEFAULT NULL /*促销信息1*/,"
			+ "'promote2' VARCHAR(50) DEFAULT NULL /*促销信息2*/,"
			+ "'memb_price' REAL(15,2) DEFAULT NULL,"
			+ "'memb_rate' INTEGER DEFAULT NULL,"
			+ "'tax' REAL(10,2) DEFAULT NULL,"
			+ "'tax_rate' INTEGER DEFAULT NULL,"
			+ "'product_date' TEXT DEFAULT NULL /*生产日期*/,"
			+ "'good_bar_code' VARCHAR(12) DEFAULT NULL /*国际统一条码*/,"
			+ "'level' VARCHAR(20) DEFAULT NULL /*等级*/,"
			+ "'pm' VARCHAR(100) DEFAULT NULL /*物价员*/,"
			+ "'promote_start_time' TEXT DEFAULT NULL /*促销开始日期*/,"
			+ "'promote_end_time' TEXT DEFAULT NULL /*促销结束日期*/,"
			+ "'price_down_flag' INTEGER DEFAULT '0' /*优惠直降 点亮操作 1点亮 0不点亮*/,"
			+ "'memb_owner' INTEGER DEFAULT '0' /*会员专享 点亮操作 1点亮 0不点*/,"
			+ "'price_unit' VARCHAR(20) DEFAULT NULL /*计价单位*/" + ")";

	public static final String ESLS_GUIDE_PRICE = "CREATE TABLE 'esls_guide_price' ("
			+ "'guide_id' INTEGER  PRIMARY KEY AUTOINCREMENT,"
			+ "'kind_id' INTEGER DEFAULT NULL,"
			+ "'guide_price' REAL(15,2) DEFAULT NULL,"
			+ "'descri' VARCHAR(200) DEFAULT NULL,"
			+ "'upd_time' TEXT DEFAULT NULL,"
			+ "'type' INTEGER DEFAULT NULL,"
			+ "'guide_state' INTEGER DEFAULT NULL,"
			+ "'remarks' VARCHAR(200) DEFAULT NULL" + ") ";

	public static final String ESLS_KINDS = "CREATE TABLE 'esls_kinds' ("
			+ "'kind_id' INTEGER PRIMARY KEY AUTOINCREMENT /*分类id*/,"
			+ "'kind_name' VARCHAR(50) DEFAULT NULL /*分类名*/,"
			+ "'father_id' INTEGER DEFAULT NULL /*父分类id*/,"
			+ "'remarks' VARCHAR(200) DEFAULT NULL /*备注*/,"
			+ "'order_num' INTEGER DEFAULT '0' /*排序*/"
			+ ") /*顶级分类 小于1000  其他分类从1000开始 1000内自定义*/";

	public static final String ESLS_PATTERN = "CREATE TABLE 'esls_patterns' ("
			+ "'pattern_id' INTEGER PRIMARY KEY AUTOINCREMENT,"
			+ "'pattern_name' VARCHAR(50) DEFAULT NULL,"
			+ "'pattern_width' INTEGER DEFAULT NULL,"
			+ "'pattern_height' INTEGER DEFAULT NULL,"
			+ "'inch' REAL(10,2) DEFAULT NULL," + "'bpp' INTEGER DEFAULT NULL,"
			+ "'model_id' INTEGER DEFAULT NULL,"
			+ "'model' VARCHAR(50) DEFAULT NULL," + "'code' TEXT,"
			+ "'image_url' VARCHAR(50) DEFAULT NULL," + "'image_examp' BLOB,"
			+ "'readme' VARCHAR(200) DEFAULT NULL,"
			+ "'remarks' VARCHAR(200) DEFAULT NULL,"
			+ "'order_num' INTEGER DEFAULT '0'" + ")";

	public static final String ESLS_SHELFS = "CREATE TABLE 'esls_shelfs' ("
			+ "'shelf_id' INTEGER PRIMARY KEY AUTOINCREMENT /*货架id*/,"
			+ "'area_id' INTEGER NOT NULL /*所属分区*/,"
			+ "'ap_id' INTEGER DEFAULT NULL /*所属AP*/,"
			+ "'layer' INTEGER NOT NULL /*层数*/,"
			+ "'rank' INTEGER NOT NULL /*列数*/,"
			+ "'shelf_name' VARCHAR(50) DEFAULT NULL /*货架名称,如“水果-进口水果”*/,"
			+ "'shelf_code' VARCHAR(10) DEFAULT NULL /*货架编号（3位自定义）,用于生成位置编码*/,"
			+ "'readme' VARCHAR(200) DEFAULT NULL /*货架说明*/,"
			+ "'remarks' VARCHAR(200) DEFAULT NULL /*备注*/" + ")";

	public static final String ESLS_SYS_CONFIG = "CREATE TABLE 'esls_sys_config' ("
			+ "'id' INTEGER PRIMARY KEY AUTOINCREMENT,"
			+ "'ctype' VARCHAR(255) DEFAULT NULL,"
			+ "'ckey' VARCHAR(100) DEFAULT NULL /*值*/,"
			+ "'cvalue' VARCHAR(255) DEFAULT NULL /*vaule*/,"
			+ "'note' VARCHAR(255) DEFAULT NULL" + ")";

	public static final String ESLS_USERS = "CREATE TABLE 'esls_users' ("
			+ "'user_id' INTEGER PRIMARY KEY AUTOINCREMENT,"
			+ "'guid' VARCHAR(40) NOT NULL DEFAULT '',"
			+ "'username' VARCHAR(50) DEFAULT NULL,"
			+ "'pw' VARCHAR(32) DEFAULT NULL," + "'role' INTEGER DEFAULT NULL"
			+ ")";

	public static final String MARKET_MODULE = "CREATE TABLE 'market_module' ("
			+ "'id' INTEGER PRIMARY KEY AUTOINCREMENT,"
			+ "'name' VARCHAR(60) NOT NULL /*名称 e文*/,"
			+ "'name_comment' VARCHAR(255) NOT NULL /*名称解释*/,"
			+ "'parent' INTEGER NOT NULL DEFAULT '0' /*父*/,"
			+ "'order' INTEGER  NOT NULL DEFAULT '0' /*排序*/,"
			+ "'status' INTEGER NOT NULL DEFAULT '1' /*1 有效 0无效*/" + ")";

	public static final String MARKET_MODULE_URL = "CREATE TABLE 'market_module_url' ("
			+ "'id' INTEGER PRIMARY KEY AUTOINCREMENT,"
			+ "'module' VARCHAR(30) NOT NULL /*模块*/,"
			+ "'url' VARCHAR(255) NOT NULL /*地址*/,"
			+ "'url_name' VARCHAR(255) NOT NULL /*名称解释*/,"
			+ "'is_menu' INTEGER NOT NULL DEFAULT '0' /*1,菜单 0不是菜单*/,"
			+ "'is_check' INTEGER NOT NULL DEFAULT '1' /*1,检查，0免检查*/,"
			+ "'status' INTEGER NOT NULL DEFAULT '1' /*1有效，0无效*/,"
			+ "'order' INTEGER DEFAULT '0' /*asc*/,"
			+ "UNIQUE ('module','url')" + ") ";

	public MyDatabaseHelper(Context context, String name,
			CursorFactory factory, int version) {
		super(context, name, factory, version);
		mContext = context;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(ESLS_AP);
		db.execSQL(ESLS_CONFIG);
		db.execSQL(ESLS_DEVICE);
		db.execSQL(ESLS_ESL);
		db.execSQL(ESLS_ESL_DISP_HIS);
		db.execSQL(ESLS_ESL_MODEL);
		db.execSQL(ESLS_ESL_STATE_KIND);
		db.execSQL(ESLS_ESL_STATE_LOG);
		db.execSQL(ESLS_ESL_WORKLOG);
		db.execSQL(ESLS_GOOD_IMPORT);
		db.execSQL(ESLS_GOODS_UPDATE);
		db.execSQL(ESLS_GOODS);
		db.execSQL(ESLS_GUIDE_PRICE);
		db.execSQL(ESLS_KINDS);
		db.execSQL(ESLS_PATTERN);
		db.execSQL(ESLS_SHELFS);
		db.execSQL(ESLS_SYS_CONFIG);
		db.execSQL(ESLS_USERS);
		db.execSQL(MARKET_MODULE);
		db.execSQL(MARKET_MODULE_URL);
		Toast.makeText(mContext, "Create succeeded", Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}

}
