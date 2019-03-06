package org.wordy.kurswork.screens.group;

import android.app.Application;

import org.wordy.kurswork.data.DataBase;
import org.wordy.kurswork.data.connect.GetInfo;
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
    public void setmCurrentUsers(List<Group> mCurrentGroup) {
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
