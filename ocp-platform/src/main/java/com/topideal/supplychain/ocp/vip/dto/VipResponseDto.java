package com.topideal.supplychain.ocp.vip.dto;

import com.topideal.supplychain.ocp.order.model.OrderVip;

import java.util.List;

public class VipResponseDto {

	private String returnCode;

	private Result result;

	private String returnMessage;

	public String getReturnCode() {
		return returnCode;
	}

	public void setReturnCode(String returnCode) {
		this.returnCode = returnCode;
	}

	public Result getResult() {
		return result;
	}

	public void setResult(Result result) {
		this.result = result;
	}

	public String getReturnMessage() {
		return returnMessage;
	}

	public void setReturnMessage(String returnMessage) {
		this.returnMessage = returnMessage;
	}


	public static class Result {
		private String responseData;

		public String getResponseData() {
			return responseData;
		}

		public void setResponseData(String responseData) {
			this.responseData = responseData;
		}
	}

	public static class ResponseData {
		private ResponseHeadDto head;
		private List<OrderVip> mpOrderList;

		public ResponseHeadDto getHead() {
			return head;
		}

		public void setHead(ResponseHeadDto head) {
			this.head = head;
		}

		public List<OrderVip> getMpOrderList() {
			return mpOrderList;
		}

		public void setMpOrderList(List<OrderVip> mpOrderList) {
			this.mpOrderList = mpOrderList;
		}
	}

	public static class ResponseHeadDto {

		public static final String SUCCESS = "200";

		private String responseTime;

		private String code;

		private String msg;

		public String getResponseTime() {
			return responseTime;
		}

		public void setResponseTime(String responseTime) {
			this.responseTime = responseTime;
		}

		public String getCode() {
			return code;
		}

		public void setCode(String code) {
			this.code = code;
		}

		public String getMsg() {
			return msg;
		}

		public void setMsg(String msg) {
			this.msg = msg;
		}
	}


}
