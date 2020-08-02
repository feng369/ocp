package com.topideal.supplychain.ocp.ofc.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.util.List;

/**
 * @ClassName RequestDto
 * @TODO TODO
 * @Author zhangzhihao
 * @DATE 2019/12/5 17:13
 * @Version 1.0
 **/
@JsonInclude(Include.NON_NULL)
public class OfcRequest {

    private OfcOrderReqDto ofcOrderReqDto;

    private List<OfcGoodsReqDto> ofcGoodsReqDtos;

    public OfcRequest(OfcOrderReqDto ofcOrderReqDto,
            List<OfcGoodsReqDto> ofcGoodsReqDtos) {
        this.ofcOrderReqDto = ofcOrderReqDto;
        this.ofcGoodsReqDtos = ofcGoodsReqDtos;
    }

    public OfcOrderReqDto getOfcOrderReqDto() {
        return ofcOrderReqDto;
    }

    public void setOfcOrderReqDto(OfcOrderReqDto ofcOrderReqDto) {
        this.ofcOrderReqDto = ofcOrderReqDto;
    }

    public List<OfcGoodsReqDto> getOfcGoodsReqDtos() {
        return ofcGoodsReqDtos;
    }

    public void setOfcGoodsReqDtos(
            List<OfcGoodsReqDto> ofcGoodsReqDtos) {
        this.ofcGoodsReqDtos = ofcGoodsReqDtos;
    }
}
