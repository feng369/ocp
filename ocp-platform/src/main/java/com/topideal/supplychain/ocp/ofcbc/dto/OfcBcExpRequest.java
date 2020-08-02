package com.topideal.supplychain.ocp.ofcbc.dto;

import java.util.List;

/**
 * 标题：OFC-EXPBC转单请求
 * 模块：ocp-parent
 * 版权: Copyright © 2019 topideal
 * 公司: 广东卓志供应链科技有限公司武汉分公司
 * 类路径：com.topideal.supplychain.ocp.ofc.dto
 * 作者：songping
 * 创建日期：2020/1/9 17:16
 *
 * @version 1.0
 */
public class OfcBcExpRequest {

    private OfcBcExpOrderReqDto ofcBcExpOrderReqDto;
    private List<OfcBcExpGoodsReqDto> ofcBcExpGoodsReqDtoList;

    public OfcBcExpRequest(OfcBcExpOrderReqDto ofcBcExpOrderReqDto, List<OfcBcExpGoodsReqDto> ofcBcExpGoodsReqDtoList) {
        this.ofcBcExpOrderReqDto = ofcBcExpOrderReqDto;
        this.ofcBcExpGoodsReqDtoList = ofcBcExpGoodsReqDtoList;
    }

    public OfcBcExpOrderReqDto getOfcBcExpOrderReqDto() {
        return ofcBcExpOrderReqDto;
    }

    public void setOfcBcExpOrderReqDto(OfcBcExpOrderReqDto ofcBcExpOrderReqDto) {
        this.ofcBcExpOrderReqDto = ofcBcExpOrderReqDto;
    }

    public List<OfcBcExpGoodsReqDto> getOfcBcExpGoodsReqDtoList() {
        return ofcBcExpGoodsReqDtoList;
    }

    public void setOfcBcExpGoodsReqDtoList(List<OfcBcExpGoodsReqDto> ofcBcExpGoodsReqDtoList) {
        this.ofcBcExpGoodsReqDtoList = ofcBcExpGoodsReqDtoList;
    }
}
