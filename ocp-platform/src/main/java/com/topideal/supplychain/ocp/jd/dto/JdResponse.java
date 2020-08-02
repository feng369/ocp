package com.topideal.supplychain.ocp.jd.dto;

import java.io.Serializable;

/**
 * @ClassName JdResponse
 * @TODO TODO
 * @Author zhangzhihao
 * @DATE 2020/2/26 15:45
 * @Version 1.0
 **/
public class JdResponse implements Serializable {

    //批量自营非自营
    private JdCustomsResponse jingdong_guangzhou_customs_queryOrderByParam_responce;
    //批量云霄购
    private JdYxResponse jingdong_YunXiaoServiceProviderService_queryOrderByParam_responce;
    //批量独立站多渠道
    private JdDlzResponse jingdong_dlz_guangzhou_customs_queryPeriodOrder_responce;
    //单票自营非自营
    private JdCustomsResponse jingdong_guangzhou_customs_queryOrderByOrderId_responce;
    //单票云霄购
    private JdYxResponse jingdong_YunXiaoServiceProviderService_queryOrderByOrderId_responce;
    //单票独立站
    private JdDlzResponse jingdong_dlz_guangzhou_customs_queryOrderByOrderId_responce;

    public JdCustomsResponse getJingdong_guangzhou_customs_queryOrderByParam_responce() {
        return jingdong_guangzhou_customs_queryOrderByParam_responce;
    }

    public void setJingdong_guangzhou_customs_queryOrderByParam_responce(
            JdCustomsResponse jingdong_guangzhou_customs_queryOrderByParam_responce) {
        this.jingdong_guangzhou_customs_queryOrderByParam_responce = jingdong_guangzhou_customs_queryOrderByParam_responce;
    }

    public JdYxResponse getJingdong_YunXiaoServiceProviderService_queryOrderByParam_responce() {
        return jingdong_YunXiaoServiceProviderService_queryOrderByParam_responce;
    }

    public void setJingdong_YunXiaoServiceProviderService_queryOrderByParam_responce(
            JdYxResponse jingdong_YunXiaoServiceProviderService_queryOrderByParam_responce) {
        this.jingdong_YunXiaoServiceProviderService_queryOrderByParam_responce = jingdong_YunXiaoServiceProviderService_queryOrderByParam_responce;
    }

    public JdDlzResponse getJingdong_dlz_guangzhou_customs_queryPeriodOrder_responce() {
        return jingdong_dlz_guangzhou_customs_queryPeriodOrder_responce;
    }

    public void setJingdong_dlz_guangzhou_customs_queryPeriodOrder_responce(
            JdDlzResponse jingdong_dlz_guangzhou_customs_queryPeriodOrder_responce) {
        this.jingdong_dlz_guangzhou_customs_queryPeriodOrder_responce = jingdong_dlz_guangzhou_customs_queryPeriodOrder_responce;
    }

    public JdCustomsResponse getJingdong_guangzhou_customs_queryOrderByOrderId_responce() {
        return jingdong_guangzhou_customs_queryOrderByOrderId_responce;
    }

    public void setJingdong_guangzhou_customs_queryOrderByOrderId_responce(
            JdCustomsResponse jingdong_guangzhou_customs_queryOrderByOrderId_responce) {
        this.jingdong_guangzhou_customs_queryOrderByOrderId_responce = jingdong_guangzhou_customs_queryOrderByOrderId_responce;
    }

    public JdYxResponse getJingdong_YunXiaoServiceProviderService_queryOrderByOrderId_responce() {
        return jingdong_YunXiaoServiceProviderService_queryOrderByOrderId_responce;
    }

    public void setJingdong_YunXiaoServiceProviderService_queryOrderByOrderId_responce(
            JdYxResponse jingdong_YunXiaoServiceProviderService_queryOrderByOrderId_responce) {
        this.jingdong_YunXiaoServiceProviderService_queryOrderByOrderId_responce = jingdong_YunXiaoServiceProviderService_queryOrderByOrderId_responce;
    }

    public JdDlzResponse getJingdong_dlz_guangzhou_customs_queryOrderByOrderId_responce() {
        return jingdong_dlz_guangzhou_customs_queryOrderByOrderId_responce;
    }

    public void setJingdong_dlz_guangzhou_customs_queryOrderByOrderId_responce(
            JdDlzResponse jingdong_dlz_guangzhou_customs_queryOrderByOrderId_responce) {
        this.jingdong_dlz_guangzhou_customs_queryOrderByOrderId_responce = jingdong_dlz_guangzhou_customs_queryOrderByOrderId_responce;
    }
}


