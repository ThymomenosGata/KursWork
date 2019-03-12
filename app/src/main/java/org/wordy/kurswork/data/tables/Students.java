package org.wordy.kurswork.data.tables;


import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import org.json.JSONException;
import org.json.JSONObject;

@Entity(
        tableName = "students"
)
public class Students {
    @PrimaryKey
    int id;
    String surname;
    String name;
    String middlename;
    int groupID;
    int avg_score;
    String date_last_modify;
    int userID;

    @Ignore
    public Students() {
    }

    public Students(int id, String surname, String name, String middlename, int groupID, int avg_score, String date_last_modify, int userID) {
        this.id = id;
        this.surname = surname;
        this.name = name;
        this.middlename = middlename;
        this.groupID = groupID;
        this.avg_score = avg_score;
        this.date_last_modify = date_last_modify;
        this.userID = userID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMiddlename() {
        return middlename;
    }

    public void setMiddlename(String middlename) {
        this.middlename = middlename;
    }

    public int getGroupID() {
        return groupID;
    }

    public void setGroupID(int groupID) {
        this.groupID = groupID;
    }

    public int getAvg_score() {
        return avg_score;
    }

    public void setAvg_score(int avg_score) {
        this.avg_score = avg_score;
    }

    public String getDate_last_modify() {
        return date_last_modify;
    }

    public void setDate_last_modify(String date_last_modify) {
        this.date_last_modify = date_last_modify;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public static Students fromJson(JSONObject json) throws JSONException {
        Students students = new Students();
        students.id = json.getInt("id");
        students.surname = json.getString("surname");
        students.name = json.getString("name");
        students.middlename = json.getString("middlename");
        students.groupID = json.getInt("group");
        students.avg_score = json.getInt("avg_score");
        students.userID = json.getInt("user");
        students.date_last_modify = json.getString("date_last_modify");
        return students;
    }
}
