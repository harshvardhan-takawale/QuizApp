package com.example.harshvardhan.quizclubapp;

/**
 * Created by lenovo on 7/13/2017.
 */

public class MessageForm {

    private String text;
    private String name;
    private String photoUrl;

    public MessageForm() {
    }

    public MessageForm(String text, String name) {
        this.text = text;
        this.name = name;
        this.photoUrl = null;

    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }
}