package org.wordy.kurswork.data;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import org.wordy.kurswork.data.dao.GroupsDao;
import org.wordy.kurswork.data.dao.NewsDao;
import org.wordy.kurswork.data.dao.ProfessorDao;
import org.wordy.kurswork.data.dao.StudentsDao;
import org.wordy.kurswork.data.dao.UsersDao;
import org.wordy.kurswork.data.tables.Group;
import org.wordy.kurswork.data.tables.News;
import org.wordy.kurswork.data.tables.Professor;
import org.wordy.kurswork.data.tables.Students;
import org.wordy.kurswork.data.tables.User;

@Database(
        entities = {
                User.class,
                Professor.class,
                Students.class,
                Group.class,
                News.class
        },
        version = 1,
        exportSchema = false)
public abstract class DataBase extends RoomDatabase {

    private static DataBase INSTANCE;

    public abstract UsersDao usersDao();

    public abstract ProfessorDao professorDao();

    public abstract StudentsDao studentsDao();

    public abstract GroupsDao groupsDao();

    public abstract NewsDao newsDao();

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
