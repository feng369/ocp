package com.topideal.supplychain.ocp.master.service.impl;

import com.topideal.supplychain.enumeration.EnableOrDisableEnum;
import com.topideal.supplychain.exception.BusinessException;
import com.topideal.supplychain.ocp.master.mapper.PlatformMapper;
import com.topideal.supplychain.ocp.master.model.Merchant;
import com.topideal.supplychain.ocp.master.model.Platform;
import com.topideal.supplychain.ocp.master.service.PlatformService;
import com.topideal.supplychain.security.authorization.Authentication;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author hhl
 * @date 2018/12/27
 */
@Service
public class PlatformServiceImpl implements PlatformService {

    @Autowired
    private PlatformMapper platformMapper;

    @Override
    public List<Platform> pageList(Platform platform) {
        return platformMapper.pageList(platform);
    }

    @Override
    @Transactional
    public int add(Platform platform) {
        Platform oldPlatform = this.findByCode(platform.getCode());
        if (null != oldPlatform) {
            throw new BusinessException("企业编码已存在");
        }
        platform.setCreateBy(Authentication.getUserId());
        platform.setCreateTime(new Date());
        return platformMapper.insert(platform);
    }

    @Override
    public Platform findByCode(String code) {
        return platformMapper.findByCode(code);
    }


    @Override
    public List<Platform> findByVirtualCode(String code) {
        return platformMapper.findByVirtualCode(code);
    }

    @Override
    public Platform findById(Long id) {
        return platformMapper.findById(id);
    }

    @Override
    @Transactional
    public int update(Platform platform) {
        platform.setUpdateBy(Authentication.getUserId());
        platform.setUpdateTime(new Date());
        return platformMapper.update(platform);
    }

    @Override
    public List<Platform> findAll() {
        return platformMapper.findAll();
    }

    @Transactional
    @Override
    public void enable(Long[] ids) {
        platformMapper.enable(ids, EnableOrDisableEnum.ENABLE.getValue());
    }

    @Transactional
    @Override
    public void disable(Long[] ids) {
        platformMapper.disable(ids, EnableOrDisableEnum.DISABLE.getValue());

    }


    @Override
    public List<Platform> autoCompletion(String q) {
        return platformMapper.autoCompletion(q);
    }
}
