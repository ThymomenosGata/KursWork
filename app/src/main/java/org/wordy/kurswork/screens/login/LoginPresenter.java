package org.wordy.kurswork.screens.login;

import android.annotation.SuppressLint;
import android.os.AsyncTask;

public class LoginPresenter implements LoginContract.Presenter {

    private LoginModel model;
    private LoginContract.View view;

    public LoginPresenter(LoginModel model, LoginContract.View view) {
        this.model = model;
        this.view = view;
    }

    @SuppressLint("StaticFieldLeak")
    @Override
    public void checkUser(final String login, final String password) {
        if (view.isOnline()) {
            new AsyncTask<Void, Void, Boolean>() {

                @Override
                protected Boolean doInBackground(Void... voids) {
                    return model.getUserFromDB(login, password);
                }

                @Override
                protected void onPostExecute(Boolean aBoolean) {
                    super.onPostExecute(aBoolean);
                    if (aBoolean) {
                        view.navigateToMainActivity();
                    } else {
                        view.showDialog("Неверный логин или пароль");
                    }
                }
            }.execute();
        } else {
            view.showDialog("Нет подключения к сети");
        }

    }

}
