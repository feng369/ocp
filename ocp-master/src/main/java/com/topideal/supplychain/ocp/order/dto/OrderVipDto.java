package com.topideal.supplychain.ocp.order.dto;

import com.topideal.supplychain.ocp.order.model.OrderVip;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * <p>标题: </p>
 * <p>模块: ocp-parent</p>
 * <p>版权: Copyright © 2019 topideal</p>
 * <p>公司: 广东卓志供应链科技有限公司武汉分公司</p>
 * <p>类路径: com.topideal.supplychain.ocp.order.dto</p>
 * <p>作者: LeoZhang</p>
 * <p>创建日期: 2019/12/30 13:59</p>
 *
 * @version 1.0
 */
public class OrderVipDto extends OrderVip {

    /**
     * 制单时间（查询条件）
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTimeStart;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTimeEnd;

    /**
     * 申报海关代码
     */
    private String customsCodeStr;

    /**
     * 进出口类型
     */
    private String tradeModeStr;

    public String getCustomsCodeStr() {
        return customsCodeStr;
    }

    public void setCustomsCodeStr(String customsCodeStr) {
        this.customsCodeStr = customsCodeStr;
    }

    public String getTradeModeStr() {
        return tradeModeStr;
    }

    public void setTradeModeStr(String tradeModeStr) {
        this.tradeModeStr = tradeModeStr;
    }

    public Date getCreateTimeStart() {
        return createTimeStart;
    }

    public void setCreateTimeStart(Date createTimeStart) {
        this.createTimeStart = createTimeStart;
    }

    public Date getCreateTimeEnd() {
        return createTimeEnd;
    }

    public void setCreateTimeEnd(Date createTimeEnd) {
        this.createTimeEnd = createTimeEnd;
    }
}
