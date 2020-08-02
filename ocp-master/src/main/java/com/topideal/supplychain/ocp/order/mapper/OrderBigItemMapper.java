package com.topideal.supplychain.ocp.order.mapper;

import com.topideal.supplychain.ocp.order.model.OrderBigItem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 标题：
 * 模块：ocp-parent
 * 版权: Copyright © 2019 topideal
 * 公司: 广东卓志供应链科技有限公司武汉分公司
 * 类路径：com.topideal.supplychain.ocp.order.mapper
 * 作者：songping
 * 创建日期：2019/12/19 16:32
 *
 * @version 1.0
 */
@Mapper
public interface OrderBigItemMapper {

    int insert(OrderBigItem record);

    int insertBatch(@Param("goods") List<OrderBigItem> goods);

    OrderBigItem selectByPrimaryKey(Long id);

    List<OrderBigItem> selectByMainId(Long mainId);

    List<OrderBigItem> selectByOrderId(String orderId);

    int update(OrderBigItem record);

    int delete(Long id);
}
