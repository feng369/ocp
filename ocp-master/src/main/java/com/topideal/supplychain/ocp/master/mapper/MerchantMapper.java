package com.topideal.supplychain.ocp.master.mapper;

import com.topideal.supplychain.ocp.master.model.Merchant;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author hhl
 * @date 2018/12/27
 */
@Mapper
public interface MerchantMapper {

    List<Merchant> findBySelective(Merchant merchant);

    int insert(Merchant merchant);

    Merchant findByCode(String code);

    int forbidden(@Param("ids") String[] ids);

    Merchant findById(Long id);

    int update(Merchant merchant);

    List<Merchant> findAll();

    void updateStatus(@Param("id") Long id, @Param("status") Integer statusValue);

    List<Merchant> autoCompletion(@Param("keyword") String keyword);

}
