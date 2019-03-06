package org.wordy.kurswork.data.tables;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(
        tableName = "user"
)
public class User {
    @PrimaryKey
    int id;
    String login;
    String password;
    int is_blocked;
    String date_last_modify;

    public User(int id, String login, String password, int is_blocked, String date_last_modify) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.is_blocked = is_blocked;
        this.date_last_modify = date_last_modify;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getIs_blocked() {
        return is_blocked;
    }

    public void setIs_blocked(int is_blocked) {
        this.is_blocked = is_blocked;
    }

    public String getDate_last_modify() {
        return date_last_modify;
    }

    public void setDate_last_modify(String date_last_modify) {
        this.date_last_modify = date_last_modify;
    }

    //TODO: implement methods toJson and fromJson
}
