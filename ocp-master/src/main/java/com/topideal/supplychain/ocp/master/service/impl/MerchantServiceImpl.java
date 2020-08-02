package com.topideal.supplychain.ocp.master.service.impl;

import com.topideal.supplychain.exception.BusinessException;
import com.topideal.supplychain.ocp.master.mapper.MerchantMapper;
import com.topideal.supplychain.ocp.master.model.Merchant;
import com.topideal.supplychain.ocp.master.service.MerchantService;
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
public class MerchantServiceImpl implements MerchantService {

    @Autowired
    private MerchantMapper merchantMapper;

    @Override
    public List<Merchant> findBySelective(Merchant merchant) {
        return merchantMapper.findBySelective(merchant);
    }

    @Override
    @Transactional
    public int add(Merchant merchant) {
        Merchant oldMerchant = this.findByCode(merchant.getCode());
        if (null != oldMerchant) {
            throw new BusinessException("企业编码已存在");
        }
        merchant.setCreateBy(Authentication.getUserId());
        merchant.setCreateTime(new Date());
        return merchantMapper.insert(merchant);
    }

    @Override
    public Merchant findByCode(String code) {
        return merchantMapper.findByCode(code);
    }

    @Override
    @Transactional
    public int forbidden(String[] ids) {
        return merchantMapper.forbidden(ids);
    }

    @Override
    public Merchant findById(Long id) {
        return merchantMapper.findById(id);
    }

    @Override
    @Transactional
    public int update(Merchant merchant) {
        merchant.setUpdateBy(Authentication.getUserId());
        merchant.setUpdateTime(new Date());
        return merchantMapper.update(merchant);
    }

    @Override
    public List<Merchant> findAll() {
        return merchantMapper.findAll();
    }

    @Override
    @Transactional
    public void updateStatus(Long id, Integer statusValue) {
        merchantMapper.updateStatus(id, statusValue);
    }

    @Override
    public List<Merchant> autoCompletion(String q) {
        return merchantMapper.autoCompletion(q);
    }
}
