package com.topideal.supplychain.ocp.dxy.dto;

import com.topideal.supplychain.util.JacksonUtils;
import java.io.Serializable;
import java.util.List;

/**
 * @ClassName DxyResponse
 * @TODO TODO
 * @Author zhangzhihao
 * @DATE 2020/6/30 15:53
 * @Version 1.0
 **/
public class DxyResponse implements Serializable {

    private List<DxyOrderResponse> data;

    private boolean success;

    private String message;

    private DxyPageBean pageBean;

    public List<DxyOrderResponse> getData() {
        return data;
    }

    public void setData(List<DxyOrderResponse> data) {
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DxyPageBean getPageBean() {
        return pageBean;
    }

    public void setPageBean(DxyPageBean pageBean) {
        this.pageBean = pageBean;
    }

    public static void main(String[] args) {
        String s = "{\n"
                + "    \"data\":[\n"
                + "        {\n"
                + "            \"orderId\":3409025170189847223,\n"
                + "            \"createTime\":\"2020-04-21 14:37:29\",\n"
                + "            \"realPayAmount\":3,\n"
                + "            \"discountAmount\":0,\n"
                + "            \"taxAmount\":1,\n"
                + "            \"freightAmount\":0,\n"
                + "            \"userRemark\":\"\",\n"
                + "            \"province\":\"浙江省\",\n"
                + "            \"city\":\"杭州市\",\n"
                + "            \"area\":\"滨江区\",\n"
                + "            \"address\":\"长河街道 滨安路 756 号 世包大楼 A 区 3 楼-丁香园 TC 办\",\n"
                + "            \"postCode\":\"0\",\n"
                + "            \"buyerName\":\"康康\",\n"
                + "            \"buyerIdCard\":\"110102197501101519\",\n"
                + "            \"buyerMobile\":\"13811456812\",\n"
                + "            \"receiverName\":\"康神\",\n"
                + "            \"receiverIdCard\":\"110102197501101519\",\n"
                + "            \"receiverMobile\":\"13811456812\",\n"
                + "            \"payTime\":\"2020-04-21 14:37:43\",\n"
                + "            \"paymentNo\":\"4200000541202004210981129650\",\n"
                + "            \"payType\":1,\n"
                + "            \"payCompanyCode\":\"4403169D3W\",\n"
                + "            \"orderItems\":[\n"
                + "                {\n"
                + "                    \"skuCode\":\"skfjsdkjf2\",\n"
                + "                    \"commodityName\":\"MADNESS 5-PANEL CAP\",\n"
                + "                    \"specificationName\":\"100 片拼图-公主\",\n"
                + "                    \"quantity\":1,\n"
                + "                    \"price\":2,\n"
                + "                    \"costPrice\":1,\n"
                + "                    \"amount\":2.7,\n"
                + "                    \"discountAmount\":0,\n"
                + "                    \"taxAmount\":1\n"
                + "                }\n"
                + "            ],\n"
                + "            \"platformCode\":\"330136802E\",\n"
                + "            \"platformName\":\"杭州丁香健康管理有限公司\"\n"
                + "        }\n"
                + "    ],\n"
                + "    \"success\":true,\n"
                + "    \"message\":\"\",\n"
                + "    \"pageBean\":{\n"
                + "        \"pageNo\":1,\n"
                + "        \"pageSize\":100,\n"
                + "        \"totalCount\":1\n"
                + "    }\n"
                + "}";

        DxyResponse response = JacksonUtils.readValue(s,DxyResponse.class);
        System.out.println();
    }
}
