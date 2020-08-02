package com.topideal.supplychain.ocp.config.mapper;


import com.topideal.supplychain.ocp.config.model.FxConfig;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface FxConfigMapper {
    int deleteByPrimaryKey(Long id);

    int insert(FxConfig record);

    int update(FxConfig record);

    List<FxConfig> pageList(FxConfig filter);

    List<FxConfig> selectAll();

    List<FxConfig> selectAllEnabled();

    void edit(FxConfig config);

    FxConfig selectById(Long id);

    void updateByIds(FxConfig updateEntity);

    void delete(@Param("ids") String ids);

    FxConfig findByUnique(@Param("electricCode") String electricCode,
            @Param("platformCode") String platformCode,
            @Param("logisticsCode") String logisticsCode, @Param("customsCode") String customsCode,
            @Param("businessMode") String businessMode);

}