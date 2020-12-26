package com.example.dam_tuca_madalin_1079;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.dam_tuca_madalin_1079.classes.Car;

@Dao
public interface CarDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertCar(Car car);

    @Query("SELECT id FROM cars WHERE brand_col LIKE :brand AND model_col LIKE :model AND uId=:uId")
    int getCarId(String brand, String model, int uId);

    @Query("DELETE FROM cars WHERE id = :id")
    public void deleteCar(int id);

    @Query("SELECT COUNT(*) FROM cars WHERE uId = :uId")
    public int getCarCount(int uId);

    @Query("DELETE FROM cars")
    public void deleteAllCars();
}
