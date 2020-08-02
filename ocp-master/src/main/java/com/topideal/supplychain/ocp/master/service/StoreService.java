package com.topideal.supplychain.ocp.master.service;

import com.topideal.supplychain.ocp.master.dto.StoreDto;
import com.topideal.supplychain.ocp.master.model.Platform;
import com.topideal.supplychain.ocp.master.model.Store;

import java.util.List;

public interface StoreService {
    /**
     * 禁用
     * @param ids
     * @return
     */
    int disable(Long[] ids);

    /**
     * 启用
     * @param ids
     * @return
     */
    int enable(Long[] ids);

    /**
     * 删除
     * @param id
     * @return
     */
    int removeById(Long id);

    /**
     * 新增
     * @param record
     * @return
     */
    int insert(Store record);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    Store selectById(Long id);

    /**
     * 根据唯一索引查询
     * @param code
     * @return
     */
    Store selectByCode(String code);

    Store selectEnableByCode(String code);

    /**
     * 条件查询
     * @param condition
     * @return
     */
    List<Store> selectByCondition(Store condition);

    /**
     * 更新
     * @param record
     * @return
     */
    int update(Store record);

    /**
     * 全字段更新
     * @param record
     * @return
     */
    int updateAll(Store record);

    /**
     * 分页查询
     * @param condition
     * @return
     */
    List<StoreDto> pageList(StoreDto condition);

    /**
     * 根据条件查询
     * @param store
     * @return
     */
    List<Store> findByFilter(Store store);

    List<Store> findAll();

    /**
     * 根据电商平台查询
     * @return
     */
    List<Store> findByPlatforms(List<Platform> platforms);

    Store selectEnableById(Long id);

    List<Store> selectEnableByCodes(String storeCode);

    List<Store> autoCompletion(String q);
}
