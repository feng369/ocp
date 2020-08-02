package com.topideal.supplychain.ocp.order.mapper;

import com.topideal.supplychain.ocp.order.model.OrderDistributionItem;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 分销订单明细
 *
 * @author xuxiaoyan
 * @date 2020-05-21 15:18
 */
@Mapper
public interface OrderDistributionItemMapper {

    int deleteById(Long id);

    int insert(OrderDistributionItem record);

    OrderDistributionItem selectById(Long id);

    int update(OrderDistributionItem record);

    void insertBatch(@Param("goods") List<OrderDistributionItem> goods);

    List<OrderDistributionItem> selectByOrder(Long headId);
}
