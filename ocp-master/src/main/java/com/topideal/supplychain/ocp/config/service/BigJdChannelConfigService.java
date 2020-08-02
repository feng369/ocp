package com.topideal.supplychain.ocp.config.service;

import com.topideal.supplychain.ocp.config.dto.BigJdChannelConfigPageRequestDto;
import com.topideal.supplychain.ocp.config.model.BigJdChannelConfig;

import java.util.List;

/**
 * 标题：京东多渠道大订单配置service
 * 模块：ocp-parent
 * 版权: Copyright © 2019 topideal
 * 公司: 广东卓志供应链科技有限公司武汉分公司
 * 类路径：com.topideal.supplychain.ocp.config.service
 * 作者：songping
 * 创建日期：2019/12/24 15:19
 *
 * @version 1.0
 */
public interface BigJdChannelConfigService {

    /**
     * 删除配置
     * @param id
     */
    void delete(Long id);

    /**
     * 批量删除
     * @param ids
     */
    void deleteByIds(List<Long> ids);

    /**
     * 保存配置
     * @param record
     */
    void insert(BigJdChannelConfig record);

    /**
     * 查询配置
     * @param id
     * @return
     */
    BigJdChannelConfig selectById(Long id);

    /**
     * 条件查询
     * @param filter
     * @return
     */
    List<BigJdChannelConfig> pageList(BigJdChannelConfigPageRequestDto filter);

    /**
     * 更新配置
     * @param record
     */
    void update(BigJdChannelConfig record);

    /**
     * 批量更新
     * @param record
     */
    void updateByIds(BigJdChannelConfig record);

    /**
     * 启用配置
     * @param ids
     */
    void enable(List<Long> ids);

    /**
     * 禁用配置
     * @param ids
     */
    void disable(List<Long> ids);

    /**
     * 查询启用状态的配置
     * @return
     */
    List<BigJdChannelConfig> selectAllEnabled();

    /**
     * 根据电商平台，电商企业和物流企获取配置
     * 匹配级别
     * 1、电商平台+电商企业+物流企业
     * 2、电商平台+%+物流企业
     * 3、%+电商企业+物流企业
     * 4、%+%+物流企业
     * @param ebpCode
     * @param ebcCode
     * @param logisticsCode
     * @return
     */
    BigJdChannelConfig getBigJdChannelConfigModel(String ebpCode, String ebcCode, String logisticsCode);
}
