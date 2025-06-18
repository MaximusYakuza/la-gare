package com.example.lagare.models;

public class CartModel {

    private String image;         // URL o nombre de imagen
    private String name;          // Nombre del producto
    private String price;         // Precio
    private String rating;        // Calificación
    private String timing;        // Horario
    private String description;   // Descripción
    private long timestamp;       // ⏰ Marca de tiempo de la orden

    // 🔄 Constructor vacío obligatorio para Firebase
    public CartModel() {}

    // 🧱 Constructor con todos los campos
    public CartModel(String image, String name, String price, String rating, String timing, String description, long timestamp) {
        this.image = image;
        this.name = name;
        this.price = price;
        this.rating = rating;
        this.timing = timing;
        this.description = description;
        this.timestamp = timestamp;
    }

    // ✅ Getters y Setters
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

    public String getTiming() {
        return timing;
    }

    public void setTiming(String timing) {
        this.timing = timing;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
