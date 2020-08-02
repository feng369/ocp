package com.topideal.supplychain.ocp.master.service.impl;

import com.topideal.supplychain.ocp.master.mapper.GoodsInfoMapper;
import com.topideal.supplychain.ocp.master.model.GoodsInfo;
import com.topideal.supplychain.ocp.master.service.GoodsInfoService;
import com.topideal.supplychain.security.authorization.Authentication;
import com.topideal.supplychain.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 商品信息登记
 * @author syq
 * @date 2019-12-04 15:25
 */
@Service
public class GoodsInfoServiceImpl implements GoodsInfoService {

    @Autowired
    private GoodsInfoMapper goodsInfoMapper;

    @Override
    public List<GoodsInfo> pageList(GoodsInfo goodsInfo) {
        return goodsInfoMapper.pageList(goodsInfo);
    }

    @Override
    public GoodsInfo selectById(Long id) {
        return goodsInfoMapper.selectById(id);
    }

    /**
     * 根据货主编码，商品编码，条形码，操作类型状态查询
     * @param ownerCode
     * @param goodsNo
     * @param barCode
     * @param actionType
     * @return
     */
    @Override
    public List<GoodsInfo> selectGoodsInfo(String ownerCode, String goodsNo, String barCode, String actionType) {
        return goodsInfoMapper.selectGoodsInfo(ownerCode, goodsNo, barCode, actionType);
    }

    @Override
    @Transactional
    public void update(GoodsInfo goodsInfo) {
        goodsInfo.setUpdateBy(Authentication.getUserId());
        goodsInfo.setUpdateTime(DateUtils.getNowTime());
        goodsInfoMapper.update(goodsInfo);
    }

    /**
     * 根据货主编码和商品编码查询
     * @param ownerCode
     * @param goodsNo
     * @return
     */
    @Override
    public List<GoodsInfo> selectByOwnerAndGoodsNo(String ownerCode, String goodsNo) {
        return goodsInfoMapper.selectByOwnerAndGoodsNo(ownerCode, goodsNo);
    }
}
