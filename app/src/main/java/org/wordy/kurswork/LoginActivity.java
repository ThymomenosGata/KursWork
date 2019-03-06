package org.wordy.kurswork;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LoginActivity extends AppCompatActivity {

    private static final String url = "jdbc:mysql://mysql.gdz-geo.myjino.ru/gdz-geo_kurs";
    private static final String user = "046120438_kurs";
    private static final String password = "046120438_kurs";

    public static Connection con;
    private static Statement stmt;
    private static ResultSet rs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public static void main(String[] args) throws ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        String query = "select * from user";
        try {
            con = DriverManager.getConnection(url, user, password);
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                int count = rs.getInt(1);
                System.out.println("Total number of books in the table : " + count);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}
