package org.wordy.kurswork.screens.group;

import android.app.Application;
import android.arch.lifecycle.LiveData;

import org.wordy.kurswork.data.DataBase;
import org.wordy.kurswork.data.rests.GetInfo;
import org.wordy.kurswork.data.tables.Group;

import java.util.List;

public class GroupModel implements GroupContract.Model {

    private DataBase dataBase;
    private GetInfo getInfo;
    private static List<Group> mCurrentGroup;

    public GroupModel(Application application) {
        this.dataBase = DataBase.getDataBase(application);
        this.getInfo = new GetInfo();
    }

    @Override
    public List<Group> getmCurrentGroup() {
        return mCurrentGroup;
    }

    @Override
    public LiveData<List<Group>> getData() {
        return dataBase.groupsDao().getAll();
    }

    @Override
    public Boolean updateGroups(Group group) {
        if (getInfo.updateGroup(group)) {
            dataBase.groupsDao().insert(group);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void setmCurrentGroup(List<Group> mCurrentGroup) {
        GroupModel.mCurrentGroup = mCurrentGroup;
    }

    @Override
    public Boolean getGroupsFromDB() {
        List<Group> groups = getInfo.selectGroup();
        for (Group group : groups) {
            dataBase.groupsDao().insert(group);
        }
        return true;
    }

}
