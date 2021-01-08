package com.example.xcustom;

import android.widget.ImageView;

import java.io.Serializable;

public class ItemHoodie implements Serializable {

    private String size;
    private String color;
    private String img;
    private String inputText;
    private Double price= 15.000;

    public ItemHoodie() {
    }

    public ItemHoodie(String size, String inputText, String color, String img) {
        this.size = size;
        this.color = color;
        this.img = img;
        this.inputText = inputText;

    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getInputText() {
        return inputText;
    }

    public void setInputText(String inputText) {
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

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public void setColor(String color) {
        this.color = color;
    }


}
