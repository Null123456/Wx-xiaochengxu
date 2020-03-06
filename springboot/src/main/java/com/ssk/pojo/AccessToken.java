package com.ssk.pojo;

import com.sun.org.apache.xpath.internal.operations.Bool;

public class AccessToken {
    private String access_token;
    private Long expires_time;

    public AccessToken(String access_token, String expires_in) {
        this.access_token = access_token;
        this.expires_time =System.currentTimeMillis()+Integer.parseInt(expires_in)*1000;

    }

    //判断token是否过期
    public Boolean isExpired(){
        return System.currentTimeMillis()>expires_time;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public Long getExpires_time() {
        return expires_time;
    }

    public void setExpires_time(Long expires_time) {
        this.expires_time = expires_time;
    }
}
