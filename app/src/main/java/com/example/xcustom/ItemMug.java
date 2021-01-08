package com.example.xcustom;

import java.io.Serializable;

public class ItemMug implements Serializable {


    private String color;
    private String img;
    private String inputText;
    private Double price= 7.000;

    public ItemMug() {
    }

    public ItemMug(String color, String img, String inputText) {
        this.color = color;
        this.img = img;
        this.inputText = inputText;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getInputText() {
        return inputText;
    }

    public void setInputText(String inputText) {
        this.inputText = inputText;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
