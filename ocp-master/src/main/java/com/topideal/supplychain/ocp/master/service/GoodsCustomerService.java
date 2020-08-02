package com.topideal.supplychain.ocp.master.service;

import com.topideal.supplychain.ocp.master.model.GoodsCustomerConfig;
import java.io.InputStream;
import java.util.List;

/**
 * 商品业主
 * @author xuxiaoyan
 * @date 2019-11-14 16:35
 */
public interface GoodsCustomerService {

    List<GoodsCustomerConfig> pageList(GoodsCustomerConfig goodsCustomerConfig);

    void insert(GoodsCustomerConfig goodsCustomerConfig);

    GoodsCustomerConfig selectById(Long id);

    void update(GoodsCustomerConfig goodsCustomerConfig);

    void batchRemove(List<String> idList);

    String importDatas(InputStream inputStream) throws Exception;

    List<GoodsCustomerConfig> selectByFilter(GoodsCustomerConfig goodsCustomerConfig);

    void batchInsert(List<GoodsCustomerConfig> datas);
}
