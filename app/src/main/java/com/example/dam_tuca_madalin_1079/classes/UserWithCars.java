package com.example.dam_tuca_madalin_1079.classes;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.example.dam_tuca_madalin_1079.classes.Act;
import com.example.dam_tuca_madalin_1079.classes.Car;
import com.example.dam_tuca_madalin_1079.classes.User;

import java.util.List;

public class UserWithCars {
    @Embedded public User user;
    @Relation(parentColumn = "id", entityColumn = "uId")
    public List<Car> cars;
    @Relation(parentColumn = "id", entityColumn = "uId")
    public List<Act> acts;
}
