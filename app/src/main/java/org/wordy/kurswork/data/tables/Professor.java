package org.wordy.kurswork.data.tables;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import org.json.JSONException;
import org.json.JSONObject;

@Entity(
        tableName = "professor"
)
public class Professor {
    @PrimaryKey
    int id;
    String surname;
    String name;
    String middlename;
    String position;
    int experience;
    int userID;
    String date_last_modify;

    public Professor() {
    }

    @Ignore
    public Professor(int id, String surname, String name, String middlename, String position, int experience, int userID, String date_last_modify) {
        this.surname = surname;
        this.name = name;
        this.middlename = middlename;
        this.position = position;
        this.experience = experience;
        this.userID = userID;
        this.date_last_modify = date_last_modify;
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

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getDate_last_modify() {
        return date_last_modify;
    }

    public void setDate_last_modify(String date_last_modify) {
        this.date_last_modify = date_last_modify;
    }

    public static Professor fromJson(JSONObject json) throws JSONException {
        Professor professor = new Professor();
        professor.id = json.getInt("id");
        professor.surname = json.getString("surname");
        professor.name = json.getString("name");
        professor.middlename = json.getString("middlename");
        professor.position = json.getString("position");
        professor.experience = json.getInt("experience");
        professor.userID = json.getInt("user");
        professor.date_last_modify = json.getString("date_last_modify");
        return professor;
    }

}
