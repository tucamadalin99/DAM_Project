package com.example.dam_tuca_madalin_1079.classes;

import java.util.Date;

public class Consumable {
    public int uId;
    private String type;
    private float price;

    public Consumable(int uId, String type, float price) {
        this.uId = uId;
        this.type = type;
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
