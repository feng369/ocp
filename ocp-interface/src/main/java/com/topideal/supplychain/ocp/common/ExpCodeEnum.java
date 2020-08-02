package com.topideal.supplychain.ocp.common;

import com.topideal.supplychain.enumeration.BaseInterfaceEnum;

/**
 * 接口区别标识枚举
 * @author huyong0112 2017年9月28日 下午2:19:29
 */
public enum ExpCodeEnum implements BaseInterfaceEnum {

	OCP001("OCP001","订单推送OFC",SourceEnum.OCP,SourceEnum.OFC),
	OCP002("OCP002","订单推送OP",SourceEnum.OCP,SourceEnum.OP),
    OCP003("OCP003","订单推送ESD",SourceEnum.OCP,SourceEnum.ESD),
    OCP004("OCP004","订单推送跨境宝",SourceEnum.OCP,SourceEnum.KJB),
	OCP005("OCP005","订单推送GEMINI",SourceEnum.OCP,SourceEnum.GEMINI),
	OCP006("OCP006","订单推送KJB",SourceEnum.OCP,SourceEnum.KJB),
	OCP007("OCP007","订单推送OFC_BC",SourceEnum.OCP,SourceEnum.OFC_BC),
	OCP008("OCP008","出口订单推送OFC_BC",SourceEnum.OCP,SourceEnum.OFC_BC),
	OCP009("OCP009","订单推送ESD2.0",SourceEnum.OCP,SourceEnum.ESD),
	OCP101("OCP101","店铺信息同步接口",SourceEnum.EXERP,SourceEnum.OCP),
	OCP102("OCP102","分销订单推送跨境宝接口",SourceEnum.OCP,SourceEnum.KJB),
	OCP103("OCP103","分销订单推送第E仓",SourceEnum.OCP,SourceEnum.EW),

	MDM107("MDM107","主数据产品商品信息同步接口",SourceEnum.MDM,SourceEnum.OCP),
	MDM120("MDM120","主数据店铺信息同步接口",SourceEnum.MDM,SourceEnum.OCP),
	//MDM108("MDM108","商品信息同步接口",SourceEnum.OCP,SourceEnum.MDM),
	DALING001("DALING001","达令家抓取订单号接口",SourceEnum.OCP,SourceEnum.DALING),
	DALING002("DALING002","达令家抓取订单详细信息接口",SourceEnum.OCP,SourceEnum.DALING),
	HIPAC001("HIPAC001","海拍客接单接口",SourceEnum.HIPAC,SourceEnum.OCP),
	YOUZAN001("YOUZAN001","有赞店铺获取TOKEN",SourceEnum.EXERP,SourceEnum.YOUZAN),
	YOUZAN003("YOUZAN003","有赞订单抓单接口",SourceEnum.EXERP,SourceEnum.YOUZAN),
	YOUZAN004("YOUZAN004","有赞订单订单号抓单接口",SourceEnum.EXERP,SourceEnum.YOUZAN),
	VIP001("VIP001","唯品订单抓单接口",SourceEnum.EXERP,SourceEnum.VIP),
	VIP002("VIP002","唯品订单接单回执接口",SourceEnum.EXERP,SourceEnum.VIP),


	PDD001("PDD001","拼多多接单接口",SourceEnum.PDD,SourceEnum.OCP),
	PDD002("PDD002","拼多多抓单接口",SourceEnum.OCP,SourceEnum.PDD),
	PDD003("PDD003","拼多多单票抓单接口",SourceEnum.OCP,SourceEnum.PDD),
	//PDD004("PDD004","拼多多店铺信息同步",SourceEnum.PDD,SourceEnum.EXERP),


	YMATOU001("YMATOU001","洋码头订单抓单接口",SourceEnum.YMATOU,SourceEnum.OCP),
	YMATOU002("YMATOU002","洋码头订单接单接口",SourceEnum.OCP,SourceEnum.YMATOU),
	YMATOU003("YMATOU003","洋码头订单支付接口",SourceEnum.OCP,SourceEnum.YMATOU),
	BAOMA001("BAOMA001","宝妈时光订单抓单接口",SourceEnum.BAOMA,SourceEnum.OCP),

	GLOBAL001("GLOBAL001","全球仓订单抓单接口",SourceEnum.GLOBAL,SourceEnum.OCP),

	PUB001("PUB001","标准订单接单接口",SourceEnum.KJB,SourceEnum.OCP),

	KJB001("KJB001","跨境宝接单接口",SourceEnum.KJB,SourceEnum.OCP),

    BIG001("BIG001","大订单接单接口",SourceEnum.BIG,SourceEnum.OCP),
	FX001("FX001","分销订单接单接口",SourceEnum.FX,SourceEnum.OCP),

	XIAOMI001("XIAOMI001","小米订单抓单接口",SourceEnum.XIAOMI,SourceEnum.OCP),
	AMWAY001("AMWAY001","安利订单接单接口",SourceEnum.AMWAY,SourceEnum.OCP),

	GS101("GS101", "环球捕手抓单接口", SourceEnum.OCP,SourceEnum.GS),
	GS102("GS102", "环球捕手跨境宝拆分接口", SourceEnum.GS,SourceEnum.KJB),
	GS103("GS103", "环球捕手单票抓单接口", SourceEnum.OCP,SourceEnum.GS),

	JD001("JD001", "京东自营非自营批量抓单", SourceEnum.OCP,SourceEnum.JD),
	JD002("JD002", "京东云霄购批量抓单", SourceEnum.OCP,SourceEnum.JD),
	JD003("JD003", "京东多渠道独立站批量抓单", SourceEnum.OCP,SourceEnum.JD),
	JD004("JD004", "京东自营非自营单票抓单", SourceEnum.OCP,SourceEnum.JD),
	JD005("JD005", "京东云霄购单票抓单", SourceEnum.OCP,SourceEnum.JD),
	JD006("JD006", "京东多渠道独立站单票抓单", SourceEnum.OCP,SourceEnum.JD),

	BEIBEI001("BEIBEI001","贝贝订单抓单接口",SourceEnum.OCP,SourceEnum.BEIBEI),
	BEIBEI002("BEIBEI002","贝贝订单详情接口",SourceEnum.OCP,SourceEnum.BEIBEI),


	DXY001("DXY001","丁香医生抓单接口",SourceEnum.OCP,SourceEnum.DXY),
	;

	private String code;
    private String name;
	private SourceEnum source;
	private SourceEnum target;

	ExpCodeEnum(String code, String name) {
		this.code = code;
		this.name = name;
	}

	private ExpCodeEnum(String code, String name,SourceEnum source,SourceEnum target) {
		this.code = code;
		this.name = name;
		this.source = source;
		this.target = target;
	}


	@Override
	public String getCode() {
		return code;
	}

	@Override
	public String getName() {
		return name;
	}

	public SourceEnum getSource() {
		return source;
	}

	public SourceEnum getTarget() {
		return target;
	}

}

