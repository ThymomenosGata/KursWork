package org.wordy.kurswork.screens;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import org.wordy.kurswork.NetworkChangeReceiver;
import org.wordy.kurswork.NetworkUtil;
import org.wordy.kurswork.R;
import org.wordy.kurswork.data.DataBase;
import org.wordy.kurswork.data.rests.GetInfo;
import org.wordy.kurswork.data.rests.PostInfo;
import org.wordy.kurswork.data.tables.Group;
import org.wordy.kurswork.data.tables.News;
import org.wordy.kurswork.data.tables.Professor;
import org.wordy.kurswork.data.tables.Students;
import org.wordy.kurswork.data.tables.User;
import org.wordy.kurswork.screens.group.GroupFragment;
import org.wordy.kurswork.screens.news.NewsFragment;
import org.wordy.kurswork.screens.professor.ProfessorFragment;
import org.wordy.kurswork.screens.students.StudentsFragment;
import org.wordy.kurswork.screens.user.UserFragment;

import java.util.List;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, NetworkChangeReceiver.ConnectivityReceiverListener {

    private static final String APP_PREFERENCES = "mysettings";
    private static final String APP_PREFERENCES_ID = "id";
    private static final String APP_PREFERENCES_UPD = "upd";
    private SharedPreferences mSettings;
    private Editor editor;
    private PostInfo postInfo;
    private GetInfo getInfo;
    private DataBase dataBase;
    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        checkConnection();

        drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        postInfo = new PostInfo();
        getInfo = new GetInfo();
        dataBase = DataBase.getDataBase(getApplication());

        mSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        editor = mSettings.edit();
        editor.putInt(APP_PREFERENCES_ID, 1);
        editor.putInt(APP_PREFERENCES_UPD, 0);
        editor.apply();

        FloatingActionButton fab = findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, PostActivity.class);
            startActivity(intent);
        });


        loadFragment(UserFragment.newInstance());
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.action_reload: {
                if (isNetworkAvailable()) {
                    //Snackbar.make(drawerLayout, "Соединено", Snackbar.LENGTH_LONG).show();
                    checkConnection();
                    updateInfo();
                } else {
                    checkConnection();
                    //Snackbar.make(drawerLayout, "Соедиение", Snackbar.LENGTH_LONG).show();
                }
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.reload, menu);
        return true;
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.users) {
            loadFragment(UserFragment.newInstance());
            editor.putInt(APP_PREFERENCES_ID, 1);
            editor.putInt(APP_PREFERENCES_UPD, 0);
            editor.apply();
        } else if (id == R.id.students) {
            loadFragment(StudentsFragment.newInstance());
            editor.putInt(APP_PREFERENCES_ID, 2);
            editor.putInt(APP_PREFERENCES_UPD, 0);
            editor.apply();
        } else if (id == R.id.professors) {
            loadFragment(ProfessorFragment.newInstance());
            editor.putInt(APP_PREFERENCES_ID, 3);
            editor.putInt(APP_PREFERENCES_UPD, 0);
            editor.apply();
        } else if (id == R.id.group) {
            loadFragment(GroupFragment.newInstance());
            editor.putInt(APP_PREFERENCES_ID, 4);
            editor.putInt(APP_PREFERENCES_UPD, 0);
            editor.apply();
        } else if (id == R.id.news) {
            loadFragment(NewsFragment.newInstance());
            editor.putInt(APP_PREFERENCES_ID, 5);
            editor.putInt(APP_PREFERENCES_UPD, 0);
            editor.apply();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void loadFragment(Fragment fragment) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.container, fragment);
        ft.commit();
    }

    public boolean isNetworkAvailable() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }

    @SuppressLint("StaticFieldLeak")
    public void updateInfo() {
        new AsyncTask<Void, Void, Boolean>() {

            @Override
            protected Boolean doInBackground(Void... voids) {
                dataBase.newsDao().deleteAll();
                dataBase.professorDao().deleteAll();
                dataBase.studentsDao().deleteAll();
                dataBase.groupsDao().deleteAll();
                dataBase.usersDao().deleteAll();
                List<Group> groups = getInfo.selectGroup();
                for (Group group : groups) {
                    dataBase.groupsDao().insert(group);
                }
                List<News> news = getInfo.selectNews();
                for (News news1 : news) {
                    dataBase.newsDao().insert(news1);
                }
                List<Professor> professors = getInfo.selectProfessor();
                for (Professor professor : professors) {
                    dataBase.professorDao().insert(professor);
                }
                List<Students> students = getInfo.selectStudents();
                for (Students student : students) {
                    dataBase.studentsDao().insert(student);
                }
                List<User> users = getInfo.selectUsers();
                for (User user : users) {
                    dataBase.usersDao().insert(user);
                }
                return true;
            }

            @Override
            protected void onPostExecute(Boolean aBoolean) {
                super.onPostExecute(aBoolean);

            }
        }.execute();
    }

    private void checkConnection() {
        boolean isConnected = NetworkChangeReceiver.isConnected();
        showSnack(isConnected);
    }

    private void showSnack(boolean isConnected) {
        String message;
        if (isConnected) {
            message = "Good! Connected to Internet";
        } else {
            message = "Sorry! Not connected to internet";
        }

        Snackbar.make(findViewById(R.id.drawer_layout), message, Snackbar.LENGTH_LONG).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        NetworkUtil.getInstance().setConnectivityListener(this);
    }

    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {
        showSnack(isConnected);
    }
}
