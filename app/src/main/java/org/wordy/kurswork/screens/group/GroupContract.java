package org.wordy.kurswork.screens.group;

import org.wordy.kurswork.data.tables.Group;

import java.util.List;

public interface GroupContract {

    interface View {

    }

    interface Model {
        Boolean getGroupsFromDB();
        void setmCurrentUsers(List<Group> mCurrentGroup);
        List<Group> getmCurrentGroup();
    }

    interface Presenter {

    }

}
