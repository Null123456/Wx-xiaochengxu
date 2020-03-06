package com.ssk.pojo;

public class MemberList {
    private String id;
    private String access;
    private String bodyStatus;
    private String checkInAddress;
    private String checkInTime;
    private String comment;
    private String contactLikeVirus;
    private String contactLikeVirusRegion;
    private String contactVirus;
    private String createTime;
    private String fromAddress;
    private String fromHB;
    private String fromWH;
    private String type;
    private String memberId;
    private String operatorUsername;
    private String temperature;
    private String traffic;

    @Override
    public String toString() {
        return "MemberList{" +
                "id='" + id + '\'' +
                ", access='" + access + '\'' +
                ", bodyStatus='" + bodyStatus + '\'' +
                ", checkInAddress='" + checkInAddress + '\'' +
                ", checkInTime='" + checkInTime + '\'' +
                ", comment='" + comment + '\'' +
                ", contactLikeVirus='" + contactLikeVirus + '\'' +
                ", contactLikeVirusRegion='" + contactLikeVirusRegion + '\'' +
                ", contactVirus='" + contactVirus + '\'' +
                ", createTime='" + createTime + '\'' +
                ", fromAddress='" + fromAddress + '\'' +
                ", fromHB='" + fromHB + '\'' +
                ", fromWH='" + fromWH + '\'' +
                ", type='" + type + '\'' +
                ", memberId='" + memberId + '\'' +
                ", operatorUsername='" + operatorUsername + '\'' +
                ", temperature='" + temperature + '\'' +
                ", traffic='" + traffic + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAccess() {
        return access;
    }

    public void setAccess(String access) {
        this.access = access;
    }

    public String getBodyStatus() {
        return bodyStatus;
    }

    public void setBodyStatus(String bodyStatus) {
        this.bodyStatus = bodyStatus;
    }

    public String getCheckInAddress() {
        return checkInAddress;
    }

    public void setCheckInAddress(String checkInAddress) {
        this.checkInAddress = checkInAddress;
    }

    public String getCheckInTime() {
        return checkInTime;
    }

    public void setCheckInTime(String checkInTime) {
        this.checkInTime = checkInTime;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getContactLikeVirus() {
        return contactLikeVirus;
    }

    public void setContactLikeVirus(String contactLikeVirus) {
        this.contactLikeVirus = contactLikeVirus;
    }

    public String getContactLikeVirusRegion() {
        return contactLikeVirusRegion;
    }

    public void setContactLikeVirusRegion(String contactLikeVirusRegion) {
        this.contactLikeVirusRegion = contactLikeVirusRegion;
    }

    public String getContactVirus() {
        return contactVirus;
    }

    public void setContactVirus(String contactVirus) {
        this.contactVirus = contactVirus;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getFromAddress() {
        return fromAddress;
    }

    public void setFromAddress(String fromAddress) {
        this.fromAddress = fromAddress;
    }

    public String getFromHB() {
        return fromHB;
    }

    public void setFromHB(String fromHB) {
        this.fromHB = fromHB;
    }

    public String getFromWH() {
        return fromWH;
    }

    public void setFromWH(String fromWH) {
        this.fromWH = fromWH;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getOperatorUsername() {
        return operatorUsername;
    }

    public void setOperatorUsername(String operatorUsername) {
        this.operatorUsername = operatorUsername;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getTraffic() {
        return traffic;
    }

    public void setTraffic(String traffic) {
        this.traffic = traffic;
    }
}
