package org.wordy.kurswork.data.tables;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

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
    String datetime;

    public Professor(int id, String surname, String name, String middlename, String position, int experience, int userID, String datetime) {
        this.id = id;
        this.surname = surname;
        this.name = name;
        this.middlename = middlename;
        this.position = position;
        this.experience = experience;
        this.userID = userID;
        this.datetime = datetime;
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

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    //TODO: implement methods toJson and fromJson

}
