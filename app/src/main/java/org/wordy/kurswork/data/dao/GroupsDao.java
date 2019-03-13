package org.wordy.kurswork.data.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import org.wordy.kurswork.data.tables.Group;

import java.util.List;

@Dao
public interface GroupsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Group group);

    @Update
    void update(Group group);

    @Delete
    void delete(Group group);

    @Query("Select * from `group`")
    LiveData<List<Group>> getAll();

    @Query("Select * from `group` where id = :id")
    Group getUserById(int id);
}
