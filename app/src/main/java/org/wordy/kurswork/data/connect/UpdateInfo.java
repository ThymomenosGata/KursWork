package org.wordy.kurswork.data.connect;

import org.wordy.kurswork.data.tables.Group;
import org.wordy.kurswork.data.tables.News;
import org.wordy.kurswork.data.tables.Professor;
import org.wordy.kurswork.data.tables.Students;
import org.wordy.kurswork.data.tables.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class UpdateInfo {

    private String mQuery;
    private Statement stmt;
    private ConnectingDB mConnect;
    private Connection mCon;

    public void updateUser(User user) {
        mQuery = "update user set login = " + user.getLogin()
                + ", password = " + user.getPassword()
                + ", is_blocked = " + user.getIs_blocked()
                + ", date_last_modify = " + user.getDate_last_modify()
                + "where id = " + user.getId();
        mConnect = new ConnectingDB();
        mCon = mConnect.getmCon();
        try {
            stmt = mCon.createStatement();
            stmt.executeUpdate(mQuery);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateStudent(Students students) {
        mQuery = "update students set surname = " + students.getSurname()
                + ", name = " + students.getName()
                + ", middlename = " + students.getMiddlename()
                + ", group = " + students.getGroupID()
                + ", avg_score = " + students.getAvg_score()
                + ", date_last_modify = " + students.getDate_last_modify()
                + ", user = " + students.getUserID()
                + "where id = " + students.getId();
        mConnect = new ConnectingDB();
        mCon = mConnect.getmCon();
        try {
            stmt = mCon.createStatement();
            stmt.executeUpdate(mQuery);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateProfessor(Professor professor) {
        mQuery = "update professor set login = " + professor.getSurname()
                + ", name = " + professor.getName()
                + ", middlename = " + professor.getMiddlename()
                + ", position = " + professor.getPosition()
                + ", experience = " + professor.getExperience()
                + ", user = " + professor.getUserID()
                + ", date_last_modify = " + professor.getDate_last_modify()
                + "where id = " + professor.getId();
        mConnect = new ConnectingDB();
        mCon = mConnect.getmCon();
        try {
            stmt = mCon.createStatement();
            stmt.executeUpdate(mQuery);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateNews(News news) {
        mQuery = "insert into news(id, title, small_sedcription, full_description, date_publish, date_last_modify, is_published, author) values(?,?,?,?,?,?,?,?);";
        mQuery = "update news set title = " + news.getTitle()
                + ", small_sedcription = " + news.getSmall_sedcription()
                + ", full_description = " + news.getFull_description()
                + ", date_publish = " + news.getDate_publish()
                + ", date_last_modify = " + news.getDate_last_modify()
                + ", is_published = " + news.getIs_published()
                + ", author = " + news.getAuthor()
                + "where id = " + news.getId();
        mConnect = new ConnectingDB();
        mCon = mConnect.getmCon();
        try {
            stmt = mCon.createStatement();
            stmt.executeUpdate(mQuery);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateGroup(Group group) {
        mQuery = "update group set name = " + group.getName()
                + ", count = " + group.getCount()
                + ", faculty = " + group.getFaculty()
                + ", date_last_modify = " + group.getDate_last_modify()
                + "where id = " + group.getId();
        mConnect = new ConnectingDB();
        mCon = mConnect.getmCon();
        try {
            stmt = mCon.createStatement();
            stmt.executeUpdate(mQuery);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
