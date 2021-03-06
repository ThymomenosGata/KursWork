package org.wordy.kurswork.data.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import org.wordy.kurswork.data.tables.User;

import java.util.List;

@Dao
public interface UsersDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(User user);

    @Update
    void update(User user);

    @Delete
    void delete(User user);

    @Query("Select * from user")
    LiveData<List<User>> getAll();

    @Query("Select * from user where id = :id")
    User getUserById(int id);

    @Query("Select * from user where login = :login")
    User getUserByLogin(String login);

    @Query("Delete from user")
    void deleteAll();

}
