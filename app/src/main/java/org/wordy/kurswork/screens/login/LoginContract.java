package org.wordy.kurswork.screens.login;

import org.wordy.kurswork.data.tables.Result;

public interface LoginContract {

    interface View {
        void showDialog(String message);
        void navigateToMainActivity();
        boolean isOnline();
        void navigateToRegistrationActivity();
    }

    interface Model {
        Result getUserFromDB(String login, String password);
    }

    interface Presenter {
        void checkUser(String login, String password);
        void navigateToRegistration();
    }

}
