package com.example.dam_tuca_madalin_1079.classes;

public class GasStation {

    public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }

    private int imgId;
    private String name;
    private float petromPrice;
    private float dieselPrice;

    public GasStation(int imdId, String name, float petromPrice, float dieselPrice) {
        this.imgId = imdId;
        this.name = name;
        this.petromPrice = petromPrice;
        this.dieselPrice = dieselPrice;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPetromPrice() {
        return petromPrice;
    }

    public void setPetromPrice(float petromPrice) {
        this.petromPrice = petromPrice;
    }

    public float getDieselPrice() {
        return dieselPrice;
    }

    public void setDieselPrice(float dieselPrice) {
        this.dieselPrice = dieselPrice;
    }
}
