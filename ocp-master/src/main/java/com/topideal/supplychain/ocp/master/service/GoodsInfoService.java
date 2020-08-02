package com.topideal.supplychain.ocp.master.service;


import com.topideal.supplychain.ocp.master.model.GoodsInfo;

import java.util.List;

/**
 * 商品信息登记
 * @author syq
 * @date 2019-12-04 15:25
 */
public interface GoodsInfoService {

    List<GoodsInfo> pageList(GoodsInfo goodsInfo);

    GoodsInfo selectById(Long id);

    /**
     * 根据货主编码，商品编码，条形码，操作类型状态查询
     * @param ownerCode
     * @param goodsNo
     * @param barCode
     * @param actionType
     * @return
     */
    List<GoodsInfo> selectGoodsInfo(String ownerCode, String goodsNo, String barCode, String actionType);

    /**
     * 根据货主编码和商品编码查询
     * @param owner
     * @param goodsNo
     * @return
     */
    List<GoodsInfo> selectByOwnerAndGoodsNo(String owner, String goodsNo);

    void update(GoodsInfo goodsInfo);
}
