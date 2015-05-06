package com.cxt.esl.good.domain;

import java.io.Serializable;

import android.os.Parcel;
import android.os.Parcelable;


public class Good implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3200816997494620276L;
	
	private  int goods_id;// int(11) NOT NULL AUTO_INCREMENT COMMENT '商品id',
	private  String bar_code;// varchar(100) NOT NULL COMMENT '商品（条码）编号（同pos系统）',
	private  String pos_name;// varchar(100) DEFAULT NULL COMMENT '商品名（同pos系统）',
	private  String esl_name;// varchar(100) DEFAULT NULL COMMENT '显示名（ESL系统）',
	private  String goods_series;// varchar(50) DEFAULT NULL COMMENT '商品系列（同pos系统）',
	private  String goods_desc;// varchar(200) DEFAULT NULL COMMENT '商品描述（同pos系统）',
	private  float orig_price;// decimal(15,2) NOT NULL DEFAULT '0.00' COMMENT '原价（同pos系统）',
	private  float pres_price;// decimal(15,2) DEFAULT '0.00' COMMENT '现价（同pos系统）',
	private  int rate;// tinyint(4) DEFAULT NULL COMMENT '折扣率（同pos系统）',
	private  String prod_area;// varchar(100) DEFAULT NULL COMMENT '产地（同pos系统）',
	private  String model;// varchar(50) DEFAULT NULL COMMENT '规格型号',
	private  stock bigint(20) DEFAULT NULL COMMENT '库存:与POS同步，清点用',
	private salable int(11) DEFAULT NULL COMMENT '上架数量',
	private  saled bigint(20) DEFAULT NULL COMMENT '已售数量',
	private  remarks varchar(200) DEFAULT NULL COMMENT '备注',
	private  upd_time datetime DEFAULT NULL,
	private  kind_id int(11) DEFAULT NULL COMMENT '所属分类',
	private  guid varchar(40) DEFAULT NULL,
	private  promote1 varchar(50) DEFAULT NULL COMMENT '促销信息1',
	private  promote2 varchar(50) DEFAULT NULL COMMENT '促销信息2',
	private  status tinyint(4) NOT NULL DEFAULT '0' COMMENT '"0-正常（默认，商品信息与ESL正确对应）\n1-商品信息修改，更新显示中（该商品相关的ESL更新显示未完成，暂不能销售）"',
	private  user_modi_time datetime DEFAULT NULL COMMENT 'MIS中用户最后一次修改的时间',
	private  pos_modi_time datetime DEFAULT NULL COMMENT '初定库存，上架数量，已售数量由POS写入',
	private  memb_price decimal(15,2) DEFAULT NULL COMMENT '会员价',
	private  memb_rate tinyint(4) DEFAULT NULL COMMENT '会员折扣',
	private  tax decimal(10,2) DEFAULT NULL COMMENT '税费',
	private  tax_rate tinyint(4) DEFAULT NULL COMMENT '税率',
	private  order_num int(16) DEFAULT '0' COMMENT '序号',
	private  product_date datetime DEFAULT NULL COMMENT '生产日期',
	private  good_bar_code char(12) DEFAULT NULL COMMENT '国家统一编码',
	private  price_unit varchar(20) DEFAULT NULL COMMENT '计价单位',
	private  level varchar(20) DEFAULT NULL COMMENT '等级',
	private  pm varchar(100) DEFAULT NULL COMMENT '物价员',
	private  promote_start_time datetime DEFAULT NULL COMMENT '促销开始日期',
	private  promote_end_time datetime DEFAULT NULL COMMENT '促销结束日期',
	private  price_down_flag int(2) DEFAULT '0' COMMENT '优惠直降 点亮操作 1点亮 0不点亮',
	private  memb_owner int(2) DEFAULT '0' COMMENT '会员专享 点亮操作 1点亮 0不点',
	
	
}