package org.wordy.kurswork.screens.login;

import org.wordy.kurswork.data.rests.GetInfo;

public class LoginModel implements LoginContract.Model {

    private GetInfo getInfo;

    public LoginModel() {
        this.getInfo = new GetInfo();
    }

    @Override
    public Boolean getUserFromDB(String login, String password) {
        return getInfo.getSuccesAuth(login, password);
    }

}
