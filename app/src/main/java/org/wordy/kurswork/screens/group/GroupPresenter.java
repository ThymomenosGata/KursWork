package org.wordy.kurswork.screens.group;

import android.annotation.SuppressLint;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import org.wordy.kurswork.data.tables.Group;
import org.wordy.kurswork.data.tables.Result;

import java.util.List;

public class GroupPresenter implements GroupContract.Presenter {

    private GroupModel model;
    private GroupContract.View view;

    public GroupPresenter(GroupModel model, GroupContract.View view) {
        this.model = model;
        this.view = view;
    }

    @SuppressLint("StaticFieldLeak")
    @Override
    public void getGroupsFromDb() {
        new AsyncTask<Void, Void, Boolean>() {
            @Override
            protected Boolean doInBackground(Void... voids) {
                return model.getGroupsFromDB();
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
    public void setGroups(List<Group> groups) {
        model.setmCurrentGroup(groups);
        view.setDataList(model.getmCurrentGroup());
    }

    @Override
    public LiveData<List<Group>> getGroups() {
        return model.getData();
    }

    @SuppressLint("StaticFieldLeak")
    public void update(Group group) {
        view.navigateToUpdate(group.getId());
    }

    @SuppressLint("StaticFieldLeak")
    public void delete(Group group) {
        new AsyncTask<Void, Void, Result>() {

            @Override
            protected Result doInBackground(Void... voids) {
                Result result = model.delGroup(group.getId());
                if (result.getMessage().equals("successful")) {
                    model.deleteGroupInDb(group);
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
