package com.example.djapp.models;

public class Recommendation {
    String name, comment;


    public Recommendation(String name, String comment) {
        this.name = name;
        this.comment = comment;
    }


    public Recommendation() {

    }


    public void setName(String name) {
        this.name = name;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getName() {
        return name;
    }

    public String getComment() {
        return comment;
    }
}
