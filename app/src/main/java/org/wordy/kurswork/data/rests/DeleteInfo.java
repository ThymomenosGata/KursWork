package org.wordy.kurswork.data.rests;

import com.google.gson.Gson;

import org.wordy.kurswork.data.PortalRest;
import org.wordy.kurswork.data.tables.Result;

import java.io.IOException;

public class DeleteInfo {

    private PortalRest mPortal;
    private Gson gson;

    public DeleteInfo() {
        this.mPortal = PortalRest.getPortal();
        this.gson = new Gson();
    }

    public Result delStudentbyId(int id) {
        try {
            return gson.fromJson(mPortal.delete("deleteStudent", String.valueOf(id)).body().string(), Result.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Result delUserById(int id) {
        try {
            return gson.fromJson(mPortal.delete("deleteUser", String.valueOf(id)).body().string(), Result.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Result delGroupById(int id) {
        try {
            return gson.fromJson(mPortal.delete("deleteGroup", String.valueOf(id)).body().string(), Result.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Result delNewsById(int id) {
        try {
            return gson.fromJson(mPortal.delete("deleteNews", String.valueOf(id)).body().string(), Result.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Result delProfessorById(int id) {
        try {
            return gson.fromJson(mPortal.delete("deleteProfessor", String.valueOf(id)).body().string(), Result.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
