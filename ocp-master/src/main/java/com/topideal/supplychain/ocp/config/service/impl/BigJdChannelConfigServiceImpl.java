package com.topideal.supplychain.ocp.config.service.impl;

import com.topideal.supplychain.enumeration.EnableOrDisableEnum;
import com.topideal.supplychain.ocp.config.dto.BigJdChannelConfigPageRequestDto;
import com.topideal.supplychain.ocp.config.mapper.BigJdChannelConfigMapper;
import com.topideal.supplychain.ocp.config.model.BigJdChannelConfig;
import com.topideal.supplychain.ocp.config.service.BigJdChannelConfigService;
import com.topideal.supplychain.ocp.constants.CodeRuleConstants;
import com.topideal.supplychain.security.authorization.Authentication;
import com.topideal.supplychain.util.DateUtils;
import com.topideal.supplychain.web.system.service.impl.CodeGeneratorService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * 标题：京东多渠道大订单配置
 * 模块：ocp-parent
 * 版权: Copyright © 2019 topideal
 * 公司: 广东卓志供应链科技有限公司武汉分公司
 * 类路径：com.topideal.supplychain.ocp.config.service.impl
 * 作者：songping
 * 创建日期：2019/12/24 15:21
 *
 * @version 1.0
 */
@Service
public class BigJdChannelConfigServiceImpl implements BigJdChannelConfigService {

    @Autowired
    private BigJdChannelConfigMapper mapper;
    @Autowired
    private CodeGeneratorService codeGeneratorService;

    @Override
    @Transactional
    public void delete(Long id) {
        mapper.delete(id);
    }

    @Override
    @Transactional
    public void deleteByIds(List<Long> ids) {
        mapper.deleteByIds(ids);
    }

    @Override
    @Transactional
    public void insert(BigJdChannelConfig record) {
        record.setCode(codeGeneratorService.generate(CodeRuleConstants.RULE_BIGJDCHANNEL_CFG));
        record.setStartFlag(EnableOrDisableEnum.DISABLE);
        record.setCreateTime(DateUtils.getNowTime());
        record.setCreateBy(Authentication.getUserId());
        mapper.insert(record);
    }

    @Override
    public BigJdChannelConfig selectById(Long id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public List<BigJdChannelConfig> pageList(BigJdChannelConfigPageRequestDto filter) {
        return mapper.pageList(filter);
    }

    @Override
    @Transactional
    public void update(BigJdChannelConfig record) {
        record.setUpdateBy(Authentication.getUserId());
        record.setUpdateTime(DateUtils.getNowTime());
        mapper.update(record);
    }

    @Override
    @Transactional
    public void updateByIds(BigJdChannelConfig record) {
        if (record == null || CollectionUtils.isEmpty(record.getIds())) {
            return;
        }
        record.setUpdateBy(Authentication.getUserId());
        record.setUpdateTime(DateUtils.getNowTime());
        mapper.updateByIds(record);
    }

    @Override
    @Transactional
    public void enable(List<Long> ids) {
        BigJdChannelConfig config = new BigJdChannelConfig();
        config.setIds(ids);
        config.setStartFlag(EnableOrDisableEnum.ENABLE);
        this.updateByIds(config);
    }

    @Override
    @Transactional
    public void disable(List<Long> ids) {
        BigJdChannelConfig config = new BigJdChannelConfig();
        config.setIds(ids);
        config.setStartFlag(EnableOrDisableEnum.DISABLE);
        this.updateByIds(config);
    }

    @Override
    public BigJdChannelConfig getBigJdChannelConfigModel(String ebpCode, String ebcCode, String logisticsCode)
    {
        if (StringUtils.isBlank(ebpCode) || StringUtils.isBlank(ebcCode) || StringUtils.isBlank(logisticsCode))
        {
            return null;
        }
        List<BigJdChannelConfig> defs = selectAllEnabled();
        BigJdChannelConfig model = null;
        if (defs != null)
        {
            for (BigJdChannelConfig def : defs)
            {
                String ebp_code = def.getEbpCode();
                String ebc_code = def.getEbcCode();
                String logistics_code = def.getLogisticsCode();
                if (ebpCode.equals(ebp_code) && ebcCode.equals(ebc_code) && logisticsCode.equals(logistics_code))
                {
                    model = def;
                    break;
                } else if (ebpCode.equals(ebp_code) && "%".equals(ebc_code) && logisticsCode.equals(logistics_code))
                {
                    model = def;
                    break;
                } else if ("%".equals(ebp_code) && ebcCode.equals(ebc_code) && logisticsCode.equals(logistics_code))
                {
                    model = def;
                    break;
                } else if ("%".equals(ebp_code) && "%".equals(ebc_code) && logisticsCode.equals(logistics_code))
                {
                    model = def;
                    break;
                }
            }
        }
        return model;
    }

    @Override
    public List<BigJdChannelConfig> selectAllEnabled(){
        return mapper.selectEnabled();
    }
}
