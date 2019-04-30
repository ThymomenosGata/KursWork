package org.wordy.kurswork.data.rests;

import com.google.gson.Gson;

import org.wordy.kurswork.data.PortalRest;
import org.wordy.kurswork.data.tables.Group;
import org.wordy.kurswork.data.tables.News;
import org.wordy.kurswork.data.tables.Professor;
import org.wordy.kurswork.data.tables.Result;
import org.wordy.kurswork.data.tables.Students;
import org.wordy.kurswork.data.tables.User;

import java.io.IOException;

public class PostInfo {

    private PortalRest mPortal;
    private Gson gson = new Gson();

    public PostInfo() {
        this.mPortal = PortalRest.getPortal();
    }

    public boolean insertGroup(Group group) {
        try {
            mPortal.post(gson.toJson(group), "newGroup").body().string();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean insertNews(News news) {
        try {
            mPortal.post(gson.toJson(news), "newNews").body().string();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean insertProfessor(Professor professor) {
        try {
            mPortal.post(gson.toJson(professor), "newProfessor").body().string();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean insertStudents(Students students) {
        try {
            mPortal.post(gson.toJson(students), "newStudent").body().string();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean insertUsers(User user) {
        try {
            mPortal.post(gson.toJson(user), "newUser").body().string();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Result newUsers(User user) {
        try {
            return gson.fromJson(mPortal.post(gson.toJson(user), "newUser").body().string(), Result.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}

