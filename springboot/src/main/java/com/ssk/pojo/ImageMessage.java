package com.ssk.pojo;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.util.Map;
@XStreamAlias("xml")
public class ImageMessage extends BaseMessage {
    @XStreamAlias("Image")
    private Image image;

    public ImageMessage(Map<String, String> requsetMap, Image image) {
        super(requsetMap);
        setMsgType("image");
        this.image = image;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
}
