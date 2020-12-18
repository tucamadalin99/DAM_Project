package com.example.dam_tuca_madalin_1079.classes;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "acts")
public class Act {
    @PrimaryKey(autoGenerate = true)
    public int id;
    @ColumnInfo(name = "uId")
    public int uId;
    public int carId;
    private String type;
    private String brand;
    private String model;
    private String startDate;
    private String endDate;

    public Act(int uId, int carId, String type, String brand, String model, String startDate, String endDate) {
        this.uId = uId;
        this.carId = carId;
        this.type = type;
        this.brand = brand;
        this.model = model;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "Act{" +
                "id=" + id +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                '}';
    }
}
