package org.wordy.kurswork.data.connect;

import org.wordy.kurswork.data.tables.Group;
import org.wordy.kurswork.data.tables.News;
import org.wordy.kurswork.data.tables.Professor;
import org.wordy.kurswork.data.tables.Students;
import org.wordy.kurswork.data.tables.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PostInfo {

    private PreparedStatement stm;
    private String mQuery;
    private Statement stmt;
    private ResultSet rs;
    private ConnectingDB mConnect;
    private Connection mCon;

    public void postUser(User user) {
        mQuery = "insert into user(id, login, password, is_blocked, date_last_modify) " +
                "values(?,?,?,?,?);";
        mConnect = new ConnectingDB();
        mCon = mConnect.getmCon();
        try {
            stm = mCon.prepareStatement(mQuery);
            stm.setInt(1, user.getId());
            stm.setString(2, user.getLogin());
            stm.setString(3, user.getPassword());
            stm.setInt(4, user.getIs_blocked());
            stm.setString(5, user.getDate_last_modify());
            stm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void postStudent(Students students) {
        mQuery = "insert into students(id, surname, name, middlename, group, avg_score, date_last_modify, user)" +
                " values(?,?,?,?,?,?,?,?);";
        mConnect = new ConnectingDB();
        mCon = mConnect.getmCon();
        try {
            stm = mCon.prepareStatement(mQuery);
            stm.setInt(1, students.getId());
            stm.setString(2, students.getSurname());
            stm.setString(3, students.getName());
            stm.setString(4, students.getMiddlename());
            stm.setInt(5, students.getGroupID());
            stm.setInt(6, students.getAvg_score());
            stm.setString(7, students.getDate_last_modify());
            stm.setInt(8, students.getUserID());
            stm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void postProfessor(Professor professor) {
        mQuery = "insert into professor(id, surname, name, middlename, position, experience, user, date_last_modify)" +
                " values(?,?,?,?,?,?,?,?);";
        mConnect = new ConnectingDB();
        mCon = mConnect.getmCon();
        try {
            stm = mCon.prepareStatement(mQuery);
            stm.setInt(1, professor.getId());
            stm.setString(2, professor.getSurname());
            stm.setString(3, professor.getName());
            stm.setString(4, professor.getMiddlename());
            stm.setString(5, professor.getPosition());
            stm.setInt(6, professor.getExperience());
            stm.setInt(7, professor.getUserID());
            stm.setString(8, professor.getDate_last_modify());
            stm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void postNews(News news) {
        mQuery = "insert into news(id, title, small_sedcription, full_description, date_publish, date_last_modify, is_published, author) " +
                "values(?,?,?,?,?,?,?,?);";
        mConnect = new ConnectingDB();
        mCon = mConnect.getmCon();
        try {
            stm = mCon.prepareStatement(mQuery);
            stm.setInt(1, news.getId());
            stm.setString(2, news.getTitle());
            stm.setString(3, news.getSmall_sedcription());
            stm.setString(4, news.getFull_description());
            stm.setString(5, news.getDate_publish());
            stm.setString(6, news.getDate_last_modify());
            stm.setInt(7, news.getIs_published());
            stm.setInt(8, news.getAuthor());
            stm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void postGroup(Group group) {
        mQuery = "insert into group(id, name, count, faculty, date_last_modify) " +
                "values(?,?,?,?,?);";
        mConnect = new ConnectingDB();
        mCon = mConnect.getmCon();
        try {
            stm = mCon.prepareStatement(mQuery);
            stm.setInt(1, group.getId());
            stm.setString(2, group.getName());
            stm.setInt(3, group.getCount());
            stm.setString(4, group.getFaculty());
            stm.setString(5, group.getDate_last_modify());
            stm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
