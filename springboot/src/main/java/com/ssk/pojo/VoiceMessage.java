package com.ssk.pojo;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.util.Map;
@XStreamAlias("xml")
public class VoiceMessage extends BaseMessage {
    @XStreamAlias("MediaId")
    private String mediaId;

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    public VoiceMessage(Map<String, String> requsetMap,String mediaId) {
        super(requsetMap);
        this.setMsgType("voice");
        this.mediaId = mediaId;
    }
}
