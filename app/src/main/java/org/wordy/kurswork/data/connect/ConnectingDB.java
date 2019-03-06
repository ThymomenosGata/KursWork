package org.wordy.kurswork.data.connect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectingDB {

    private static final String url = "jdbc:mysql://mysql.gdz-geo.myjino.ru/gdz-geo_kurs";
    private static final String user = null;
    private static final String password = null;

    private Connection mCon;

    public ConnectingDB() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            this.mCon = DriverManager.getConnection(url, user, password);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Connection getmCon() {
        return mCon;
    }
}
