package com.lms.jasonWrapper;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by nuwantha on 12/21/16.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Email {
    @JsonProperty
    String primary;

    @JsonProperty
    String address;

    public Email(){

    }

    public String getPrimary() {
        return primary;
    }

    public void setPrimary(String primary) {
        this.primary = primary;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
