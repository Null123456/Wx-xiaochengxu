package com.ssk.pojo;

public class Global {
    public static String OpenId;
    public static String SessionKey;

    public static String getSessionKey() {
        return SessionKey;
    }

    public static void setSessionKey(String sessionKey) {
        SessionKey = sessionKey;
    }

    public static String getOpenId() {
        return OpenId;
    }

    public static void setOpenId(String openId) {
        OpenId = openId;
    }
}
