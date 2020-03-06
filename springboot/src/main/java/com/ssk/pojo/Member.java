package com.ssk.pojo;

public class Member {
    private String id;
    private String address;
    private String age;
    private String area;
    private String city;
    private String card;
    private String type;
    private String name;
    private String nativ;
    private String phone;
    private String province;
    private String regtime;
    private String sex;
    private String status;

    @Override
    public String toString() {
        return "Member{" +
                "id='" + id + '\'' +
                ", address='" + address + '\'' +
                ", age='" + age + '\'' +
                ", area='" + area + '\'' +
                ", city='" + city + '\'' +
                ", card='" + card + '\'' +
                ", type='" + type + '\'' +
                ", name='" + name + '\'' +
                ", nativ='" + nativ + '\'' +
                ", phone='" + phone + '\'' +
                ", province='" + province + '\'' +
                ", regtime='" + regtime + '\'' +
                ", sex='" + sex + '\'' +
                ", status='" + status + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNativ() {
        return nativ;
    }

    public void setNativ(String nativ) {
        this.nativ = nativ;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getRegtime() {
        return regtime;
    }

    public void setRegtime(String regtime) {
        this.regtime = regtime;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
