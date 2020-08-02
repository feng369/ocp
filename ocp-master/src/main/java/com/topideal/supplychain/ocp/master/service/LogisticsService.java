package com.topideal.supplychain.ocp.master.service;

import com.topideal.supplychain.ocp.master.model.Logistics;
import java.util.List;

/**
 * 物流企业
 * @author xuxiaoyan
 * @date 2019-11-13 19:25
 */
public interface LogisticsService {

    List<Logistics> pageList(Logistics logistics);

    void updateStatus(Long id, Integer value);

    List<Logistics> findAll();
}
