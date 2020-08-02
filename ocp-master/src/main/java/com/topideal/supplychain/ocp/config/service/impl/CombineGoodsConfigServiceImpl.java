package com.topideal.supplychain.ocp.config.service.impl;

import com.topideal.supplychain.enumeration.EnableOrDisableEnum;
import com.topideal.supplychain.ocp.config.mapper.CombineGoodsConfigMapper;
import com.topideal.supplychain.ocp.config.model.CombineGoodsConfig;
import com.topideal.supplychain.ocp.config.service.CombineGoodsConfigService;
import com.topideal.supplychain.ocp.constants.CodeRuleConstants;
import com.topideal.supplychain.security.authorization.Authentication;
import com.topideal.supplychain.util.DateUtils;
import com.topideal.supplychain.web.system.service.impl.CodeGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 标题：组套商品配置service
 * 模块：ocp-parent
 * 版权: Copyright © 2019 topideal
 * 公司: 广东卓志供应链科技有限公司武汉分公司
 * 类路径：com.topideal.supplychain.ocp.config.service.impl
 * 作者：songping
 * 创建日期：2019/12/23 15:03
 *
 * @version 1.0
 */
@Service
public class CombineGoodsConfigServiceImpl implements CombineGoodsConfigService {

    @Autowired
    private CodeGeneratorService codeGeneratorService;
    @Autowired
    private CombineGoodsConfigMapper goodsConfigMapper;

    @Override
    @Transactional
    public void delete(Long id) {
        goodsConfigMapper.delete(id);
    }

    @Override
    @Transactional
    public void deleteByIds(Long[] ids) {
        goodsConfigMapper.deleteByIds(ids);
    }

    @Override
    @Transactional
    public void insert(CombineGoodsConfig record) {
        record.setStartFlag(EnableOrDisableEnum.DISABLE);
        record.setCode(codeGeneratorService.generate(CodeRuleConstants.RULE_COMBINEGOODS_CFG));
        record.setCreateTime(DateUtils.getNowTime());
        record.setCreateBy(Authentication.getUserId());
        goodsConfigMapper.insert(record);
    }

    @Override
    public CombineGoodsConfig selectById(Long id) {
        return goodsConfigMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<CombineGoodsConfig> pageList(CombineGoodsConfig filter) {
        return goodsConfigMapper.pageList(filter);
    }

    @Override
    public List<CombineGoodsConfig> selectByCombineCode(String combineCode) {
        CombineGoodsConfig filter = new CombineGoodsConfig();
        filter.setCombineCode(combineCode);
        filter.setStartFlag(EnableOrDisableEnum.ENABLE);
        return pageList(filter);
    }

    @Override
    @Transactional
    public void update(CombineGoodsConfig record) {
        record.setUpdateBy(Authentication.getUserId());
        record.setUpdateTime(DateUtils.getNowTime());
        goodsConfigMapper.update(record);
    }

    @Override
    @Transactional
    public void updateByIds(CombineGoodsConfig record) {
        if (record == null || record.getIds() == null || record.getIds().length == 0) {
            return;
        }
        record.setUpdateBy(Authentication.getUserId());
        record.setUpdateTime(DateUtils.getNowTime());
        goodsConfigMapper.updateByIds(record);
    }

    @Override
    @Transactional
    public void enable(Long[] ids) {
        if (ids == null || ids.length == 0) {
            return;
        }
        CombineGoodsConfig config = new CombineGoodsConfig();
        config.setIds(ids);
        config.setStartFlag(EnableOrDisableEnum.ENABLE);
        this.updateByIds(config);
    }

    @Override
    @Transactional
    public void disable(Long[] ids) {
        if (ids == null || ids.length == 0) {
            return;
        }
        CombineGoodsConfig config = new CombineGoodsConfig();
        config.setIds(ids);
        config.setStartFlag(EnableOrDisableEnum.DISABLE);
        this.updateByIds(config);
    }
}
