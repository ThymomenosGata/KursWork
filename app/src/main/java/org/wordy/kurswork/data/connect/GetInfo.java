package org.wordy.kurswork.data.connect;

import org.wordy.kurswork.data.tables.Group;
import org.wordy.kurswork.data.tables.News;
import org.wordy.kurswork.data.tables.Professor;
import org.wordy.kurswork.data.tables.Students;
import org.wordy.kurswork.data.tables.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class GetInfo {

    private String mQuery;
    private Statement stmt;
    private ResultSet rs;
    private ConnectingDB mConnect;
    private Connection mCon;

    public List<User> selectUser() {
        List<User> users = new ArrayList<>();
        mQuery = "select * from user";
        mConnect = new ConnectingDB();
        mCon = mConnect.getmCon();
        try {
            stmt = mCon.createStatement();
            rs = stmt.executeQuery(mQuery);
            while (rs.next()) {
                users.add(
                        new User(
                                rs.getInt(1),
                                rs.getString(2),
                                rs.getString(3),
                                rs.getInt(4),
                                rs.getString(5)
                        )
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public List<Professor> selectProfessor() {
        List<Professor> professors = new ArrayList<>();
        mQuery = "select * from professor";
        mConnect = new ConnectingDB();
        mCon = mConnect.getmCon();
        try {
            stmt = mCon.createStatement();
            rs = stmt.executeQuery(mQuery);
            while (rs.next()) {
                professors.add(
                        new Professor(
                                rs.getInt(1),
                                rs.getString(2),
                                rs.getString(3),
                                rs.getString(4),
                                rs.getString(5),
                                rs.getInt(6),
                                rs.getInt(7),
                                rs.getString(8)
                        )
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return professors;
    }

    public List<Students> selectStudent() {
        List<Students> students = new ArrayList<>();
        mQuery = "select * from students";
        mConnect = new ConnectingDB();
        mCon = mConnect.getmCon();
        try {
            stmt = mCon.createStatement();
            rs = stmt.executeQuery(mQuery);
            while (rs.next()) {
                students.add(
                        new Students(
                                rs.getInt(1),
                                rs.getString(2),
                                rs.getString(3),
                                rs.getString(4),
                                rs.getInt(6),
                                rs.getInt(7),
                                rs.getString(8),
                                rs.getInt(9)
                        )
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }

    public List<Group> selectGroup() {
        List<Group> groups = new ArrayList<>();
        mQuery = "select * from group";
        mConnect = new ConnectingDB();
        mCon = mConnect.getmCon();
        try {
            stmt = mCon.createStatement();
            rs = stmt.executeQuery(mQuery);
            while (rs.next()) {
                groups.add(
                        new Group(
                                rs.getInt(1),
                                rs.getString(2),
                                rs.getInt(3),
                                rs.getString(4),
                                rs.getString(5)
                        )
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return groups;
    }

    public List<News> selectNews() {
        List<News> news = new ArrayList<>();
        mQuery = "select * from news";
        mConnect = new ConnectingDB();
        mCon = mConnect.getmCon();
        try {
            stmt = mCon.createStatement();
            rs = stmt.executeQuery(mQuery);
            while (rs.next()) {
                news.add(
                        new News(
                                rs.getInt(1),
                                rs.getString(2),
                                rs.getString(3),
                                rs.getString(4),
                                rs.getString(5),
                                rs.getString(6),
                                rs.getInt(7),
                                rs.getInt(8)
                        )
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return news;
    }

}
