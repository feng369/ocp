package com.topideal.supplychain.ocp.config.service;


import com.topideal.supplychain.ocp.config.model.CatchOrderConfig;
import com.topideal.supplychain.ocp.master.model.Platform;
import java.util.Date;
import java.util.List;

/**
 * 商品业主
 * @author shengyunqi
 * @date 2019-11-19 16:35
 */
public interface CatchOrderConfigService {

    List<CatchOrderConfig> pageList(CatchOrderConfig catchOrderConfig);

    void enable(Long[] ids);

    void disable(Long[] ids);

    List<CatchOrderConfig> selectByPlatform(List<Platform> platforms);

    CatchOrderConfig selectEnableById(Long businessId);

    CatchOrderConfig selectById(Long id);

    void edit(CatchOrderConfig config);

    void save(CatchOrderConfig config);

    CatchOrderConfig selectByCode(String code);

    void update(CatchOrderConfig config);

    int updateQueryTime(Date queryTime,Long Id);

    List<CatchOrderConfig> selectByGrabKey(String grabKey);

    CatchOrderConfig selectByPlatformCode(String code);
}
