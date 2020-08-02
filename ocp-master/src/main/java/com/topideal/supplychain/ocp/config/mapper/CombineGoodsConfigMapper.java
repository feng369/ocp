package com.topideal.supplychain.ocp.config.mapper;

import com.topideal.supplychain.ocp.config.model.CombineGoodsConfig;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 标题：组套商品配置Mapper
 * 模块：ocp-parent
 * 版权: Copyright © 2019 topideal
 * 公司: 广东卓志供应链科技有限公司武汉分公司
 * 类路径：com.topideal.supplychain.ocp.config.mapper
 * 作者：songping
 * 创建日期：2019/12/23 14:42
 *
 * @version 1.0
 */
@Mapper
public interface CombineGoodsConfigMapper {

    int delete(Long id);

    int deleteByIds(@Param("ids")Long[] ids);

    int insert(CombineGoodsConfig record);

    CombineGoodsConfig selectByPrimaryKey(Long id);

    List<CombineGoodsConfig> pageList(CombineGoodsConfig filter);

    int update(CombineGoodsConfig record);

    int updateByIds(CombineGoodsConfig record);

}
