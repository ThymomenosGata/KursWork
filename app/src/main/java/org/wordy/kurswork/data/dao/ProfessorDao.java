package org.wordy.kurswork.data.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Update;

import org.wordy.kurswork.data.tables.Professor;

@Dao
public interface ProfessorDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Professor professor);

    @Update
    void update(Professor professor);

    @Delete
    void delete(Professor professor);

}
