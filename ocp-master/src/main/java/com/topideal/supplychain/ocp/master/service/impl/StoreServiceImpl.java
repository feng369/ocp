package com.topideal.supplychain.ocp.master.service.impl;

import com.topideal.supplychain.ocp.master.dto.StoreDto;
import com.topideal.supplychain.ocp.master.mapper.StoreMapper;
import com.topideal.supplychain.ocp.master.model.Platform;
import com.topideal.supplychain.ocp.master.model.Store;
import com.topideal.supplychain.ocp.master.service.StoreService;
import com.topideal.supplychain.security.authorization.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class StoreServiceImpl implements StoreService {
    @Autowired
    private StoreMapper storeMapper;
    /**
     * 禁用
     * @param ids
     * @return
     */
    @Override
    @Transactional
    public int disable(Long[] ids) {
        return storeMapper.disable(ids);
    }
    /**
     * 启用
     * @param ids
     * @return
     */
    @Override
    @Transactional
    public int enable(Long[] ids) {
        return storeMapper.enable(ids);
    }
    /**
     * 删除
     * @param id
     * @return
     */
    @Override
    @Transactional
    public int removeById(Long id) {
        return storeMapper.removeById(id);
    }
    /**
     * 新增
     * @param record
     * @return
     */
    @Override
    @Transactional
    public int insert(Store record) {
        record.setCreateBy(Authentication.getUserId());
        record.setCreateTime(new Date());
        return storeMapper.insert(record);
    }
    /**
     * 根据id查询
     * @param id
     * @return
     */
    @Override
    public Store selectById(Long id) {
        return storeMapper.selectById(id);
    }
    /**
     * 根据唯一索引查询
     * @param code
     * @return
     */
    @Override
    public Store selectByCode(String code) {
        return storeMapper.selectByCode(code);
    }

    @Override
    public Store selectEnableByCode(String code) {
        return storeMapper.selectEnableByCode(code);
    }

    /**
     * 条件查询
     * @param condition
     * @return
     */
    @Override
    public List<Store> selectByCondition(Store condition) {
        return storeMapper.selectByCondition(condition);
    }

    /**
     * 更新
     * @param record
     * @return
     */
    @Override
    @Transactional
    public int update(Store record) {
        record.setUpdateBy(Authentication.getUserId());
        record.setUpdateTime(new Date());
        return storeMapper.update(record);
    }
    /**
     * 全字段更新
     * @param record
     * @return
     */
    @Override
    @Transactional
    public int updateAll(Store record) {
        record.setUpdateBy(Authentication.getUserId());
        record.setUpdateTime(new Date());
        return storeMapper.updateAll(record);
    }

    /**
     * 分页查询
     * @param condition
     * @return
     */
    @Override
    public List<StoreDto> pageList(StoreDto condition) {
        return storeMapper.pageList(condition);
    }

    /**
     * 根据条件查询
     *
     * @param store
     * @return
     */
    @Override
    public List<Store> findByFilter(Store store) {
        return storeMapper.findByFilter(store);
    }

    @Override
    public List<Store> findAll() {
        return storeMapper.findAll();
    }

    /**
     *
     * @param platforms
     * @return
     */
    @Override
    public List<Store> findByPlatforms(List<Platform> platforms) {
        return storeMapper.findByPlatforms(platforms);
    }

    @Override
    public Store selectEnableById(Long id) {
        return storeMapper.selectEnableById(id);
    }

    @Override
    public List<Store> selectEnableByCodes(String storeCode) {
        return storeMapper.selectEnableByCodes(storeCode);
    }

    @Override
    public List<Store> autoCompletion(String q) {
        return storeMapper.autoCompletion(q);
    }
}
