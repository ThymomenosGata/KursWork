package org.wordy.kurswork.data.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import org.wordy.kurswork.data.tables.Students;

import java.util.List;

@Dao
public interface StudentsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Students students);

    @Update
    void update(Students students);

    @Delete
    void delete(Students students);

    @Query("Select * from students")
    LiveData<List<Students>> getAll();
}
