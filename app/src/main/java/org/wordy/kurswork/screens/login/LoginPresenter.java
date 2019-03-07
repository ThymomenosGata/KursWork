package org.wordy.kurswork.screens.login;

public class LoginPresenter implements LoginContract.Presenter {

    private LoginModel model;
    private LoginContract.View view;

    public LoginPresenter(LoginModel model, LoginContract.View view) {
        this.model = model;
        this.view = view;
    }

    @Override
    public void checkUser(String login, String password) {
        if (view.isOnline()) {
            if (model.getUserFromDB(login, password)) {
                view.navigateToMainActivity();
            } else {
                view.showDialog("Неверный логин или пароль");
            }
        } else {
            view.showDialog("Нет подключения к сети");
        }

    }

}
