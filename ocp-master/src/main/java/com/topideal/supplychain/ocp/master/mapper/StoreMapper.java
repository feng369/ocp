package com.topideal.supplychain.ocp.master.mapper;

import com.topideal.supplychain.dict.DataDictAction;
import com.topideal.supplychain.ocp.master.dto.StoreDto;
import com.topideal.supplychain.ocp.master.model.Platform;
import com.topideal.supplychain.ocp.master.model.Store;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface StoreMapper {
    int disable(@Param("ids") Long[] ids);

    int enable(@Param("ids") Long[] ids);

    int removeById(Long id);

    int insert(Store record);

    Store selectById(Long id);

    Store selectByCode(@Param("code") String code);

    Store selectEnableByCode(@Param("code") String code);

    List<Store> selectByCondition(Store condition);

    int update(Store record);

    int updateAll(Store record);
    @DataDictAction
    List<StoreDto> pageList(StoreDto condition);

    List<Store> findByFilter(Store store);

    List<Store> findAll();

    //List<Store> findByPlatform(List<Platform> platforms);

    List<Store> findByPlatforms(@Param("platforms") List<Platform> platforms);

    Store selectEnableById(Long id);

    List<Store> selectEnableByCodes(String storeCode);

    List<Store> autoCompletion(@Param("keyword") String keyword);
}