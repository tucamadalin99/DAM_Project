package com.example.dam_tuca_madalin_1079;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class UserWithCars {
    @Embedded public User user;
    @Relation(parentColumn = "id", entityColumn = "uId")
    public List<Car> cars;
    @Relation(parentColumn = "id", entityColumn = "uId")
    public List<Act> acts;
}
