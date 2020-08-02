package com.topideal.supplychain.ocp.youzan.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import java.io.Serializable;

/**
 * @ClassName YouzanToken
 * @TODO TODO
 * @Author zhangzhihao
 * @DATE 2019/12/2 16:52
 * @Version 1.0
 **/
public class YouzanToken implements Serializable {

    private boolean success;

    private String code;

    private String message;

    private OAuthToken data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public OAuthToken getData() {
        return data;
    }

    public void setData(OAuthToken data) {
        this.data = data;
    }

    public static class OAuthToken {

        private String accessToken;
        private Long expires;
        private String authorityId;
        private String scope;
        private String refreshToken;

        public OAuthToken() {
        }

        public String getAccessToken() {
            return this.accessToken;
        }

        @JsonAlias("access_token")
        public void setAccessToken(String accessToken) {
            this.accessToken = accessToken;
        }

        public Long getExpires() {
            return this.expires;
        }

        public void setExpires(Long expires) {
            this.expires = expires;
        }

        public String getScope() {
            return this.scope;
        }

        public void setScope(String scope) {
            this.scope = scope;
        }

        public String getRefreshToken() {
            return this.refreshToken;
        }

        @JsonAlias("refresh_token")
        public void setRefreshToken(String refreshToken) {
            this.refreshToken = refreshToken;
        }

        public String getAuthorityId() {
            return this.authorityId;
        }

        @JsonAlias("authority_id")
        public void setAuthorityId(String authorityId) {
            this.authorityId = authorityId;
        }

    }

}
