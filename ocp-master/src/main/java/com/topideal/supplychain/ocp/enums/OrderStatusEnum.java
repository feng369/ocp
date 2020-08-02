package com.topideal.supplychain.ocp.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import com.topideal.supplychain.enumeration.StringEnum;

/**
 * @Author: Hu Yong
 * @Description: 订单状态枚举
 * @Date: Created In 2019/4/10 11:18
 * @Modified By:
 */
public enum  OrderStatusEnum implements StringEnum {
	INIT("10","制单"),
	GRAB_INFO("20","已抓取详细"),
	BACK_FAILURE("40","回抛失败"),
	BACK_SUCCESS("50","回抛成功"),
	KJB_FAILURE("70","组套接口失败"),
	KJB_SUCCESS("80","组套接口成功"),
	GEMINI_FAILURE("100","税价分离失败"),
	GEMINI_SUCCESS("110","税价分离成功"),
	SEND_FAILURE("130","下发失败"),
	SEND_SUCCESS("140","下发成功"),
	;
	
	private String value;
	private String desc;
	
	OrderStatusEnum(String value, String desc) {
		this.value = value;
		this.desc = desc;
	}
	
	@Override
	public String getValue() {
		return value;
	}

	@Override
	@JsonValue
	public String getDesc() {
		return desc;
	}

	public static OrderStatusEnum getByValue(String value){
		for(OrderStatusEnum item:OrderStatusEnum.values()){
			if(item.getValue().equals(value)){
				return item;
			}
		}
		return null;
	}
}
