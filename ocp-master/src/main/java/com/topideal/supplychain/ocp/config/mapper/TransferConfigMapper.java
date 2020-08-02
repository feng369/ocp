package com.topideal.supplychain.ocp.config.mapper;


import com.topideal.supplychain.ocp.config.model.TransferConfig;
import com.topideal.supplychain.ocp.enums.BusiModeEnum;
import com.topideal.supplychain.ocp.enums.CustomsCodeEnum;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TransferConfigMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TransferConfig record);

    int update(TransferConfig record);

    List<TransferConfig> pageList(TransferConfig filter);

    List<TransferConfig> selectAll();

    List<TransferConfig> selectAllEnabled();

    void edit(TransferConfig config);

    TransferConfig selectById(Long id);

    void updateByIds(TransferConfig updateEntity);

    void delete(@Param("ids") String ids);

    TransferConfig findByUnique(@Param("electricCode") String electricCode, @Param("platformCode") String platformCode, @Param("logisticsCode") String logisticsCode, @Param("customsCode") String customsCode, @Param("businessMode") String businessMode);

}