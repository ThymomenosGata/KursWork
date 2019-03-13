package org.wordy.kurswork.screens.user;

import android.arch.lifecycle.LiveData;

import org.wordy.kurswork.data.tables.User;

import java.util.List;

public interface UserContract {

    interface View {
        void setDataList(List<User> users);

        void getData();
    }

    interface Model {
        Boolean getUsersFromDB();

        void setmCurrentUsers(List<User> mCurrentUsers);

        List<User> getmCurrentUsers();

        LiveData<List<User>> getData();

        Boolean updateUsers(User user);
    }

    interface Presenter {
        void getUsersFromDb();

        void setUsers(List<User> users);

        LiveData<List<User>> getUsers();
    }

}
