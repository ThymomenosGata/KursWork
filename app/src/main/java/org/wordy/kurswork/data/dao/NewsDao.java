package org.wordy.kurswork.data.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Update;

import org.wordy.kurswork.data.tables.News;

@Dao
public interface NewsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(News news);

    @Update
    void update(News news);

    @Delete
    void delete(News news);
}
