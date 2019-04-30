package org.wordy.kurswork.data.rests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.wordy.kurswork.data.PortalRest;
import org.wordy.kurswork.data.tables.Group;
import org.wordy.kurswork.data.tables.News;
import org.wordy.kurswork.data.tables.Professor;
import org.wordy.kurswork.data.tables.Result;
import org.wordy.kurswork.data.tables.Students;
import org.wordy.kurswork.data.tables.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GetInfo {

    private PortalRest mPortal;
    private Gson gson;

    public GetInfo() {
        this.mPortal = PortalRest.getPortal();
        gson = new Gson();
    }

    public Result getSuccesAuth(User user) {
        try {
            return gson.fromJson(mPortal.get("getSuccesAuth", gson.toJson(user)).body().string(), Result.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public User getUserByLogin(String login) {
        try {
            return gson.fromJson(mPortal.get("getUserByLogin", login).body().string(), User.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Professor getProfessorByName(String name) {
        try {
            return gson.fromJson(mPortal.get("getProfessorByName", name).body().string(), Professor.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Students getStudentByName(String name) {
        try {
            return gson.fromJson(mPortal.get("getStudentByName", name).body().string(), Students.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Group getGroupByName(String name) {
        try {
            return gson.fromJson(mPortal.get("getGroupByName", name).body().string(), Group.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public News getNewsByName(String title) {
        try {
            return gson.fromJson(mPortal.get("getNewsByName", title).body().string(), News.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Group> selectGroup() {
        try {
            return gson.fromJson(mPortal.get("getGroups").body().string(), new TypeToken<ArrayList<Group>>() {
            }.getType());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<News> selectNews() {
        try {
            return gson.fromJson(mPortal.get("getNews").body().string(), new TypeToken<ArrayList<News>>() {
            }.getType());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Professor> selectProfessor() {
        try {
            return gson.fromJson(mPortal.get("getProfessors").body().string(), new TypeToken<ArrayList<Professor>>() {
            }.getType());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Students> selectStudents() {
        try {
            return gson.fromJson(mPortal.get("getStudents").body().string(), new TypeToken<ArrayList<Students>>() {
            }.getType());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<User> selectUsers() {
        try {
            return gson.fromJson(mPortal.get("getUsers").body().string(), new TypeToken<ArrayList<User>>() {
            }.getType());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
