package com.topideal.supplychain.ocp.common;

public enum SourceEnum {

	OFC("OFC", "订单履约系统"),
	OFC_BC("OFC-BC", "OFCBC"),
	OCP("OCP", "接单系统"),
	ESD("ESD", "速运系统"),
	OP("OP","OP管理系统"),
	YOUZAN("YOUZAN","有赞"),
	PDD("PDD","拼多多"),
	VIP("VIP","唯品"),
	KJB("KJB","跨境宝"),
	GEMINI("GEMINI","税金分离"),
	GLOBAL("GLOBAL", "全球仓系统"),
	MDM("MDM","主数据"),
	YMATOU("YMATOU","洋码头"),
	BAOMA("BAOMA","宝妈时光"),
	EXERP("EXERP", "其他外部系统"),
	DALING("DALING", "达令家"),
	HIPAC("HIPAC", "海拍客"),
	BIG("BIG", "大订单"),
	FX("FX", "分销订单"),
	XIAOMI("XIAOMI", "小米"),
	AMWAY("AMWAY", "安利"),
	GS("GS", "环球捕手"),
	JD("JD", "京东"),
	BEIBEI("BEIBEI", "贝贝"),
	ESDOCP("ESDOCP","其他外部系统"),
	DXY("DXY","丁香医生"),
	EW("EW","第E仓")

	;

	private String code;

	private String value;

	SourceEnum(String code, String value) {
		this.setCode(code);
		this.setValue(value);
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
