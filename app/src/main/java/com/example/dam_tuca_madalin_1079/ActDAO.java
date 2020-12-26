package com.example.dam_tuca_madalin_1079;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;

import com.example.dam_tuca_madalin_1079.classes.Act;

import java.util.List;

@Dao
public interface ActDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertAct(Act act);

    @Transaction
    @Query("SELECT * FROM acts WHERE uId = :currentUid")
    public List<Act> getUserActs(int currentUid);

    @Query("SELECT COUNT(*) FROM acts WHERE uId = :uId")
    int getActsCount(int uId);

    @Transaction
    @Delete
    public void deleteAct(Act act);

}
