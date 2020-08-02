package com.topideal.supplychain.ocp.vip.dto;

import java.util.List;

public class VipRequestDto {

	//唯品请求参数
    private String requestData;

	public String getRequestData() {
		return requestData;
	}

	public void setRequestData(String requestData) {
		this.requestData = requestData;
	}

	/**
	 * 回抛请求
	 */
	public static class VipFeedbackReqDto {

		//调用者身份
		private String appKey;

		//订单号列表
		private List<String> orderSnList;

		public String getAppKey() {
			return appKey;
		}

		public void setAppKey(String appKey) {
			this.appKey = appKey;
		}

		public List<String> getOrderSnList() {
			return orderSnList;
		}

		public void setOrderSnList(List<String> orderSnList) {
			this.orderSnList = orderSnList;
		}

	}

	/**
	 * 抓单请求
	 */
	public static class VipGrabReqDto {

		//调用者身份
		private String appKey ;

		//海关编号
		private String customsCode;

		//进出口类型 001（BC）；003（BBC备货）；008（个人物品）；099	（出口）
		private String tradeMode;

		//请求条数 不传则默认100条。count最大值为500
		private Integer count;

		public String getAppKey() {
			return appKey;
		}

		public void setAppKey(String appKey) {
			this.appKey = appKey;
		}

		public String getCustomsCode() {
			return customsCode;
		}

		public void setCustomsCode(String customsCode) {
			this.customsCode = customsCode;
		}

		public String getTradeMode() {
			return tradeMode;
		}

		public void setTradeMode(String tradeMode) {
			this.tradeMode = tradeMode;
		}

		public Integer getCount() {
			return count;
		}

		public void setCount(Integer count) {
			this.count = count;
		}
	}
}
