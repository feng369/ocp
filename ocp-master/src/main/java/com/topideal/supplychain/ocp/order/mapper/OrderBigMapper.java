package com.topideal.supplychain.ocp.order.mapper;

import com.topideal.supplychain.dict.DataDictAction;
import com.topideal.supplychain.ocp.order.dto.OrderBigPageRequestDto;
import com.topideal.supplychain.ocp.order.model.OrderBig;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 标题：大订单Mapper
 * 模块：ocp-parent
 * 版权: Copyright © 2019 topideal
 * 公司: 广东卓志供应链科技有限公司武汉分公司
 * 类路径：com.topideal.supplychain.ocp.order.mapper
 * 作者：songping
 * 创建日期：2019/12/16 14:02
 *
 * @version 1.0
 */

@Mapper
public interface OrderBigMapper {

    OrderBig selectByPrimaryKey(Long id);

    OrderBig selectByCode(String code);

    String selectSensitiveData(@Param("propertyName") String propertyName, @Param("id") Long id);

    @DataDictAction
    List<OrderBig> pageList(OrderBigPageRequestDto filter);

    List<OrderBig> selectByIds(@Param("ids") List<Long> ids);

    int insert(OrderBig record);

    void update(OrderBig updateEntity);

    int delete(Long id);

    OrderBig selectByConditon(@Param("electricCode") String electricCode, @Param("cbepcomCode") String cbepcomCode, @Param("busiMode") String busiMode, @Param("orderId") String orderId);
}
