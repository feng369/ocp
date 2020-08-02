package com.topideal.supplychain.ocp.config.service;

import com.topideal.supplychain.ocp.config.model.CombineGoodsConfig;

import java.util.List;

/**
 * 标题：组套商品配置service
 * 模块：ocp-parent
 * 版权: Copyright © 2019 topideal
 * 公司: 广东卓志供应链科技有限公司武汉分公司
 * 类路径：com.topideal.supplychain.ocp.config.service
 * 作者：songping
 * 创建日期：2019/12/23 15:00
 *
 * @version 1.0
 */
public interface CombineGoodsConfigService {

    /**
     * 删除配置
     * @param id
     */
    void delete(Long id);

    /**
     * 批量删除
     * @param ids
     */
    void deleteByIds(Long[] ids);

    /**
     * 保存配置
     * @param record
     */
    void insert(CombineGoodsConfig record);

    /**
     * 查询配置
     * @param id
     * @return
     */
    CombineGoodsConfig selectById(Long id);

    /**
     * 条件查询
     * @param filter
     * @return
     */
    List<CombineGoodsConfig> pageList(CombineGoodsConfig filter);

    /**
     * 查询配置
     * @param combineCode
     * @return
     */
    List<CombineGoodsConfig> selectByCombineCode(String combineCode);

    /**
     * 更新配置
     * @param record
     */
    void update(CombineGoodsConfig record);

    /**
     * 批量更新
     * @param record
     */
    void updateByIds(CombineGoodsConfig record);

    /**
     * 启用配置
     * @param ids
     */
    void enable(Long[] ids);

    /**
     * 禁用配置
     * @param ids
     */
    void disable(Long[] ids);

}
