package com.topideal.supplychain.ocp.esd.dto;

import java.io.Serializable;

public class EsdServiceType implements Serializable {

    private Integer service_type; //服务类型

    private EsdServiceType(Builder builder) {
        service_type = builder.service_type;
    }

    public static Builder newBuilder() {
        return new Builder();
    }


    public static final class Builder {

        private Integer service_type;

        private Builder() {
        }

        public Builder service_type(Integer service_type) {
            this.service_type = service_type;
            return this;
        }

        public EsdServiceType build() {
            return new EsdServiceType(this);
        }
    }

    public Integer getService_type() {
        return service_type;
    }
}
