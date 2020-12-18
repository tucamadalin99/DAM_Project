package com.example.dam_tuca_madalin_1079;
import androidx.room.RoomDatabase;
import androidx.room.Database;

import com.example.dam_tuca_madalin_1079.classes.Act;
import com.example.dam_tuca_madalin_1079.classes.Car;
import com.example.dam_tuca_madalin_1079.classes.DriverLicense;
import com.example.dam_tuca_madalin_1079.classes.User;

@Database(entities = {User.class, Car.class, Act.class, DriverLicense.class}, version = 8)
public abstract class AppDb extends RoomDatabase {
    public abstract UserDAO userDAO();
    public abstract CarDAO carDAO();
    public abstract  ActDAO actDAO();
}
