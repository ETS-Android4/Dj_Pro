package com.example.djapp.models;

public class Publish {

    private String name;
    private String phone;
    private String freeText;
    private String creditcard;
    private Integer price;


    public Publish(String name, String phone, String freeText, String creditcard, Integer price) {
        this.name = name;
        this.phone = phone;
        this.freeText = freeText;
        this.creditcard = creditcard;
        this.price = price;
    }

    public Publish(){

    }

    public void setCreditcard(String creditcard) {
        this.creditcard = creditcard;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getCreditcard() {
        return creditcard;
    }

    public Integer getPrice() {
        return price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setFreeText(String freeText) {
        this.freeText = freeText;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getFreeText() {
        return freeText;
    }
}
