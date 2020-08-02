package com.topideal.supplychain.ocp.order.model;

import com.topideal.supplychain.common.model.BaseEntity;
import com.topideal.supplychain.ocp.enums.*;
import com.topideal.supplychain.util.JacksonUtils;
import org.apache.commons.lang3.StringUtils;

import java.beans.Transient;
import java.util.Date;

/**
 * 达令家订单model
 *
 * @author xuxiaoyan
 * @date 2019-11-29 10:54
 */
public class OrderDaling extends BaseEntity {

    /**
     * ocp内部订单号
     */
    private String code;

    /**
     * 企业订单编号，对应接口报文order_no
     **/
    private String orderNo;
    /**
     * 运单号  对应接口报文logistics_number
     **/
    private String logisticsNumber;
    /**
     * 企业订单状态 对应接口报文status,1-有效 2-无效
     **/
    private DaLingOrderStatusEnum status;

    /**
     * 转发系统
     */
    private ForwardSystemEnum sendSystem;
    /**
     * 系统自动生成，默认10制单；分别有：10 制单；20 已抓取详细；60 下发失败；70 下发成功
     **/
    private OrderStatusEnum orderStatus;
    /**
     * 订单生成时间，对应接口报文created_date
     **/
    private Date createdDate;

    /**
     * 订单详细抓取状态 10 已抓取 20-未抓取
     **/
    private Integer infoStatus;
    /**
     * 订单抓取定时任务id
     **/
    private String taskId;
    /**
     * 第三方物流商编码,根据抓单定时任务默认值配置设置
     **/
    private String tpl;
    /**
     * 电商企业编码，根据抓单定时任务默认值配置设置
     **/
    private String electricCode;
    /**
     * 电商平台编码，根据抓单定时任务默认值配置设置
     **/
    private String cbePcomCode;
    /**
     * 进口模式，根据抓单定时任务默认值配置设置
     **/
    private BusiModeEnum busiMode;
    /**
     * 申报关区，根据抓单定时任务默认值配置设置
     **/
    private CustomsCodeEnum customsCode;
    /**
     * 申报国检，根据抓单定时任务默认值配置设置
     **/
    private String ciqbCode;
    /**
     * 推op失败原因
     **/
    private String opReason;
    /**
     * 订单详细信息报文 (数据库是json格式)
     **/
    private String infoData;

    /**
     * JSON报文对应的封装类
     **/
    private OrderDetailDaling orderDetail;

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getLogisticsNumber() {
        return logisticsNumber;
    }

    public void setLogisticsNumber(String logisticsNumber) {
        this.logisticsNumber = logisticsNumber;
    }

    public DaLingOrderStatusEnum getStatus() {
        return status;
    }

    public void setStatus(DaLingOrderStatusEnum status) {
        this.status = status;
    }

    public OrderStatusEnum getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatusEnum orderStatus) {
        this.orderStatus = orderStatus;
    }

    public ForwardSystemEnum getSendSystem() {
        return sendSystem;
    }

    public void setSendSystem(ForwardSystemEnum sendSystem) {
        this.sendSystem = sendSystem;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Integer getInfoStatus() {
        return infoStatus;
    }

    public void setInfoStatus(Integer infoStatus) {
        this.infoStatus = infoStatus;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getTpl() {
        return tpl;
    }

    public void setTpl(String tpl) {
        this.tpl = tpl;
    }

    public String getElectricCode() {
        return electricCode;
    }

    public void setElectricCode(String electricCode) {
        this.electricCode = electricCode;
    }

    public String getCbePcomCode() {
        return cbePcomCode;
    }

    public void setCbePcomCode(String cbePcomCode) {
        this.cbePcomCode = cbePcomCode;
    }

    public BusiModeEnum getBusiMode() {
        return busiMode;
    }

    public void setBusiMode(BusiModeEnum busiMode) {
        this.busiMode = busiMode;
    }

    public CustomsCodeEnum getCustomsCode() {
        return customsCode;
    }

    public void setCustomsCode(CustomsCodeEnum customsCode) {
        this.customsCode = customsCode;
    }

    public String getCiqbCode() {
        return ciqbCode;
    }

    public void setCiqbCode(String ciqbCode) {
        this.ciqbCode = ciqbCode;
    }

    public String getOpReason() {
        return opReason;
    }

    public void setOpReason(String opReason) {
        this.opReason = opReason;
    }

    public String getInfoData() {
        return infoData;
    }

    public void setInfoData(String infoData) {
        this.infoData = infoData;
    }

    @Transient
    public OrderDetailDaling getOrderDetail() {
        if (null == orderDetail && StringUtils.isNotEmpty(infoData)) {
            orderDetail = JacksonUtils
                    .readValue(infoData, OrderDetailDaling.class);
        }
        return null == orderDetail ? new OrderDetailDaling() : orderDetail;
    }

    public void setOrderDetail(OrderDetailDaling orderDetail) {
        this.orderDetail = orderDetail;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
