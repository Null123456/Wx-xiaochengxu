package com.ssk.pojo;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.util.Map;


public class BaseMessage {
    @XStreamAlias("ToUserName")
    private String touserName;
    @XStreamAlias("FromUserName")
    private String fromusrName;
    @XStreamAlias("CreateTime")
    private String createTime;
    @XStreamAlias("MsgType")
    private String msgType;

    public String getTouserName() {
        return touserName;
    }

    public void setTouserName(String touserName) {
        this.touserName = touserName;
    }

    public String getFromusrName() {
        return fromusrName;
    }

    public void setFromusrName(String fromusrName) {
        this.fromusrName = fromusrName;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public BaseMessage(Map<String,String> requsetMap){
        this.touserName = requsetMap.get("FromUserName");
        this.fromusrName = requsetMap.get("ToUserName");
        this.createTime = System.currentTimeMillis() /1000+"";
    }

    @Override
    public String toString() {
        return "BaseMessage{" +
                "touserName='" + touserName + '\'' +
                ", fromusrName='" + fromusrName + '\'' +
                ", createTime='" + createTime + '\'' +
                ", msgType='" + msgType + '\'' +
                '}';
    }
}
