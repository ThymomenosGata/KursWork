package org.wordy.kurswork.screens.user;

import org.wordy.kurswork.data.tables.User;

import java.util.List;

public interface UserContract {

    interface View {

    }

    interface Model {
        Boolean getUsersFromDB();
        Boolean getUsers();
        void setmCurrentUsers(List<User> mCurrentUsers);
        List<User> getmCurrentUsers();
    }

    interface Presenter {

    }

}
