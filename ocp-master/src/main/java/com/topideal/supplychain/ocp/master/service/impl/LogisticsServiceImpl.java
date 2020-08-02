package com.topideal.supplychain.ocp.master.service.impl;

import com.topideal.supplychain.ocp.master.mapper.LogisticsMapper;
import com.topideal.supplychain.ocp.master.model.Logistics;
import com.topideal.supplychain.ocp.master.service.LogisticsService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 物流企业
 * @author xuxiaoyan
 * @date 2019-11-13 19:25
 */
@Service
public class LogisticsServiceImpl implements LogisticsService{

    @Autowired
    private LogisticsMapper logisticsMapper;

    @Override
    public List<Logistics> pageList(Logistics logistics) {
        return logisticsMapper.pageList(logistics);
    }

    /**
     * 批量修改状态
     * @param id
     * @param status
     */
    @Override
    @Transactional
    public void updateStatus(Long id, Integer status) {
        logisticsMapper.updateStatus(id, status);
    }

    @Override
    public List<Logistics> findAll() {
        return logisticsMapper.findAll();
    }
}
