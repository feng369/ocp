package com.topideal.supplychain.ocp.config.mapper;

import com.topideal.supplychain.ocp.config.dto.BigJdChannelConfigPageRequestDto;
import com.topideal.supplychain.ocp.config.model.BigJdChannelConfig;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 标题：京东多渠道大订单配置mapper
 * 模块：ocp-parent
 * 版权: Copyright © 2019 topideal
 * 公司: 广东卓志供应链科技有限公司武汉分公司
 * 类路径：com.topideal.supplychain.ocp.config.mapper
 * 作者：songping
 * 创建日期：2019/12/24 14:16
 *
 * @version 1.0
 */
@Mapper
public interface BigJdChannelConfigMapper {

    List<BigJdChannelConfig> pageList(BigJdChannelConfigPageRequestDto filter);

    List<BigJdChannelConfig> selectEnabled();

    BigJdChannelConfig selectByPrimaryKey(Long id);

    int delete(Long id);

    int deleteByIds(@Param("ids") List<Long> ids);

    int insert(BigJdChannelConfig record);

    int update(BigJdChannelConfig record);

    int updateByIds(BigJdChannelConfig record);
}
