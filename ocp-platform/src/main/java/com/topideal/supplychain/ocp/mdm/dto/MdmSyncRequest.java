package com.topideal.supplychain.ocp.mdm.dto;

import java.util.List;

/**
 * @description: 主数据同步商品信息请求
 * @author: syq
 * @create: 2019-12-09 15:13
 **/
public class MdmSyncRequest {

    private String syncType; //来源系统

    private String source; //版本号

    private String version; //同步类型1:新增,2:更新

    private List<MdmGoodsReqDto> mdmGoodsReqDtoList;

    private MdmRequestHead messageHead;

    public String getSyncType() {
        return syncType;
    }

    public void setSyncType(String syncType) {
        this.syncType = syncType;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public List<MdmGoodsReqDto> getMdmGoodsReqDtoList() {
        return mdmGoodsReqDtoList;
    }

    public void setMdmGoodsReqDtoList(List<MdmGoodsReqDto> mdmGoodsReqDtoList) {
        this.mdmGoodsReqDtoList = mdmGoodsReqDtoList;
    }

    public MdmRequestHead getMessageHead() {
        return messageHead;
    }

    public void setMessageHead(MdmRequestHead messageHead) {
        this.messageHead = messageHead;
    }
}
