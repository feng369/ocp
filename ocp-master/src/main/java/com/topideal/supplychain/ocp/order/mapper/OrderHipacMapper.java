package com.topideal.supplychain.ocp.order.mapper;

import com.topideal.supplychain.ocp.order.dto.OrderHipacDto;
import com.topideal.supplychain.ocp.order.model.OrderHipac;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 海拍客订单
 *
 * @author xuxiaoyan
 * @date 2019-12-16 15:21
 */
@Mapper
public interface OrderHipacMapper {

    /**
     * 查询
     * @param filter
     * @return
     */
    List<OrderHipacDto> pageList(OrderHipacDto filter);

    OrderHipac selectById(Long id);

    String selectSensitiveData(@Param("propertyName") String propertyName, @Param("orderId") long orderId);

    void insert(OrderHipac orderHipac);

    List<OrderHipac> selectByIds(@Param("ids") List<Long> ids);

    void updateOrderStatus(@Param("id") Long id, @Param("status") String status, @Param("reason") String msg);
}
