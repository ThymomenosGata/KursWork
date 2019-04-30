package org.wordy.kurswork.screens.group;

import android.app.Application;
import android.arch.lifecycle.LiveData;

import org.wordy.kurswork.data.DataBase;
import org.wordy.kurswork.data.rests.DeleteInfo;
import org.wordy.kurswork.data.rests.GetInfo;
import org.wordy.kurswork.data.rests.UpdateInfo;
import org.wordy.kurswork.data.tables.Group;
import org.wordy.kurswork.data.tables.Result;

import java.util.List;

public class GroupModel implements GroupContract.Model {

    private DataBase dataBase;
    private GetInfo getInfo;
    private DeleteInfo deleteInfo;
    private static List<Group> mCurrentGroup;

    public GroupModel(Application application) {
        this.dataBase = DataBase.getDataBase(application);
        this.getInfo = new GetInfo();
        this.deleteInfo = new DeleteInfo();
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

    public Result delGroup(int id) {
        return deleteInfo.delGroupById(id);
    }

    public void deleteGroupInDb(Group group) {
        dataBase.groupsDao().delete(group);
    }

}
