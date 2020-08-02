package com.topideal.supplychain.ocp.order.mapper;

import com.topideal.supplychain.dict.DataDictAction;
import com.topideal.supplychain.ocp.order.dto.OrderDistributionPageRequestDto;
import com.topideal.supplychain.ocp.order.model.OrderDistribution;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 分销订单
 *
 * @author xuxiaoyan
 * @date 2020-05-21 14:33
 */
@Mapper
public interface OrderDistributionMapper {

    int deleteById(Long id);

    int insert(OrderDistribution record);

    OrderDistribution selectById(Long id);

    int update(OrderDistribution record);

    @DataDictAction
    List<OrderDistribution> pageList(OrderDistributionPageRequestDto filter);

    List<OrderDistribution> selectByIds(@Param("ids") List<Long> ids);

    OrderDistribution selectByConditon(@Param("electricCode") String electricCode, @Param("cbepcomCode") String cbepcomCode, @Param("busiMode") String busiMode, @Param("orderId") String orderId);

    void updateOrderStatus(@Param("id")Long id, @Param("status")String status, @Param("reason")String msg);
}
