package com.example.dam_tuca_madalin_1079;

import java.util.Date;
import java.util.List;

public class Car {
    private String brand;
    private String model;
    private int doors;
    private String carBodyType;
    private String color;
    private Date producedDate;
    private String fuelType;
    private int cilindricCapacity;
    private int hp;
    private List<Act> acts;
    private List<Consumable> consumables;

    public Car() {
        this.brand = "";
        this.model = "";
        this.doors = 0;
        this.carBodyType = "";
        this.color = "";
        this.producedDate = null;
        this.fuelType = "";
        this.cilindricCapacity = 0;
        this.hp = 0;
    }

    public Car(String brand, String model, int doors, String carBodyType, String color, Date producedDate, String fuelType, int cilindricCapacity, int hp) {
        this.brand = brand;
        this.model = model;
        this.doors = doors;
        this.carBodyType = carBodyType;
        this.color = color;
        this.producedDate = producedDate;
        this.fuelType = fuelType;
        this.cilindricCapacity = cilindricCapacity;
        this.hp = hp;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getDoors() {
        return doors;
    }

    public void setDoors(int doors) {
        this.doors = doors;
    }

    public String getCarBodyType() {
        return carBodyType;
    }

    public void setCarBodyType(String carBodyType) {
        this.carBodyType = carBodyType;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Date getProducedDate() {
        return producedDate;
    }

    public void setProducedDate(Date producedDate) {
        this.producedDate = producedDate;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public int getCilindricCapacity() {
        return cilindricCapacity;
    }

    public void setCilindricCapacity(int cilindricCapacity) {
        this.cilindricCapacity = cilindricCapacity;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public List<Act> getActs() {
        return acts;
    }

    public void setActs(List<Act> acts) {
        this.acts = acts;
    }

    public List<Consumable> getConsumables() {
        return consumables;
    }

    public void setConsumables(List<Consumable> consumables) {
        this.consumables = consumables;
    }
}
