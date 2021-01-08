package com.example.xcustom;

import java.io.Serializable;

public class ItemCanvas implements Serializable {

    private String size;
    private String color;
    private String img;
    private String inputText;
    private Double price= 10.000;

    public ItemCanvas() {
    }

    public ItemCanvas(String size, String color, String img, String inputText) {
        this.size = size;
        this.color = color;
        this.img = img;
        this.inputText = inputText;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
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
