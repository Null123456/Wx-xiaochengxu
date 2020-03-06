package com.ssk.pojo;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.util.Map;
@XStreamAlias("xml")
public class MusicMessage extends BaseMessage {

    @XStreamAlias("Music")
    private Music music;

    public Music getMusic() {
        return music;
    }

    public void setMusic(Music music) {
        this.music = music;
    }

    public MusicMessage(Map<String, String> requsetMap, Music music) {
        super(requsetMap);
        setMsgType("music");
        this.music = music;
    }
}
