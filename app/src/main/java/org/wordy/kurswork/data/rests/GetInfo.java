package org.wordy.kurswork.data.rests;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.wordy.kurswork.data.PortalRest;
import org.wordy.kurswork.data.tables.Group;
import org.wordy.kurswork.data.tables.News;
import org.wordy.kurswork.data.tables.Professor;
import org.wordy.kurswork.data.tables.Students;
import org.wordy.kurswork.data.tables.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GetInfo {

    private PortalRest mPortal;

    public GetInfo() {
        this.mPortal = PortalRest.getPortal();
    }

    public boolean getSuccesAuth(String login, String password) {
        try {
            return mPortal.get("select password from user where login = " + login).body().string().equals(password);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Group> selectGroup() {
        List<Group> groups = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(mPortal.get("select * from group").body().string());
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject json = jsonArray.getJSONObject(i);
                groups.add(Group.fromJson(json));
            }
            return groups;
        } catch (IOException | JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<News> selectNews() {
        List<News> news = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(mPortal.get("select * from news").body().string());
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject json = jsonArray.getJSONObject(i);
                news.add(News.fromJson(json));
            }
            return news;
        } catch (IOException | JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Professor> selectProfessor() {
        List<Professor> professors = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(mPortal.get("select * from professor").body().string());
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject json = jsonArray.getJSONObject(i);
                professors.add(Professor.fromJson(json));
            }
            return professors;
        } catch (IOException | JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Students> selectStudents() {
        List<Students> students = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(mPortal.get("select * from students").body().string());
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject json = jsonArray.getJSONObject(i);
                students.add(Students.fromJson(json));
            }
            return students;
        } catch (IOException | JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<User> selectUsers() {
        List<User> users = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(mPortal.get("select * from user").body().string());
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject json = jsonArray.getJSONObject(i);
                users.add(User.fromJson(json));
            }
            return users;
        } catch (IOException | JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

}
