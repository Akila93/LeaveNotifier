package com.lms.jasonWrapper;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by nuwantha on 12/21/16.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class Wrapper {
    @JsonProperty
    String nextPageToken;

    @JsonProperty
    String kind;

    @JsonProperty
    String etag;

    @JsonProperty
    List<Users> users;

    public Wrapper(){

    }

    public String getNextPageToken() {
        return nextPageToken;
    }

    public void setNextPageToken(String nextPageToken) {
        this.nextPageToken = nextPageToken;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getEtag() {
        return etag;
    }

    public void setEtag(String etag) {
        this.etag = etag;
    }

    public List<Users> getUsers() {
        return users;
    }

    public void setUsers(List<Users> users) {
        this.users = users;
    }
}
