package com.ssk.pojo;

public class Message {
    private String id;
    private String address;
    private String latitude;
    private String longitude;
    private String message;
    private String contact;
    private String type;
    private String openid;

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getOpenid() {
        return openid;
    }

    public String getId() {
        return id;
    }

    public String getAddress() {
        return address;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public String getMessage() {
        return message;
    }

    public String getContact() {
        return contact;
    }

    public String getType() {
        return type;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id='" + id + '\'' +
                ", address='" + address + '\'' +
                ", latitude='" + latitude + '\'' +
                ", longitude='" + longitude + '\'' +
                ", message='" + message + '\'' +
                ", contact='" + contact + '\'' +
                ", type='" + type + '\'' +
                ", openid='" + openid + '\'' +
                '}';
    }
}
