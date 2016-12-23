package com.lms.jasonWrapper;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by nuwantha on 12/21/16.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Name {
    @JsonProperty
    String fullName;

    @JsonProperty
    String givenName;

    @JsonProperty
    String familyName;

    public Name(){

    }

    public String getFullname() {
        return fullName;
    }

    public void setFullname(String fullname) {
        this.fullName = fullname;
    }

    public String getGivenName() {
        return givenName;
    }

    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }
}
