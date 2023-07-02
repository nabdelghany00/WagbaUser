package com.example.foodapplogin.models;

public class CartModel {
    String image;
    String name;
    String price;
    String rating;

    public CartModel() {
    }

    public CartModel(String image, String name, String price, String rating) {
        this.image = image;
        this.name = name;
        this.price = price;
        this.rating = rating;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }
}
