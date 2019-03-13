package org.wordy.kurswork.screens.user;

import android.annotation.SuppressLint;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

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
        new AsyncTask<Void, Void, Boolean>() {

            @Override
            protected Boolean doInBackground(Void... voids) {
                return model.updateUsers(user);
            }

            @Override
            protected void onPostExecute(Boolean aBoolean) {
                super.onPostExecute(aBoolean);

            }
        }.execute();
    }


}
