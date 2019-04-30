package org.wordy.kurswork.screens.user;

import android.app.Application;
import android.arch.lifecycle.LiveData;

import org.wordy.kurswork.data.DataBase;
import org.wordy.kurswork.data.PortalRest;
import org.wordy.kurswork.data.rests.DeleteInfo;
import org.wordy.kurswork.data.rests.GetInfo;
import org.wordy.kurswork.data.tables.Group;
import org.wordy.kurswork.data.tables.Result;
import org.wordy.kurswork.data.tables.User;

import java.util.List;

public class UserModel implements UserContract.Model {

    private DataBase dataBase;
    private GetInfo getInfo;
    private DeleteInfo deleteInfo;
    private static List<User> mCurrentUsers;

    public UserModel(Application application) {
        this.dataBase = DataBase.getDataBase(application);
        this.getInfo = new GetInfo();
        this.deleteInfo = new DeleteInfo();
    }

    @Override
    public List<User> getmCurrentUsers() {
        return mCurrentUsers;
    }

    @Override
    public void setmCurrentUsers(List<User> mCurrentUsers) {
        UserModel.mCurrentUsers = mCurrentUsers;
    }

    @Override
    public Boolean getUsersFromDB() {
        List<User> users = getInfo.selectUsers();
        for (User user : users) {
            dataBase.usersDao().insert(user);
        }
        return true;
    }

    @Override
    public LiveData<List<User>> getData() {
        return dataBase.usersDao().getAll();
    }

    @Override
    public Result delUser(int id) {
        return deleteInfo.delUserById(id);
    }

    @Override
    public void deleteUserInDb(User user) {
        dataBase.usersDao().delete(user);
    }

}
