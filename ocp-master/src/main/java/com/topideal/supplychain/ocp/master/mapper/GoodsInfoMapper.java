package com.topideal.supplychain.ocp.master.mapper;

import com.topideal.supplychain.ocp.config.model.CatchOrderConfig;
import com.topideal.supplychain.ocp.master.model.GoodsInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * 商品信息登记
 * @author syq
 * @date 2019-12-04 15:26
 */
@Mapper
public interface GoodsInfoMapper {

    /**
     * 分页查询
     * @param goodsInfo
     * @return
     */
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
    List<GoodsInfo> selectGoodsInfo(@Param("ownerCode") String ownerCode, @Param("goodsNo") String goodsNo,
                                    @Param("barCode") String barCode, @Param("actionType") String actionType);

    void update(GoodsInfo goodsInfo);

    /**
     * 根据货主编码和商品编码查询
     * @param ownerCode
     * @param goodsNo
     * @return
     */
    List<GoodsInfo> selectByOwnerAndGoodsNo(@Param("ownerCode") String ownerCode, @Param("goodsNo") String goodsNo);
}
