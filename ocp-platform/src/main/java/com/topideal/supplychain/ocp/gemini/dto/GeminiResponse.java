package com.topideal.supplychain.ocp.gemini.dto;

import java.io.Serializable;
import java.util.List;

/**
 * @author xiao yu qiang
 * @time 2019/5/7 15:11
 * @description
 * @table
 */
public class GeminiResponse implements Serializable {

    //返回状态 SUCCESS/FAILURE
    private String flag;

    //错误信息
    private String failMessage;

    //返回信息
    private Message message;

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getFailMessage() {
        return failMessage;
    }

    public void setFailMessage(String failMessage) {
        this.failMessage = failMessage;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public static class Message implements Serializable{
        //完税货价
        private String goodsValue;
        //税费
        private String taxTotal;
        //商品
        private List<Goods> goods;

        public String getGoodsValue() {
            return goodsValue;
        }

        public void setGoodsValue(String goodsValue) {
            this.goodsValue = goodsValue;
        }

        public String getTaxTotal() {
            return taxTotal;
        }

        public void setTaxTotal(String taxTotal) {
            this.taxTotal = taxTotal;
        }

        public List<Goods> getGoods() {
            return goods;
        }

        public void setGoods(
            List<Goods> goods) {
            this.goods = goods;
        }

        public static class Goods implements Serializable{
            //商品id
            private String id;
            //商品编码
            private String outerItemId;
            //完税单价
            private String price;
            //完税总价
            private String totalPrice;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getOuterItemId() {
                return outerItemId;
            }

            public void setOuterItemId(String outerItemId) {
                this.outerItemId = outerItemId;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getTotalPrice() {
                return totalPrice;
            }

            public void setTotalPrice(String totalPrice) {
                this.totalPrice = totalPrice;
            }
        }
    }

}
