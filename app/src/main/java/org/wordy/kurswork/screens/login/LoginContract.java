package org.wordy.kurswork.screens.login;

public interface LoginContract {

    interface View {
        void showDialog(String message);
        void navigateToMainActivity();
        boolean isOnline();
    }

    interface Model {
        Boolean getUserFromDB(String login, String password);
    }

    interface Presenter {
        void checkUser(String login, String password);
    }

}
