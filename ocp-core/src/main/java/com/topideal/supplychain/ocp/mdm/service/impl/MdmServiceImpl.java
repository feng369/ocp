package com.topideal.supplychain.ocp.mdm.service.impl;

import com.topideal.supplychain.common.model.BaseResponse;
import com.topideal.supplychain.ocp.common.ExpCodeEnum;
import com.topideal.supplychain.ocp.common.SourceEnum;
import com.topideal.supplychain.ocp.enums.ActionTypeEnum;
import com.topideal.supplychain.ocp.enums.SyncStatusEnum;
import com.topideal.supplychain.ocp.master.model.GoodsInfo;
import com.topideal.supplychain.ocp.master.service.GoodsInfoService;
import com.topideal.supplychain.ocp.mdm.dto.MdmGoodsReqDto;
import com.topideal.supplychain.ocp.mdm.dto.MdmSyncRequest;
import com.topideal.supplychain.ocp.mdm.dto.MdmRequestHead;
import com.topideal.supplychain.ocp.mdm.dto.MdmSyncResponse;
import com.topideal.supplychain.ocp.mdm.service.MdmApiService;
import com.topideal.supplychain.ocp.mdm.service.MdmService;
import com.topideal.supplychain.util.DateUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * @description: 对接主数据
 * @author: syq
 * @create: 2019-12-09 14:50
 **/
@Service
public class MdmServiceImpl implements MdmService {

    @Autowired
    private GoodsInfoService goodsInfoService;
    @Autowired
    private MdmApiService mdmApiService;

    /**
     * 同步商品信息
     * 1.组装报文
     * 2.调主数据同步商品信息接口
     * 3.若接口成功则更新商品信息的同步状态为已同步，若商品信息的操作类型为新增，则将接口回执信息中的opg号更新
     * 4.若接口失败则更新商品信息的同步状态为同步失败，mq重试
     * @param goodsInfo
     * @return
     */
    @Override
    @Transactional
    public BaseResponse syncGoodsInfo(GoodsInfo goodsInfo) {
        //组装请求报文
        MdmSyncRequest request = buildReq(goodsInfo);
        //调主数据同步商品信息接口
        BaseResponse<MdmSyncResponse> baseResponse = mdmApiService.syncGoodsInfo(request, goodsInfo.getCode());
        //如果同步成功，更新商品信息状态为已同步
        //如果同步失败，更新商品状态为同步失败
        GoodsInfo update = new GoodsInfo();
        update.setId(goodsInfo.getId());
        if (baseResponse.isSuccess()) {
            update.setStatus(SyncStatusEnum.HAVE_SYNC);
            //若商品信息的操作类型为新增，则将接口回执信息中的opg号更新
            if (goodsInfo.getActionType().equals(ActionTypeEnum.ADD.getDesc())) {
                update.setOpgCode(baseResponse.getData().getSyncResultInfo().get(0).getOpgcode());
            } else {
                update.setOpgCode(request.getMdmGoodsReqDtoList().get(0).getOpgcode());
            }
            goodsInfoService.update(update);
        } else {
            update.setStatus(SyncStatusEnum.SYNC_FAIL);
            goodsInfoService.update(goodsInfo);
        }
        return baseResponse;
    }

