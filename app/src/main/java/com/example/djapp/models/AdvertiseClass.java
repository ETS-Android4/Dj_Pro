package com.example.djapp.models;

public class AdvertiseClass {
    String name;
    String phone;
    String freeText;
    Integer price;
    String creditCard;
    public static int priceCounter = 0;


    public AdvertiseClass(String name, String phone, String freeText, String creditCard) {
        this.name = name;
        this.phone = phone;
        this.freeText = freeText;
        this.creditCard = creditCard;
        this.price = priceCounter + 100;
    }

    public AdvertiseClass() {

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

    public Integer getPrice() {
        return price;
    }

    public String getCreditCard() {
        return creditCard;
    }

    public static int getPriceCounter() {
        return priceCounter;
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

    public void setPrice(Integer price) {
        this.price = price;
    }

    public void setCreditCard(String creditCard) {
        this.creditCard = creditCard;
    }

    public static void setPriceCounter(int priceCounter) {
        AdvertiseClass.priceCounter = priceCounter;
    }
}
