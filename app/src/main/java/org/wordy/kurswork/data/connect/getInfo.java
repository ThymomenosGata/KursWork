package org.wordy.kurswork.data.connect;

import org.wordy.kurswork.data.tables.Professor;
import org.wordy.kurswork.data.tables.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class getInfo {

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

}