    /**
     * 构建请求
     * @param goodsInfo
     * @return
     */
    private MdmSyncRequest buildReq(GoodsInfo goodsInfo) {

        MdmSyncRequest request = new MdmSyncRequest();
        request.setSource(SourceEnum.OCP.getCode());
        request.setVersion("1.0");
        //构建请求头
        MdmRequestHead mdmRequestHead = buildRequestHead(goodsInfo.getOpgCode());

        List<MdmGoodsReqDto> mdmGoodsReqDtoList = new ArrayList<>();
        MdmGoodsReqDto goodsReqDto = new MdmGoodsReqDto();
        //同步类型
        if (goodsInfo.getActionType().equals(ActionTypeEnum.ADD.getDesc())) {
            request.setSyncType(ActionTypeEnum.ADD.getValue());
        } else {
            //若同步类型不为新增时，opg号为必填
            //根据货主编码、商品编码、条形码、操作编码查询，若同步状态为已同步，则取该条记录的opg号
            request.setSyncType(ActionTypeEnum.UPDATE.getValue());
            List<GoodsInfo> goodsInfoList = goodsInfoService.selectGoodsInfo(goodsInfo.getOwnerCode(), goodsInfo.getGoodsNo(),
                    goodsInfo.getBarCode(), ActionTypeEnum.ADD.getDesc());
            if (CollectionUtils.isNotEmpty(goodsInfoList) && SyncStatusEnum.HAVE_SYNC.getValue().equals(goodsInfoList.get(0).getStatus().getValue())) {
                goodsReqDto.setOpgcode(goodsInfoList.get(0).getOpgCode());
            }
        }
        goodsReqDto.setBarcode(goodsInfo.getBarCode()); //条形码
        goodsReqDto.setLength(goodsInfo.getLength() == null ? null : goodsInfo.getLength().multiply(new BigDecimal(10))); //长
        goodsReqDto.setWidth(goodsInfo.getWidth() == null ? null : goodsInfo.getWidth().multiply(new BigDecimal(10))); //宽
        goodsReqDto.setHeight(goodsInfo.getHeight() == null ? null : goodsInfo.getHeight().multiply(new BigDecimal(10))); //高
        goodsReqDto.setVolume(goodsInfo.getVolume() == null ? null : goodsInfo.getVolume().multiply(new BigDecimal(1000000))); //体积
        goodsReqDto.setKgs(goodsInfo.getGrossWeight() == null ? null : goodsInfo.getGrossWeight().multiply(new BigDecimal(1000))); //毛重
        goodsReqDto.setNet(goodsInfo.getNetWeight() == null ? null : goodsInfo.getNetWeight().multiply(new BigDecimal(1000))); //净重
        goodsReqDto.setLifeDays(goodsInfo.getShelfLife() == null ? null : Long.valueOf(goodsInfo.getShelfLife()/24)); //保质期天数
        goodsReqDto.setUseLifeFlag(goodsInfo.getIsShelfLife().getValue()); //是否需要保质期管理
        goodsReqDto.setQtyUnit(goodsInfo.getUnit()); //计量单位
        goodsReqDto.setBrand(goodsInfo.getBrandName()); //品牌名称
        goodsReqDto.setOriginRegion(goodsInfo.getOriginAddress()); //商品原产地
        goodsReqDto.setColor(goodsInfo.getColor()); //颜色
        goodsReqDto.setPrdSize(goodsInfo.getSize()); //尺码
        goodsReqDto.setFragileFlag(goodsInfo.getIsFragile().getValue()); //是否易碎品
        goodsReqDto.setDangerousFlag(goodsInfo.getIsDangerous().getValue()); //是否危险品
        goodsReqDto.setPrdRemark(goodsInfo.getRemark()); //备注
        goodsReqDto.setGcode(goodsInfo.getGoodsNo()); //商品编码
        goodsReqDto.setgName(goodsInfo.getGoodsName()); //商品名称
        goodsReqDto.setCcode(goodsInfo.getOwnerCode()); //货主编码
        goodsReqDto.setGoodsbarcode(goodsInfo.getBarCode()); //商品条形码

        mdmGoodsReqDtoList.add(goodsReqDto);

        request.setMessageHead(mdmRequestHead);
        request.setMdmGoodsReqDtoList(mdmGoodsReqDtoList);
        return request;
    }

    private MdmRequestHead buildRequestHead(String traceId) {
        MdmRequestHead mdmRequestHead = new MdmRequestHead();
        String messageID = ExpCodeEnum.MDM107.getCode() + SourceEnum.OCP.getCode() +
                DateUtils.dateToString(new Date(), DateUtils.YMDHMS) + String.valueOf((int)(Math.random()*9000)+1000);
        mdmRequestHead.setMessageID(messageID);
        mdmRequestHead.setMessageType(ExpCodeEnum.MDM107.getCode());
        mdmRequestHead.setTraceId(traceId);
        mdmRequestHead.setSenderID(SourceEnum.OCP.getCode());
        mdmRequestHead.setReceiverID(SourceEnum.MDM.getCode());
        mdmRequestHead.setSendTime(DateUtils.dateToString(new Date(), DateUtils.DATETIME_PATTERN));
        mdmRequestHead.setVersion("1.0");
        return mdmRequestHead;
    }
}
