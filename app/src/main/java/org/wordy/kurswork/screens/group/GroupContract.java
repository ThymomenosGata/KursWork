package org.wordy.kurswork.screens.group;

import android.arch.lifecycle.LiveData;

import org.wordy.kurswork.data.tables.Group;

import java.util.List;

public interface GroupContract {

    interface View {

        void setDataList(List<Group> Groups);

        void getData();

    }

    interface Model {
        Boolean getGroupsFromDB();

        void setmCurrentGroup(List<Group> mCurrentGroup);

        List<Group> getmCurrentGroup();

        LiveData<List<Group>> getData();
    }

    interface Presenter {

        void getGroupsFromDb();

        void setGroups(List<Group> groups);

        LiveData<List<Group>> getGroups();

    }

}
