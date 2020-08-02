package com.topideal.supplychain.ocp.config.service.impl;

import com.topideal.supplychain.enumeration.EnableOrDisableEnum;
import com.topideal.supplychain.ocp.config.mapper.TransferConfigMapper;
import com.topideal.supplychain.ocp.config.model.TransferConfig;
import com.topideal.supplychain.ocp.config.service.TransferConfigService;
import com.topideal.supplychain.ocp.constants.CodeRuleConstants;
import com.topideal.supplychain.ocp.constants.PunctuateConstant;
import com.topideal.supplychain.ocp.enums.LogisticsEnum;
import com.topideal.supplychain.ocp.enums.MerchantEnum;
import com.topideal.supplychain.security.authorization.Authentication;
import com.topideal.supplychain.web.system.service.impl.CodeGeneratorService;
import io.micrometer.core.lang.NonNull;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
public class TransferConfigServiceImpl implements TransferConfigService {

    @Autowired
    private TransferConfigMapper mapper;
    @Autowired
    private CodeGeneratorService codeGeneratorService;

    @Override
    public List<TransferConfig> pageList(TransferConfig filter) {
        return mapper.pageList(filter);
    }

    @Override
    public List<TransferConfig> selectAll() {
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
    @Caching(evict = {@CacheEvict(value = "transferConfig", allEntries = true)})
    public void save(TransferConfig config) {
        //解析优先级
        parsePriority(config);
        this.insert(config);
    }

    /**
     * 更新配置信息
     * @param config
     */
    @Override
    @Transactional
    @Caching(evict = {@CacheEvict(value = "transferConfig", allEntries = true)})
    public void edit(TransferConfig config) {
        //解析优先级
        parsePriority(config);
        config.setUpdateBy(Authentication.getUserId());
        config.setUpdateTime(new Date());
        mapper.edit(config);
    }

    /**
     * 解析优先级
     * @param config
     */
    private void parsePriority(TransferConfig config) {
        if (PunctuateConstant.PERCENT.equals(config.getLogisticsCode()) && PunctuateConstant.PERCENT.equals(config.getElectricCode())) {
            config.setPriority(0);
        } else if (PunctuateConstant.PERCENT.equals(config.getElectricCode())) {
            config.setPriority(1);
        } else if (PunctuateConstant.PERCENT.equals(config.getLogisticsCode())) {
            config.setPriority(2);
        } else {
            config.setPriority(3);
        }
    }

    /**
     * 启用配置
     * @param ids
     */
    @Override
    @Transactional
    @Caching(evict = {@CacheEvict(value = "transferConfig", allEntries = true)})
    public void enable(String ids) {
        TransferConfig updateEntity = new TransferConfig();
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
    @Caching(evict = {@CacheEvict(value = "transferConfig", allEntries = true)})
    public void disable(String ids) {
        TransferConfig updateEntity = new TransferConfig();
        updateEntity.setIds(ids);
        updateEntity.setStatus(EnableOrDisableEnum.DISABLE);
        this.updateByIds(updateEntity);
    }

    @Override
    @Transactional
    @Caching(evict = {@CacheEvict(value = "transferConfig", allEntries = true)})
    public void update(TransferConfig updateEntity) {
        updateEntity.setCreateBy(Authentication.getUserId());
        updateEntity.setCreateTime(new Date());
        mapper.update(updateEntity);
    }

    @Override
    @Transactional
    @Caching(evict = {@CacheEvict(value = "transferConfig", allEntries = true)})
    public void insert(TransferConfig config) {
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
    public TransferConfig selectById(Long id) {
        return mapper.selectById(id);
    }

    @Override
    @Transactional
    @Caching(evict = {@CacheEvict(value = "transferConfig", allEntries = true)})
    public void updateByIds(TransferConfig updateEntity) {
        updateEntity.setCreateBy(Authentication.getUserId());
        updateEntity.setCreateTime(new Date());
        mapper.updateByIds(updateEntity);
    }

    @Override
    @Transactional
    @Caching(evict = {@CacheEvict(value = "transferConfig", allEntries = true)})
    public void delete(String ids) {
        mapper.delete(ids);
    }

    @Override
    @Cacheable(value = "transferConfig", key = "#electricCode.concat('-').concat(#platformCode).concat('-').concat(#logisticsCode).concat('-').concat(#customsCode).concat('-').concat(#businessMode)", unless="#result == null")
    public TransferConfig findByUnique(@NonNull String electricCode, @NonNull String platformCode, @NonNull String logisticsCode, @NonNull String customsCode, @NonNull String businessMode) {
        TransferConfig config = mapper.findByUnique(electricCode, platformCode, logisticsCode, customsCode, businessMode);
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

    @Override
    public TransferConfig getBigTransferConfig(@NonNull String ebpCode, @NonNull String ebcCode, String logisticsCode, @NonNull String customsCode, @NonNull String busiMode)
    {
        List<TransferConfig> defs = selectEnabled();
        if (CollectionUtils.isEmpty(defs))
        {
            return null;
        }
        Map<String, TransferConfig> map = new HashMap<>();
        for (TransferConfig def : defs)
        {
            String key = def.getPlatformCode() + "_"
                    + def.getElectricCode() + "_"
                    + def.getCustomsCode().getValue() + "_"
                    + def.getBusinessMode().getValue() + "_"
                    + def.getLogisticsCode();
            map.put(key, def);
        }
        String key = ebpCode + "_" + ebcCode + "_" + customsCode + "_" + busiMode + "_" + logisticsCode;
        TransferConfig def = map.get(key);
        if(StringUtils.isBlank(logisticsCode) || null == def) {
            key = ebpCode + "_" + ebcCode + "_" + customsCode + "_" + busiMode + "_%";
            def = map.get(key);
        }
        return def;
    }

    @Override
    @Cacheable(value = "transferConfig", key = "enabledTransferConfig", unless="#result == null")
    public List<TransferConfig> selectEnabled(){
        return mapper.selectAllEnabled();
    }

    /**
     * 清空缓存
     */
    @Override
    @Caching(evict = {@CacheEvict(value = "transferConfig", allEntries = true)})
    public void clearCache() {

    }
}
