package com.topideal.supplychain.ocp.config.mapper;

import com.topideal.supplychain.ocp.config.model.CatchOrderConfig;
import com.topideal.supplychain.ocp.master.model.Platform;
import java.util.Date;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
/**
 * 抓单配置
 */
@Mapper
public interface CatchOrderConfigMapper {
    int deleteById(Long id);

    int insert(CatchOrderConfig record);

    CatchOrderConfig selectById(Long id);

    CatchOrderConfig selectEnableById(Long id);

    int update(CatchOrderConfig record);

    /**
     * 分页查询
     * @param catchOrderConfig
     * @return
     */
    List<CatchOrderConfig> pageList(CatchOrderConfig catchOrderConfig);

    /**
     * 启用
     * @param ids
     * @param enable
     */
    void enable(@Param("ids")Long[] ids, @Param("enable")Integer enable);

    /**
     * 禁用
     * @param ids
     * @param disable
     */
    void disable(@Param("ids")Long[] ids, @Param("disable")Integer disable);


    List<CatchOrderConfig> selectByPlatform(@Param("platforms") List<Platform> platforms);

    void edit(CatchOrderConfig config);

    CatchOrderConfig selectByCode(String code);

    int updateQueryTime(@Param("queryTime")Date queryTime,@Param("id")Long id);

    List<CatchOrderConfig> selectByGrabKey(String grabKey);

    List<CatchOrderConfig> selectByPlatformCode(String code);
}