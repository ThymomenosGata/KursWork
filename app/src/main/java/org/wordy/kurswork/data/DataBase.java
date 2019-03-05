package org.wordy.kurswork.data;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(
        entities = {},
        version = 1,
        exportSchema = false)
public abstract class DataBase extends RoomDatabase {

    private static DataBase INSTANCE;

    public static DataBase getINSTACE(Context context) {
        if (INSTANCE == null) {
            synchronized (DataBase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            DataBase.class, "org.wordy.kurswork.database")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
