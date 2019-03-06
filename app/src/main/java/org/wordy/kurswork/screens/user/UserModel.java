package org.wordy.kurswork.screens.user;

import android.app.Application;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.wordy.kurswork.data.DataBase;
import org.wordy.kurswork.data.PortalRest;
import org.wordy.kurswork.data.connect.GetInfo;
import org.wordy.kurswork.data.tables.Group;
import org.wordy.kurswork.data.tables.User;

import java.io.IOException;
import java.util.List;

public class UserModel {

    private PortalRest mPortal;
    private DataBase dataBase;
    private GetInfo getInfo;
    private static List<User> mCurrentUsers;

    public UserModel(Application application) {
        this.dataBase = DataBase.getDataBase(application);
        this.mPortal = PortalRest.getPortal();
        this.getInfo = new GetInfo();
    }

    public List<User> getmCurrentUsers() {
        return mCurrentUsers;
    }

    public void setmCurrentUsers(List<User> mCurrentUsers) {
        UserModel.mCurrentUsers = mCurrentUsers;
    }

    public Boolean getUsers() {
        try {
            JSONArray jsonArray = new JSONArray(mPortal.get("user.php?id=all").body().string());
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject json = jsonArray.getJSONObject(i);
                dataBase.groupsDao().insert(Group.fromJson(json));
            }
            return true;
        } catch (JSONException | IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Boolean getUsersFromDB() {
        List<User> users = getInfo.selectUser();
        for (User user : users) {
            dataBase.usersDao().insert(user);
        }
        return true;
    }

}
