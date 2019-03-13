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

    public Professor getProfessorByName(String name) {
        try {
            JSONArray jsonArray = new JSONArray(mPortal.post("{\"query\":\"select * from professor where name = \'" + name + "\'\"}").body().string());
            JSONObject json = jsonArray.getJSONObject(0);
            return Professor.fromJson(json);
        } catch (IOException | JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Students getStudentByName(String name) {
        try {
            JSONArray jsonArray = new JSONArray(mPortal.post("{\"query\":\"select * from students where name = \'" + name + "\'\"}").body().string());
            JSONObject json = jsonArray.getJSONObject(0);
            return Students.fromJson(json);
        } catch (IOException | JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Students delStudentbyId(int id) {
        try {
            JSONArray jsonArray = new JSONArray(mPortal.post("{\"query\":\"delete from students where id = " + id + "\"}").body().string());
            JSONObject json = jsonArray.getJSONObject(0);
            return Students.fromJson(json);
        } catch (IOException | JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public User delUserById(int id) {
        try {
            JSONArray jsonArray = new JSONArray(mPortal.post("{\"query\":\"delete from user where id = " + id + "\"}").body().string());
            JSONObject jsonObject = jsonArray.getJSONObject(0);
            return User.fromJson(jsonObject);
        } catch (IOException | JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Group delGroupById(int id) {
        try {
            JSONArray jsonArray = new JSONArray(mPortal.post("{\"query\":\"delete from `group` where id = " + id + "\"}").body().string());
            JSONObject jsonObject = jsonArray.getJSONObject(0);
            return Group.fromJson(jsonObject);
        } catch (IOException | JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Group delProfessorById(int id) {
        try {
            JSONArray jsonArray = new JSONArray(mPortal.post("{\"query\":\"delete from professor where id = " + id + "\"}").body().string());
            JSONObject jsonObject = jsonArray.getJSONObject(0);
            return Group.fromJson(jsonObject);
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

    public boolean updateUser(User user) {
        try {
            mPortal.post("{\"query\":\"update user set login = \'"
                    + user.getLogin() + "\', password = \'" + user.getPassword() + "\', is_blocked ="
                    + user.getIs_blocked() + ",date_last_modify =\'" + user.getDate_last_modify()
                    + "\' where id = " + user.getId() + "\"}").body().string();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateGroup(Group group) {
        try {
            mPortal.post("{\"query\":\"update `group` set name = \'"
                    + group.getName() + "\', count = " + group.getCount() + ", faculty =\'" + group.getFaculty()
                    + "\', date_last_modify = \'" + group.getDate_last_modify()
                    + "\' where id = " + group.getId() + "\"}").body().string();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
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

    public boolean updateProfessor(Professor professor) {
        try {
            mPortal.post("{\"query\" : \"update professor set surname = \'"+ professor.getSurname() + "\', name = \'"
                    + professor.getName() + "\', middlename = \'" + professor.getMiddlename() + "\', position = \'"
                    + professor.getPosition() + "\',experience = " + professor.getExperience() + ",user = " + professor.getUserID()
                    + ", date_last_modify = \'" + professor.getDate_last_modify() + "\' where id = " + professor.getId() + "\"}").body().string();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateStudents(Students students) {
        try {
            mPortal.post("{\"query\" : \"update students set surname = \'"+ students.getSurname() + "\', name = \'"
                    + students.getName() + "\', middlename = \'" + students.getMiddlename() + "\', `group` = "
                    + students.getGroupID() + ",avg_score = " + students.getAvg_score() + ", date_last_modify = \'"
                    + students.getDate_last_modify() + "\',user = " + students.getUserID() +  "where id = " + students.getId() + "\"}").body().string();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
