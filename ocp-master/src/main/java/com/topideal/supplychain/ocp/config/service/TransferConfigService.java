package com.topideal.supplychain.ocp.config.service;

import com.topideal.supplychain.ocp.config.model.TransferConfig;
import com.topideal.supplychain.ocp.enums.BusiModeEnum;
import io.micrometer.core.lang.NonNull;

import java.util.List;

/**
 * <p>标题: </p>
 * <p>模块: ocp-parent</p>
 * <p>版权: Copyright © 2019 topideal</p>
 * <p>公司: 广东卓志供应链科技有限公司武汉分公司</p>
 * <p>类路径: com.topideal.supplychain.ocp.service</p>
 * <p>作者: LeoZhang</p>
 * <p>创建日期: 2019/11/22 13:41</p>
 *
 * @version 1.0
 */
public interface TransferConfigService {
    List<TransferConfig> pageList(TransferConfig filter);

    List<TransferConfig> selectAll();

    void save(TransferConfig config);

    void edit(TransferConfig config);

    void enable(String ids);

    void disable(String ids);

    void update(TransferConfig updateEntity);

    void insert(TransferConfig config);

    TransferConfig selectById(Long id);

    void updateByIds(TransferConfig updateEntity);

    void delete(String ids);

    TransferConfig findByUnique(@NonNull String electricCode, @NonNull String platformCode, @NonNull String logisticsCode, @NonNull String customsCode, @NonNull String businessMode);

    /**
     * 查询大订单配置
     * @param ebpCode
     * @param ebcCode
     * @param logisticsCode
     * @param customsCode
     * @param busiMode
     * @return
     */
    TransferConfig getBigTransferConfig(@NonNull String ebpCode, @NonNull String ebcCode, String logisticsCode, @NonNull String customsCode, @NonNull String busiMode);

    /**
     * 查询生效的配置
     * @return
     */
    List<TransferConfig> selectEnabled();

    void clearCache();
}
