package com.example.lagare.models;

public class DetailedDailyModel {

    private int image;  // Cambiado de String a int ✅
    private String name;
    private String description;
    private String rating;
    private String price;
    private String timing;

    // 🔥 Constructor vacío para Firebase
    public DetailedDailyModel() {}

    // Constructor con todos los campos
    public DetailedDailyModel(int image, String name, String description, String rating, String price, String timing) {
        this.image = image;
        this.name = name;
        this.description = description;
        this.rating = rating;
        this.price = price;
        this.timing = timing;
    }

    // Getters y Setters
    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getTiming() {
        return timing;
    }

    public void setTiming(String timing) {
        this.timing = timing;
    }
}
