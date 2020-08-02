package com.topideal.supplychain.ocp.master.mapper;

import com.topideal.supplychain.ocp.master.model.Merchant;
import com.topideal.supplychain.ocp.master.model.Platform;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author hhl
 * @date 2018/12/27
 */
@Mapper
public interface PlatformMapper {

    List<Platform> pageList(Platform platform);

    int insert(Platform platform);


    Platform findByCode(String code);

    List<Platform> findByVirtualCode(String virtualCode);

    //int forbidden(@Param("ids") String[] ids);

    Platform findById(Long id);

    int update(Platform platform);

    List<Platform> findAll();

    void enable(@Param("ids")Long[] ids, @Param("enable")Integer enable);

    void disable(@Param("ids")Long[] ids, @Param("disable")Integer disable);

    List<Platform> autoCompletion(@Param("keyword") String keyword);

}
