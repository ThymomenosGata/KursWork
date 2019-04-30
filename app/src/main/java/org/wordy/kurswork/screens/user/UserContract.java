package org.wordy.kurswork.screens.user;

import android.arch.lifecycle.LiveData;

import org.wordy.kurswork.data.tables.Result;
import org.wordy.kurswork.data.tables.User;

import java.util.List;

public interface UserContract {

    interface View {
        void setDataList(List<User> users);

        void getData();

        void showDialog(String message);

        void navigateToUpdate(int id);
    }

    interface Model {
        Boolean getUsersFromDB();

        void setmCurrentUsers(List<User> mCurrentUsers);

        List<User> getmCurrentUsers();

        LiveData<List<User>> getData();

        Result delUser(int id);

        void deleteUserInDb(User user);

    }

    interface Presenter {
        void getUsersFromDb();

        void setUsers(List<User> users);

        LiveData<List<User>> getUsers();
    }

}
