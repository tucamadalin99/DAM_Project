package com.example.dam_tuca_madalin_1079;
import androidx.annotation.NonNull;
import androidx.room.RoomDatabase;
import androidx.room.Database;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {User.class, Car.class, Act.class, DriverLicense.class}, version = 8)
public abstract class AppDb extends RoomDatabase {
    public abstract UserDAO userDAO();
}
