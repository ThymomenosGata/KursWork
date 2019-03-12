package org.wordy.kurswork.data.rests;

import org.wordy.kurswork.data.PortalRest;
import org.wordy.kurswork.data.tables.Group;

import java.io.IOException;

public class PostInfo {

    //TODO: доделать епту нада

    private PortalRest mPortal;

    public PostInfo() {
        this.mPortal = PortalRest.getPortal();
    }

    public boolean insertGroup(Group group) {
        try {
            mPortal.post("insert into group(id, name, count, faculty, date_last_modify) " +
                    "values(nextVal('id')," + group.getName() + "," + group.getCount() + "," + group.getFaculty() + ","
                    + group.getDate_last_modify() + ")").body().string();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean insertNews() {
        try {
            mPortal.post("insert into group() values()").body().string();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean insertProfessor() {
        try {
            mPortal.post("insert into group() values()").body().string();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean insertStudents() {
        try {
            mPortal.post("insert into group() values()").body().string();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean insertUsers() {
        try {
            mPortal.post("insert into group() values()").body().string();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

}
