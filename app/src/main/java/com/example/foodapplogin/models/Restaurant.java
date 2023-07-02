package com.example.foodapplogin.models;

public class Restaurant {
    String ResName,ResImg;

    public Restaurant() {
    }

    public Restaurant(String resName, String resImg) {
        ResName = resName;
        ResImg = resImg;
    }

    public String getResName() {
        return ResName;
    }

    public void setResName(String resName) {
        ResName = resName;
    }

    public String getResImg() {
        return ResImg;
    }

    public void setResImg(String resImg) {
        ResImg = resImg;
    }
}
