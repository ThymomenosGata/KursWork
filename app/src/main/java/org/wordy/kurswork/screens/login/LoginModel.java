package org.wordy.kurswork.screens.login;

import android.app.Application;

import org.wordy.kurswork.data.DataBase;
import org.wordy.kurswork.data.rests.GetInfo;
import org.wordy.kurswork.data.tables.Result;
import org.wordy.kurswork.data.tables.User;

public class LoginModel implements LoginContract.Model {

    private GetInfo getInfo;
    private DataBase dataBase;

    public LoginModel(Application application) {
        this.getInfo = new GetInfo();
        this.dataBase = DataBase.getDataBase(application);
    }

    @Override
    public Result getUserFromDB(String login, String password) {
        User user = new User(login, password);
        return getInfo.getSuccesAuth(user);
    }

    public User getUserLocal(String login) {
        return dataBase.usersDao().getUserByLogin(login);
    }

}
