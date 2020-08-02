package com.topideal.supplychain.ocp.config.service.impl;

import com.topideal.supplychain.enumeration.EnableOrDisableEnum;
import com.topideal.supplychain.ocp.config.mapper.CatchOrderConfigMapper;
import com.topideal.supplychain.ocp.config.model.CatchOrderConfig;
import com.topideal.supplychain.ocp.config.service.CatchOrderConfigService;
import com.topideal.supplychain.ocp.constants.CodeRuleConstants;
import com.topideal.supplychain.ocp.master.model.Platform;
import com.topideal.supplychain.security.authorization.Authentication;
import com.topideal.supplychain.util.DateUtils;
import com.topideal.supplychain.web.system.service.impl.CodeGeneratorService;
import io.micrometer.core.instrument.Meter.Id;

import java.util.Date;
import java.util.List;
import java.util.stream.DoubleStream;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

/**
 * 抓单配置
 * @author shengyunqi
 * @date 2019-11-20 16:35
 */
@Service
public class CatchOrderConfigServiceImpl implements CatchOrderConfigService {

    @Autowired
    private CatchOrderConfigMapper catchOrderConfigMapper;
    @Autowired
    private CodeGeneratorService codeGeneratorService;

    /**
     * 分页查询
     * @param catchOrderConfig
     * @return
     */
    @Override
    public List<CatchOrderConfig> pageList(CatchOrderConfig catchOrderConfig) {
        return catchOrderConfigMapper.pageList(catchOrderConfig);
    }

    /**
     * 启用
     * @param ids
     */
    @Transactional
    @Override
    public void enable(Long[] ids) {
        catchOrderConfigMapper.enable(ids, EnableOrDisableEnum.ENABLE.getValue());
    }

    /**
     * 禁用
     * @param ids
     */
    @Transactional
    @Override
    public void disable(Long[] ids) {
        catchOrderConfigMapper.disable(ids, EnableOrDisableEnum.DISABLE.getValue());

    }

    @Override
    public CatchOrderConfig selectEnableById(Long id) {
        return catchOrderConfigMapper.selectEnableById(id);
    }

    @Override
    public CatchOrderConfig selectById(Long id) {
        return catchOrderConfigMapper.selectById(id);
    }

    @Override
    @Transactional
    public void edit(CatchOrderConfig config) {
        config.setUpdateBy(Authentication.getUserId());
        config.setUpdateTime(new Date());
        catchOrderConfigMapper.edit(config);
    }

    @Override
    @Transactional
    public void save(CatchOrderConfig config) {
        if (StringUtils.isEmpty(config.getCode())) {
            String code = codeGeneratorService.generate(CodeRuleConstants.RULE_CFG);
            config.setCode(code);
        }
        config.setCreateBy(Authentication.getUserId());
        config.setCreateTime(new Date());
        config.setUpdateBy(Authentication.getUserId());
        config.setUpdateTime(new Date());
        catchOrderConfigMapper.insert(config);
    }

    @Override
    public CatchOrderConfig selectByCode(String code) {
        return catchOrderConfigMapper.selectByCode(code);
    }

    @Override
    @Transactional
    public void update(CatchOrderConfig config) {
        config.setUpdateBy(Authentication.getUserId());
        config.setUpdateTime(new Date());
        catchOrderConfigMapper.update(config);
    }

    @Override
    public List<CatchOrderConfig> selectByPlatform(List<Platform> platforms) {
        return catchOrderConfigMapper.selectByPlatform(platforms);
    }

    @Transactional
    @Override
    public int updateQueryTime(Date queryTime,Long id) {
        return catchOrderConfigMapper.updateQueryTime(queryTime,id);

    }

    @Override
    public List<CatchOrderConfig> selectByGrabKey(String grabKey) {
        return catchOrderConfigMapper.selectByGrabKey(grabKey);
    }

    @Override
    public CatchOrderConfig selectByPlatformCode(String code) {
        List<CatchOrderConfig> catchOrderConfigs = catchOrderConfigMapper
                .selectByPlatformCode(code);
        if (CollectionUtils.isEmpty(catchOrderConfigs)) {
            return null;
        }
        return catchOrderConfigs.get(0);
    }
}
