package com.topideal.supplychain.ocp.master.mapper;

import com.topideal.supplychain.ocp.master.model.GoodsCustomerConfig;
import java.util.Date;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 商品业主
 *
 * @author xuxiaoyan
 * @date 2019-11-14 16:57
 */
@Mapper
public interface GoodsCustomerMapper {

    /**
     * 分页查询
     * @param goodsCustomerConfig
     * @return
     */
    List<GoodsCustomerConfig> pageList(GoodsCustomerConfig goodsCustomerConfig);

    void insert(GoodsCustomerConfig goodsCustomerConfig);

    GoodsCustomerConfig selectById(Long id);

    void update(GoodsCustomerConfig goodsCustomerConfig);

    void batchRemove(@Param("idList") List<String> idList, @Param("userId") Long userId,
            @Param("nowTime") Date nowTime);

    List<GoodsCustomerConfig> selectByFilter(GoodsCustomerConfig goodsCustomerConfig);

    void batchInsert(@Param("datas") List<GoodsCustomerConfig> datas, @Param("userId") Long userId,
            @Param("nowTime") Date nowTime);

}
