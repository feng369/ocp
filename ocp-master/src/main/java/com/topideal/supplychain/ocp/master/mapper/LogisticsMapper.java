package com.topideal.supplychain.ocp.master.mapper;

import com.topideal.supplychain.ocp.master.model.Logistics;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 物流企业
 * @author xuxiaoyan
 * @date 2019-11-13 19:26
 */
@Mapper
public interface LogisticsMapper {

    /**
     * 分页查询
     * @param logistics
     * @return
     */
    List<Logistics> pageList(Logistics logistics);

    /**
     * 批量修改状态
     * @param id
     * @param status
     */
    void updateStatus(@Param("id") Long id, @Param("status") Integer status);

    /**
     * 查询所有
     * @return
     */
    List<Logistics> findAll();
}
