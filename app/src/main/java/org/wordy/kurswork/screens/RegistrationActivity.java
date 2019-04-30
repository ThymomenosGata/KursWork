package org.wordy.kurswork.screens;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.wordy.kurswork.R;
import org.wordy.kurswork.data.DataBase;
import org.wordy.kurswork.data.rests.GetInfo;
import org.wordy.kurswork.data.rests.PostInfo;
import org.wordy.kurswork.data.rests.UpdateInfo;
import org.wordy.kurswork.data.tables.Result;
import org.wordy.kurswork.data.tables.User;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RegistrationActivity extends AppCompatActivity {

    private EditText mLogin, mPassword, mRepeatPass;
    private Button mSend;
    private PostInfo postInfo;
    private GetInfo getInfo;
    private UpdateInfo updateInfo;
    private DataBase dataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        mLogin = findViewById(R.id.login);
        mPassword = findViewById(R.id.password);
        mRepeatPass = findViewById(R.id.repeat_pass);
        mSend = findViewById(R.id.send);

        postInfo = new PostInfo();
        getInfo = new GetInfo();
        updateInfo = new UpdateInfo();
        dataBase = DataBase.getDataBase(getApplicationContext());

        mSend.setOnClickListener(v -> {
            if (mPassword.getText().toString().equals(mRepeatPass.getText().toString())) {
                Date date = new Date();
                @SuppressLint("SimpleDateFormat")
                DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                User user = new User(0, mLogin.getText().toString(), mPassword.getText().toString(), 0, dateFormatter.format(date));
                setData(v, user);
            } else {
                Snackbar.make(v, "Пароли не совпали", Snackbar.LENGTH_LONG).show();
            }
        });

    }

    @SuppressLint("StaticFieldLeak")
    private void setData(View view, User user) {
        new AsyncTask<User, Void, Result>() {

            @Override
            protected Result doInBackground(User... users) {
                Result flag = postInfo.newUsers(users[0]);
                if (flag.getMessage().equals("successful")) {
                    users[0] = getInfo.getUserByLogin(user.getLogin());
                    dataBase.usersDao().insert(users[0]);
                }
                return flag;
            }

            @Override
            protected void onPostExecute(Result result) {
                super.onPostExecute(result);
                if (result.getMessage().equals("successful")) {
                    Snackbar.make(view, "отправлено", Snackbar.LENGTH_LONG).show();
                    finish();
                } else {
                    Log.i("APP", result.getMessage());
                    Snackbar.make(view, result.getMessage(), Snackbar.LENGTH_LONG).show();
                }
            }
        }.execute(user);
    }

}
