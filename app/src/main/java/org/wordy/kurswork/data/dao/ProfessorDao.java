package org.wordy.kurswork.data.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import org.wordy.kurswork.data.tables.Professor;

import java.util.List;

@Dao
public interface ProfessorDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Professor professor);

    @Update
    void update(Professor professor);

    @Delete
    void delete(Professor professor);

    @Query("Select * from professor")
    LiveData<List<Professor>> getAll();

    @Query("Select * from professor where id = :id")
    Professor getProfessorById(int id);

}
