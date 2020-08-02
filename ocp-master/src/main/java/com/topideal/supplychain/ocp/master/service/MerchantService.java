package com.topideal.supplychain.ocp.master.service;

import com.topideal.supplychain.ocp.master.model.Merchant;
import java.util.List;

/**
 * @author hhl
 * @date 2018/12/27
 */
public interface MerchantService {

    List<Merchant> findBySelective(Merchant merchant);

    int add(Merchant merchant);

    Merchant findByCode(String code);

    int forbidden(String[] ids);

    Merchant findById(Long id);

    int update(Merchant merchant);

    List<Merchant> findAll();

    void updateStatus(Long aLong, Integer value);

    List<Merchant> autoCompletion(String q);
}
