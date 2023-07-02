package com.example.foodapplogin.models;

public class Dish {
    String DName;
    String DPrice;
    String DRating;
    String DTime;
    String Dimg;
    String RID;

    public Dish() {
    }

    public Dish(String DName, String DPrice, String DRating, String DTime, String dimg, String RID) {
        this.DName = DName;
        this.DPrice = DPrice;
        this.DRating = DRating;
        this.DTime = DTime;
        Dimg = dimg;
        this.RID = RID;
    }

    public String getDName() {
        return DName;
    }

    public void setDName(String DName) {
        this.DName = DName;
    }

    public String getDPrice() {
        return DPrice;
    }

    public void setDPrice(String DPrice) {
        this.DPrice = DPrice;
    }

    public String getDRating() {
        return DRating;
    }

    public void setDRating(String DRating) {
        this.DRating = DRating;
    }

    public String getDTime() {
        return DTime;
    }

    public void setDTime(String DTime) {
        this.DTime = DTime;
    }

    public String getDimg() {
        return Dimg;
    }

    public void setDimg(String dimg) {
        Dimg = dimg;
    }

    public String getRID() {
        return RID;
    }

    public void setRID(String RID) {
        this.RID = RID;
    }
}
