package org.wordy.kurswork.screens.login;

import android.annotation.SuppressLint;
import android.os.AsyncTask;

import org.wordy.kurswork.data.tables.Result;
import org.wordy.kurswork.data.tables.User;

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
            new AsyncTask<Void, Void, Result>() {

                @Override
                protected Result doInBackground(Void... voids) {
                    return model.getUserFromDB(login, password);
                }

                @Override
                protected void onPostExecute(Result result) {
                    super.onPostExecute(result);
                    if (result.getMessage().equals("successful")) {
                        view.navigateToMainActivity();
                    } else {
                        view.showDialog(result.getMessage());
                    }
                }
            }.execute();
        } else {
            new AsyncTask<Void, Void, User>() {

                @Override
                protected User doInBackground(Void... voids) {
                    return model.getUserLocal(login);
                }

                @Override
                protected void onPostExecute(User user) {
                    super.onPostExecute(user);
                    if(user != null) {
                        if (!password.equals(user.getPassword())) {
                            view.showDialog("Неверный логин или пароль");
                        } else {
                            view.navigateToMainActivity();
                        }
                    } else {
                        view.showDialog("Войдите, когда появится интернет");
                    }
                }
            }.execute();
        }
    }

    @Override
    public void navigateToRegistration() {
        view.navigateToRegistrationActivity();
    }

}
