package com.topideal.supplychain.ocp.master.service;

import com.topideal.supplychain.ocp.master.model.Platform;
import java.util.List;

/**
 * @author hhl
 * @date 2018/12/27
 */
public interface PlatformService {

    List<Platform> pageList(Platform platform);

    int add(Platform platform);

    Platform findByCode(String code);

    List<Platform> findByVirtualCode(String code);

    Platform findById(Long id);

    int update(Platform platform);

    List<Platform> findAll();

    void enable(Long[] ids);

    void disable(Long[] ids);

    List<Platform> autoCompletion(String q);
}
