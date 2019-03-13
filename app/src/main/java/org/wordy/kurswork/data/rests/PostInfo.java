package org.wordy.kurswork.data.rests;

import org.wordy.kurswork.data.PortalRest;
import org.wordy.kurswork.data.tables.Group;
import org.wordy.kurswork.data.tables.News;
import org.wordy.kurswork.data.tables.Professor;
import org.wordy.kurswork.data.tables.Students;
import org.wordy.kurswork.data.tables.User;

import java.io.IOException;

public class PostInfo {

    private PortalRest mPortal;

    public PostInfo() {
        this.mPortal = PortalRest.getPortal();
    }

    public boolean insertGroup(Group group) {
        try {
            mPortal.post("{\"query\" : \"insert into `group`(name, count, faculty, date_last_modify) " +
                    "values(\'" + group.getName() + "\'," + group.getCount() + ",\'" + group.getFaculty() + "\',\'"
                    + group.getDate_last_modify() + "\')\"}").body().string();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean insertNews(News news) {
        try {
            mPortal.post("{\"query\" : \"insert into news(title, small_sedcription, full_description, date_published, date_last_modify, is_published, author)"
                    + "values(\'" + news.getTitle() + "\',\'" + news.getSmall_sedcription() + "\',\'" + news.getFull_description() + "\',\'" + news.getDate_publish()
                    + "\', \'" + news.getDate_last_modify() + "\'," + news.getIs_published() + "," + news.getAuthor() + ")\"}").body().string();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean insertProfessor(Professor professor) {
        try {
            mPortal.post("{\"query\" : \"insert into professor(surname, name, middlename, position, experience, user, date_last_modify)"
                    + "values(\'" + professor.getSurname() + "\',\'" + professor.getName() + "\',\'" + professor.getMiddlename() + "\',\'"
                    + professor.getPosition() + "\'," + professor.getExperience() + "," + professor.getUserID() + ",\'"
                    + professor.getDate_last_modify() + "\')\"}").body().string();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean insertStudents(Students students) {
        try {
            mPortal.post("{\"query\" : \"insert into students(surname, name, middlename, `group`, avg_score, date_last_modify, user) values(\'"
                    + students.getSurname() + "\',\'" + students.getName() + "\',\'" + students.getMiddlename() + "\'," + students.getGroupID()
                    + "," + students.getAvg_score() + ",\'" + students.getDate_last_modify() + "\'," + students.getUserID() + ")\"}").body().string();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean insertUsers(User user) {
        try {
            mPortal.post("{\"query\" : \"insert into user(login, password, is_blocked, date_last_modify) values(\'"
                    + user.getLogin() + "\',\'" + user.getPassword() + "\'," + user.getIs_blocked() + ",\'"
                    + user.getDate_last_modify() + "\')\"}").body().string();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

}
