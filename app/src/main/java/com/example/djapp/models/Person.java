package com.example.djapp.models;

public class Person {

    String name;
    String email;
    String phone;
    String account;
    String id;
    String genre;

    public Person(String name, String email, String phone, String account, String id, String genre) {
        this.name = name;
        this.account = account;
        this.email = email;
        this.phone = phone;
        this.id = id;
        this.genre = genre;
    }

    public Person() {

    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getId() {return id; }

    public String getName() {
        return name;
    }

    public void setId(String id) { this.id = id; }

    public String getAccount() {
        return account;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAccount(String id) {
        this.account = account;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}


