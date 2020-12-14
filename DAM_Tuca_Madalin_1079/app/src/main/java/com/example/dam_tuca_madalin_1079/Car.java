package com.example.dam_tuca_madalin_1079;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;
import androidx.room.Relation;

import java.util.Date;
import java.util.List;
@Entity(tableName = "cars")
public class Car {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public int uId;
    @ColumnInfo(name = "brand_col")
    private String brand;
    @ColumnInfo(name = "model_col")
    private String model;
    private String carBodyType;
    private int producedYear;
    private String fuelType;
    private int cilindricCapacity;


    public Car(int uId, String brand, String model, String carBodyType, int producedYear, String fuelType, int cilindricCapacity) {
        this.brand = brand;
        this.uId = uId;
        this.model = model;
        this.carBodyType = carBodyType;
        this.producedYear = producedYear;
        this.fuelType = fuelType;
        this.cilindricCapacity = cilindricCapacity;
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

    public String getCarBodyType() {
        return carBodyType;
    }

    public void setCarBodyType(String carBodyType) {
        this.carBodyType = carBodyType;
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

    public int getProducedYear() {
        return producedYear;
    }

    public void setProducedYear(int producedYear) {
        this.producedYear = producedYear;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", uId=" + uId +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", carBodyType='" + carBodyType + '\'' +
                ", producedYear=" + producedYear +
                ", fuelType='" + fuelType + '\'' +
                ", cilindricCapacity=" + cilindricCapacity +
                '}';
    }
}
