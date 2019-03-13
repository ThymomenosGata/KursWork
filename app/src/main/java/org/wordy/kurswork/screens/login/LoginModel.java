package org.wordy.kurswork.screens.login;

import android.app.Application;

import org.wordy.kurswork.data.DataBase;
import org.wordy.kurswork.data.rests.GetInfo;
import org.wordy.kurswork.data.tables.User;

public class LoginModel implements LoginContract.Model {

    private GetInfo getInfo;
    private DataBase dataBase;

    public LoginModel(Application application) {
        this.getInfo = new GetInfo();
        this.dataBase = DataBase.getDataBase(application);
    }

    @Override
    public Boolean getUserFromDB(String login, String password) {
        return getInfo.getSuccesAuth(login, password);
    }

    public User getUserLocal(String login) {
        return dataBase.usersDao().getUserByLogin(login);
    }

}
