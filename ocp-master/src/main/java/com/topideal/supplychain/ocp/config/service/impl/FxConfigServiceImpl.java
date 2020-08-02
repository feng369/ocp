package com.topideal.supplychain.ocp.config.service.impl;

import com.topideal.supplychain.enumeration.EnableOrDisableEnum;
import com.topideal.supplychain.ocp.config.mapper.FxConfigMapper;
import com.topideal.supplychain.ocp.config.model.FxConfig;
import com.topideal.supplychain.ocp.config.service.FxConfigService;
import com.topideal.supplychain.ocp.constants.CodeRuleConstants;
import com.topideal.supplychain.ocp.enums.LogisticsEnum;
import com.topideal.supplychain.ocp.enums.MerchantEnum;
import com.topideal.supplychain.security.authorization.Authentication;
import com.topideal.supplychain.web.system.service.impl.CodeGeneratorService;
import io.micrometer.core.lang.NonNull;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>标题: 转单配置实现</p>
 * <p>模块: ocp-parent</p>
 * <p>版权: Copyright © 2019 topideal</p>
 * <p>公司: 广东卓志供应链科技有限公司武汉分公司</p>
 * <p>类路径: com.topideal.supplychain.ocp.service.impl</p>
 * <p>作者: LeoZhang</p>
 * <p>创建日期: 2019/11/22 13:41</p>
 *
 * @version 1.0
 */
@Service
public class FxConfigServiceImpl implements FxConfigService {

    @Autowired
    private FxConfigMapper mapper;
    @Autowired
    private CodeGeneratorService codeGeneratorService;

    @Override
    public List<FxConfig> pageList(FxConfig filter) {
        return mapper.pageList(filter);
    }

    @Override
    public List<FxConfig> selectAll() {
        return mapper.selectAll();
    }

    /**
     * 保存转单配置
     * 1.需要按照页面参数设值优先级
     * 如果物流企业和电商编码存在通配符，优先级最高。依次类推
     * @param config
     */
    @Override
    @Transactional
    @Caching(evict = {@CacheEvict(value = "fxConfig", allEntries = true)})
    public void save(FxConfig config) {
        //解析优先级
        this.insert(config);
    }

    /**
     * 更新配置信息
     * @param config
     */
    @Override
    @Transactional
    @Caching(evict = {@CacheEvict(value = "fxConfig", allEntries = true)})
    public void edit(FxConfig config) {
        //解析优先级
        config.setUpdateBy(Authentication.getUserId());
        config.setUpdateTime(new Date());
        mapper.edit(config);
    }

    /**
     * 启用配置
     * @param ids
     */
    @Override
    @Transactional
    @Caching(evict = {@CacheEvict(value = "fxConfig", allEntries = true)})
    public void enable(String ids) {
        FxConfig updateEntity = new FxConfig();
        updateEntity.setIds(ids);
        updateEntity.setStatus(EnableOrDisableEnum.ENABLE);
        this.updateByIds(updateEntity);
    }

    /**
     * 禁用配置
     * @param ids
     */
    @Override
    @Transactional
    @Caching(evict = {@CacheEvict(value = "fxConfig", allEntries = true)})
    public void disable(String ids) {
        FxConfig updateEntity = new FxConfig();
        updateEntity.setIds(ids);
        updateEntity.setStatus(EnableOrDisableEnum.DISABLE);
        this.updateByIds(updateEntity);
    }

    @Override
    @Transactional
    @Caching(evict = {@CacheEvict(value = "fxConfig", allEntries = true)})
    public void update(FxConfig updateEntity) {
        updateEntity.setCreateBy(Authentication.getUserId());
        updateEntity.setCreateTime(new Date());
        mapper.update(updateEntity);
    }

    @Override
    @Transactional
    @Caching(evict = {@CacheEvict(value = "fxConfig", allEntries = true)})
    public void insert(FxConfig config) {
        if (StringUtils.isEmpty(config.getCode())) {
            String code = codeGeneratorService.generate(CodeRuleConstants.RULE_CFG);
            config.setCode(code);
        }
        config.setCreateBy(Authentication.getUserId());
        config.setCreateTime(new Date());
        config.setUpdateBy(Authentication.getUserId());
        config.setUpdateTime(new Date());
        mapper.insert(config);
    }

    @Override
    public FxConfig selectById(Long id) {
        return mapper.selectById(id);
    }

    @Override
    @Transactional
    @Caching(evict = {@CacheEvict(value = "fxConfig", allEntries = true)})
    public void updateByIds(FxConfig updateEntity) {
        updateEntity.setCreateBy(Authentication.getUserId());
        updateEntity.setCreateTime(new Date());
        mapper.updateByIds(updateEntity);
    }

    @Override
    @Transactional
    @Caching(evict = {@CacheEvict(value = "fxConfig", allEntries = true)})
    public void delete(String ids) {
        mapper.delete(ids);
    }

    @Override
    @Cacheable(value = "fxConfig", key = "#electricCode.concat('-').concat(#platformCode).concat('-').concat(#logisticsCode).concat('-').concat(#customsCode).concat('-').concat(#businessMode)", unless="#result == null")
    public FxConfig findByUnique(@NonNull String electricCode, @NonNull String platformCode, @NonNull String logisticsCode, @NonNull String customsCode, @NonNull String businessMode) {
        FxConfig config = mapper.findByUnique(electricCode, platformCode, logisticsCode, customsCode, businessMode);
        if (config == null) {
            config = mapper.findByUnique(MerchantEnum.VM.getCode(),platformCode, logisticsCode, customsCode, businessMode);
        }
        if (config == null) {
            config = mapper.findByUnique(electricCode, platformCode, LogisticsEnum.VL.getCode(), customsCode, businessMode);
        }
        if (config == null) {
            config = mapper.findByUnique(MerchantEnum.VM.getCode() ,platformCode, LogisticsEnum.VL.getCode(), customsCode, businessMode);
        }
        return config;
    }

    /**
     * 清空缓存
     */
    @Override
    @Caching(evict = {@CacheEvict(value = "fxConfig", allEntries = true)})
    public void clearCache() {

    }
}
