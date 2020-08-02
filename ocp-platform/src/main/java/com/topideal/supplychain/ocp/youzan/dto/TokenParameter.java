package com.topideal.supplychain.ocp.youzan.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.youzan.cloud.open.sdk.common.constant.OAuthEnum.ErrorMessage;
import com.youzan.cloud.open.sdk.common.constant.OAuthEnum.TokenType;
import com.youzan.cloud.open.sdk.common.exception.SDKException;
import com.youzan.cloud.open.sdk.common.util.CheckUtils;
import org.apache.commons.lang3.StringUtils;

public class TokenParameter {

    @JsonProperty(value = "authorize_type")
    private String authorizeType;
    @JsonProperty(value = "client_id")
    private String clientId;
    @JsonProperty(value = "client_secret")
    private String clientSecret;
    @JsonProperty(value = "grant_id")
    private String grantId;
    @JsonProperty(value = "code")
    private String code;
    @JsonProperty(value = "refresh_token")
    private String refreshToken;
    @JsonProperty(value = "scope")
    private String scope;

    private TokenParameter() {
    }

    public static TokenParameter.SelfBuilder self() {
        return new TokenParameter.SelfBuilder();
    }

    public static TokenParameter.CertificateBuilder certificate() {
        return new TokenParameter.CertificateBuilder();
    }

    public static TokenParameter.CodeBuilder code() {
        return new TokenParameter.CodeBuilder();
    }

    public static TokenParameter.RefreshBuilder refresh() {
        return new TokenParameter.RefreshBuilder();
    }

    public String getClientId() {
        return this.clientId;
    }

    public String getClientSecret() {
        return this.clientSecret;
    }

    public String getGrantId() {
        return this.grantId;
    }

    public String getCode() {
        return this.code;
    }

    public String getRefreshToken() {
        return this.refreshToken;
    }

    public String getScope() {
        return this.scope;
    }

    public String getAuthorizeType() {
        return this.authorizeType;
    }

    public static class RefreshBuilder {

        private String clientId;
        private String clientSecret;
        private String refreshToken;

        public RefreshBuilder() {
        }

        public TokenParameter.RefreshBuilder clientId(String clientId) {
            this.clientId = clientId;
            return this;
        }

        public TokenParameter.RefreshBuilder clientSecret(String clientSecret) {
            this.clientSecret = clientSecret;
            return this;
        }

        public TokenParameter.RefreshBuilder refreshToken(String refreshToken) {
            this.refreshToken = refreshToken;
            return this;
        }

        public TokenParameter build() throws SDKException {
            CheckUtils
                    .checkArgument(StringUtils.isNotBlank(this.clientId), ErrorMessage.PARAM_ERROR,
                            "clientId can not be null!");
            CheckUtils.checkArgument(StringUtils.isNotBlank(this.clientSecret),
                    ErrorMessage.PARAM_ERROR, "clientSecret can not be null!");
            CheckUtils.checkArgument(StringUtils.isNotBlank(this.refreshToken),
                    ErrorMessage.PARAM_ERROR, "refreshToken can not be null!");
            TokenParameter tokenParameter = new TokenParameter();
            tokenParameter.refreshToken = this.refreshToken;
            tokenParameter.clientId = this.clientId;
            tokenParameter.clientSecret = this.clientSecret;
            tokenParameter.authorizeType = StringUtils
                    .lowerCase(TokenType.refresh_token.toString());
            return tokenParameter;
        }
    }

    public static class CodeBuilder {

        private String clientId;
        private String clientSecret;
        private String code;

        public CodeBuilder() {
        }

        public TokenParameter.CodeBuilder clientId(String clientId) {
            this.clientId = clientId;
            return this;
        }

        public TokenParameter.CodeBuilder clientSecret(String clientSecret) {
            this.clientSecret = clientSecret;
            return this;
        }

        public TokenParameter.CodeBuilder code(String code) {
            this.code = code;
            return this;
        }

        public TokenParameter build() throws SDKException {
            CheckUtils
                    .checkArgument(StringUtils.isNotBlank(this.clientId), ErrorMessage.PARAM_ERROR,
                            "clientId can not be null!");
            CheckUtils.checkArgument(StringUtils.isNotBlank(this.code), ErrorMessage.PARAM_ERROR,
                    "code can not be null!");
            CheckUtils.checkArgument(StringUtils.isNotBlank(this.clientSecret),
                    ErrorMessage.PARAM_ERROR, "clientSecret can not be null!");
            TokenParameter tokenParameter = new TokenParameter();
            tokenParameter.clientId = this.clientId;
            tokenParameter.clientSecret = this.clientSecret;
            tokenParameter.code = this.code;
            tokenParameter.authorizeType = StringUtils
                    .lowerCase(TokenType.authorization_code.toString());
            return tokenParameter;
        }
    }

    public static class CertificateBuilder {

        private String clientId;
        private String clientSecret;

        public CertificateBuilder() {
        }

        public TokenParameter.CertificateBuilder clientId(String clientId) {
            this.clientId = clientId;
            return this;
        }

        public TokenParameter.CertificateBuilder clientSecret(String clientSecret) {
            this.clientSecret = clientSecret;
            return this;
        }

        public TokenParameter build() throws SDKException {
            CheckUtils
                    .checkArgument(StringUtils.isNotBlank(this.clientId), ErrorMessage.PARAM_ERROR,
                            "clientId can not be null!");
            CheckUtils.checkArgument(StringUtils.isNotBlank(this.clientSecret),
                    ErrorMessage.PARAM_ERROR, "clientSecret can not be null!");
            TokenParameter tokenParameter = new TokenParameter();
            tokenParameter.clientId = this.clientId;
            tokenParameter.clientSecret = this.clientSecret;
            tokenParameter.authorizeType = StringUtils.lowerCase(TokenType.certificate.toString());
            return tokenParameter;
        }
    }

    public static class SelfBuilder {

        private String clientId;
        private String clientSecret;
        private String grantId;

        public SelfBuilder() {
        }

        public TokenParameter.SelfBuilder clientId(String clientId) {
            this.clientId = clientId;
            return this;
        }

        public TokenParameter.SelfBuilder clientSecret(String clientSecret) {
            this.clientSecret = clientSecret;
            return this;
        }

        public TokenParameter.SelfBuilder grantId(String bizId) {
            this.grantId = bizId;
            return this;
        }

        public TokenParameter build() throws SDKException {
            CheckUtils
                    .checkArgument(StringUtils.isNotBlank(this.clientId), ErrorMessage.PARAM_ERROR,
                            "clientId can not be null!");
            CheckUtils.checkArgument(StringUtils.isNotBlank(this.clientSecret),
                    ErrorMessage.PARAM_ERROR, "clientSecret can not be null!");
            CheckUtils.checkArgument(StringUtils.isNotBlank(this.grantId), ErrorMessage.PARAM_ERROR,
                    "grantId can not be null!");
            TokenParameter tokenParameter = new TokenParameter();
            tokenParameter.grantId = this.grantId;
            tokenParameter.clientId = this.clientId;
            tokenParameter.clientSecret = this.clientSecret;
            tokenParameter.authorizeType = StringUtils.lowerCase(TokenType.silent.toString());
            return tokenParameter;
        }
    }
}
