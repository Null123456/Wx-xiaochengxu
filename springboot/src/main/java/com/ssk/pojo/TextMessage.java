package com.ssk.pojo;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.util.Map;
@XStreamAlias("xml")
public class TextMessage extends BaseMessage {
    @XStreamAlias("Content")
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public TextMessage(Map<String,String> requsetMap,String content){
        super(requsetMap);
        this.setMsgType("text");
        this.content = content;
    }

    @Override
    public String toString() {
        return "TextMessage{" +"getToUsreName="+getTouserName()+",getFromUserName="+getFromusrName()+",getCreateTime="+getCreateTime()+
                "content='" + content + '\'' +
                '}';
    }
}
