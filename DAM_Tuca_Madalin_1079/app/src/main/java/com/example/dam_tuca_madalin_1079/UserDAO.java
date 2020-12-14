package com.example.dam_tuca_madalin_1079;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;

import java.util.List;

@Dao
public interface UserDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertUser(User user);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertCar(Car car);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertAct(Act act);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertLicense(DriverLicense license);

    @Delete
    void deleteUser(User user);

    @Query("SELECT * FROM users WHERE email_col LIKE :email AND pass_col LIKE :pass")
    User getLoginUser(String email, String pass);

    @Query("SELECT * FROM users WHERE id = :currentUid")
    User getUserById(int currentUid);

    @Query("SELECT id FROM cars WHERE brand_col LIKE :brand AND model_col LIKE :model AND uId=:uId")
    int getCarId(String brand, String model, int uId);

    @Query("UPDATE users SET pass_col = :newPass WHERE id = :currentUid")
    void updatePassword(String newPass, int currentUid);

    @Query("UPDATE users SET name_col = :newName, surname_col = :newSurname, email_col = :newEmail WHERE id = :currentUid")
    void updateUserData(String newName, String newSurname, String newEmail, int currentUid);

    @Query("SELECT COUNT(*) FROM licenses WHERE uId_col = :uId")
    int getDriverLicenseCount(int uId);

    @Query("SELECT * FROM licenses WHERE uId_col = :uId")
    DriverLicense getDriverLicense(int uId);

    @Transaction
    @Query("SELECT * FROM users")
    public List<UserWithCars> getUsersWithCars();

    @Transaction
    @Query("SELECT * FROM users WHERE id = :currentUid")
    public UserWithCars getUserCars(int currentUid);

    @Transaction
    @Query("SELECT * FROM acts WHERE uId = :currentUid")
    public List<Act> getUserActs(int currentUid);



}
