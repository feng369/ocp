package com.topideal.supplychain.ocp.kjb.dto;

import com.topideal.supplychain.ocp.enums.KjbSendStausEnum;
import com.topideal.supplychain.util.JacksonUtils;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author xiao yu qiang
 * @time 2019/6/24 16:05
 * @description 调用跨境宝订单返回实体
 * @table
 */
public class KjbResponse implements Serializable {

    //订单号
    private String order_id;
    //状态
    private KjbSendStausEnum status;

    //备注
    private String notes;

    //商品
    private List<KjbResponseGoods> good_list;

    public static class KjbResponseGoods implements Serializable{

        //商品货号
        private String goods_id;
        //商品名称
        private String goods_name;
        //商品数量
        private String goods_num;
        //商品单价
        private BigDecimal goods_price;
        //计量单位
        private String goods_unit;

        public String getGoods_id() {
            return goods_id;
        }

        public void setGoods_id(String goods_id) {
            this.goods_id = goods_id;
        }

        public String getGoods_name() {
            return goods_name;
        }

        public void setGoods_name(String goods_name) {
            this.goods_name = goods_name;
        }

        public String getGoods_num() {
            return goods_num;
        }

        public void setGoods_num(String goods_num) {
            this.goods_num = goods_num;
        }

        public BigDecimal getGoods_price() {
            return goods_price == null ? null : goods_price.setScale(4, BigDecimal.ROUND_HALF_UP);
        }

        public void setGoods_price(BigDecimal goods_price) {
            this.goods_price = goods_price;
        }

        public String getGoods_unit() {
            return goods_unit;
        }

        public void setGoods_unit(String goods_unit) {
            this.goods_unit = goods_unit;
        }
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public KjbSendStausEnum getStatus() {
        return status;
    }

    public void setStatus(KjbSendStausEnum status) {
        this.status = status;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public List<KjbResponseGoods> getGood_list() {
        return good_list;
    }

    public void setGood_list(
            List<KjbResponseGoods> good_list) {
        this.good_list = good_list;
    }


}
