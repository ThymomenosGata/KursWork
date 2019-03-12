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
            JSONArray jsonArray = new JSONArray(mPortal.post("{\"query\":\"select password from user where login = \'" + login + "\'\"}").body().string());
            JSONObject jsonObject = jsonArray.getJSONObject(0);
            return jsonObject.getString("password").equals(String.valueOf(password));
        } catch (IOException | JSONException e) {
            e.printStackTrace();
            return false;
        }
    }

    public User getUserByLogin(String login) {
        try {
            JSONArray jsonArray = new JSONArray(mPortal.post("{\"query\":\"select * from user where login = \'" + login + "\'\"}").body().string());
            JSONObject jsonObject = jsonArray.getJSONObject(0);
            return User.fromJson(jsonObject);
        } catch (IOException | JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Group getGroupByName(String name) {
        try {
            JSONArray jsonArray = new JSONArray(mPortal.post("{\"query\":\"select * from `group` where name = \'" + name + "\'\"}").body().string());
            JSONObject jsonObject = jsonArray.getJSONObject(0);
            return Group.fromJson(jsonObject);
        } catch (IOException | JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Group> selectGroup() {
        List<Group> groups = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(mPortal.post("{\"query\":\"select * from `group`\"}").body().string());
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
            JSONArray jsonArray = new JSONArray(mPortal.post("{\"query\":\"select * from news\"}").body().string());
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
            JSONArray jsonArray = new JSONArray(mPortal.post("{\"query\":\"select * from professor\"}").body().string());
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
            JSONArray jsonArray = new JSONArray(mPortal.post("{\"query\":\"select * from students\"}").body().string());
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
            JSONArray jsonArray = new JSONArray(mPortal.post("{\"query\":\"select * from user\"}").body().string());
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
