package com.topideal.supplychain.ocp.pub.dto;

import java.io.Serializable;

/**
 * <p>标题: </p>
 * <p>模块: ocp-parent</p>
 * <p>版权: Copyright © 2019 topideal</p>
 * <p>公司: 广东卓志供应链科技有限公司武汉分公司</p>
 * <p>类路径: com.topideal.supplychain.ocp.pub</p>
 * <p>作者: LeoZhang</p>
 * <p>创建日期: 2019/12/20 16:54</p>
 *
 * @version 1.0
 */
public class PubReceiptDto implements Serializable {

    /**
     * 字符集
     */
    private String charset;

    /**
     * 请求体
     */
    private String biz_content;

    /**
     * 方法名
     */
    private String method;

    /**
     * 格式
     */

    private String format;

    /**
     * 签名
     */
    private String sign;

    /**
     * appId
     */
    private String app_id;

    /**
     * 版本
     */

    private String version;

    /**
     * 签名类型
     */

    private String sign_type;

    /**
     * 密钥
     */
    private String app_key;

    /**
     * 时间戳
     */
    private String timestamp;

    public String getCharset() {
        return charset;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }

    public String getBiz_content() {
        return biz_content;
    }

    public void setBiz_content(String biz_content) {
        this.biz_content = biz_content;
    }

    public String getApp_id() {
        return app_id;
    }

    public void setApp_id(String app_id) {
        this.app_id = app_id;
    }

    public String getSign_type() {
        return sign_type;
    }

    public void setSign_type(String sign_type) {
        this.sign_type = sign_type;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getApp_key() {
        return app_key;
    }

    public void setApp_key(String app_key) {
        this.app_key = app_key;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }



    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
