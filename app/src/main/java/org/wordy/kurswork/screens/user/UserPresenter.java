package org.wordy.kurswork.screens.user;

import android.annotation.SuppressLint;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import org.wordy.kurswork.data.tables.Group;
import org.wordy.kurswork.data.tables.Result;
import org.wordy.kurswork.data.tables.User;

import java.util.List;

public class UserPresenter implements UserContract.Presenter {

    private UserModel model;
    private UserContract.View view;

    public UserPresenter(UserModel model, UserContract.View view) {
        this.model = model;
        this.view = view;
    }

    @SuppressLint("StaticFieldLeak")
    @Override
    public void getUsersFromDb() {
        new AsyncTask<Void, Void, Boolean>() {
            @Override
            protected Boolean doInBackground(Void... voids) {
                return model.getUsersFromDB();
            }

            @Override
            protected void onPostExecute(Boolean aBoolean) {
                super.onPostExecute(aBoolean);
                if (aBoolean) {
                    view.getData();
                }
            }
        }.execute();
    }

    @Override
    public void setUsers(List<User> users) {
        model.setmCurrentUsers(users);
        view.setDataList(model.getmCurrentUsers());
    }

    @Override
    public LiveData<List<User>> getUsers() {
        return model.getData();
    }

    @SuppressLint("StaticFieldLeak")
    public void update(User user) {
        view.navigateToUpdate(user.getId());
    }

    @SuppressLint("StaticFieldLeak")
    public void delete(User user) {
        new AsyncTask<Void, Void, Result>() {

            @Override
            protected Result doInBackground(Void... voids) {
                Result result = model.delUser(user.getId());
                if (result.getMessage().equals("successful")) {
                    model.deleteUserInDb(user);
                }
                return result;
            }

            @Override
            protected void onPostExecute(Result result) {
                super.onPostExecute(result);
                if (!result.getMessage().equals("successful")) {
                    view.showDialog(result.getMessage());
                }
            }
        }.execute();
    }

}
