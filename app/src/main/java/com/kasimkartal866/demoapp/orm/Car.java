package com.kasimkartal866.demoapp.orm;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class Car {
    @PrimaryKey(autoGenerate = true)
    public int id;
    private String model;
    private String brand;
    private String km;
    private String color;
    private String imageAddress;
    private int userId;

    public Car() {
    }

    @Ignore
    public Car(String model, String brand, String km, String color, String imageAddress, int userId) {
        this.model = model;
        this.brand = brand;
        this.km = km;
        this.color = color;
        this.imageAddress = imageAddress;
        this.userId = userId;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getKm() {
        return km;
    }

    public void setKm(String km) {
        this.km = km;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getImageAddress() {
        return imageAddress;
    }

    public void setImageAddress(String imageAddress) {
        this.imageAddress = imageAddress;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

}
