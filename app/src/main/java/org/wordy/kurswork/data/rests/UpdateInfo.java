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

public class UpdateInfo {

    private PortalRest mPortal;
    private Gson gson;

    public UpdateInfo() {
        this.mPortal = PortalRest.getPortal();
        this.gson = new Gson();
    }

    public Result updateUser(User user) {
        try {
            return gson.fromJson(mPortal.update(gson.toJson(user),"updateUser").body().string(), Result.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Result updateGroup(Group group) {
        try {
            return gson.fromJson(mPortal.update(gson.toJson(group),"updateGroup").body().string(), Result.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Result updateProfessor(Professor professor) {
        try {
            return gson.fromJson(mPortal.update(gson.toJson(professor),"updateProfessor").body().string(), Result.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Result updateStudents(Students students) {
        try {
            return gson.fromJson(mPortal.update(gson.toJson(students),"updateStudent").body().string(), Result.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Result updateNews(News news) {
        try {
            return gson.fromJson(mPortal.update(gson.toJson(news),"updateNews").body().string(), Result.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
