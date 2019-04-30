package org.wordy.kurswork.screens.post_user;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

public class PostUserFragment extends Fragment {

    private EditText mLogin, mPassword;
    private Button mSend;
    private PostInfo postInfo;
    private GetInfo getInfo;
    private UpdateInfo updateInfo;
    private DataBase dataBase;

    private static final String APP_PREFERENCES = "mysettings";
    private static final String APP_PREFERENCES_UPD = "upd";
    private SharedPreferences mSettings;
    private int upd = 0;
    private User user;

    public PostUserFragment() {
    }

    public static PostUserFragment newInstance() {
        return new PostUserFragment();
    }

    @SuppressLint("StaticFieldLeak")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_post_users, container, false);

        mSettings = getActivity().getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);

        if (mSettings.contains(APP_PREFERENCES_UPD)) {
            upd = mSettings.getInt(APP_PREFERENCES_UPD, 0);
        }

        mLogin = view.findViewById(R.id.login);
        mPassword = view.findViewById(R.id.password);
        mSend = view.findViewById(R.id.send);

        user = new User();

        postInfo = new PostInfo();
        getInfo = new GetInfo();
        updateInfo = new UpdateInfo();
        dataBase = DataBase.getDataBase(getActivity().getApplicationContext());

        if (upd == 0) {

            mSend.setOnClickListener(v -> {
                Date date = new Date();
                @SuppressLint("SimpleDateFormat")
                DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                User user = new User(0, mLogin.getText().toString(), mPassword.getText().toString(), 0, dateFormatter.format(date));
                setData(view, user);
            });
        } else {
            new AsyncTask<Void, Void, User>() {
                @Override
                protected User doInBackground(Void... voids) {
                    return dataBase.usersDao().getUserById(upd);
                }

                @Override
                protected void onPostExecute(User userq) {
                    super.onPostExecute(userq);
                    mLogin.setText(userq.getLogin());
                    mPassword.setText(userq.getPassword());
                    user.setId(userq.getId());
                    user.setIs_blocked(userq.getIs_blocked());
                    user.setLogin(userq.getLogin());
                    user.setPassword(userq.getPassword());
                }
            }.execute();

            mSend.setOnClickListener(v -> {
                Date date = new Date();
                @SuppressLint("SimpleDateFormat")
                DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                user.setLogin(mLogin.getText().toString());
                user.setPassword(mPassword.getText().toString());
                user.setDate_last_modify(dateFormatter.format(date));
                setUpdData(view, user);
            });
        }

        return view;
    }

    @SuppressLint("StaticFieldLeak")
    private void setData(View view, User user) {
        new AsyncTask<User, Void, Boolean>() {

            @Override
            protected Boolean doInBackground(User... users) {
                boolean flag = postInfo.insertUsers(users[0]);
                users[0] = getInfo.getUserByLogin(user.getLogin());
                dataBase.usersDao().insert(users[0]);
                return flag;
            }

            @Override
            protected void onPostExecute(Boolean aBoolean) {
                super.onPostExecute(aBoolean);
                if (aBoolean) {
                    Snackbar.make(view, "отправлено", Snackbar.LENGTH_LONG).show();
                    getActivity().finish();
                } else {
                    Snackbar.make(view, "не отправлено", Snackbar.LENGTH_LONG).show();
                }
            }
        }.execute(user);
    }

    @SuppressLint("StaticFieldLeak")
    private void setUpdData(View view, User user) {
        new AsyncTask<User, Void, Boolean>() {

            @Override
            protected Boolean doInBackground(User... users) {
                Result flag = updateInfo.updateUser(users[0]);
                if (flag.getMessage().equals("successful")) {
                    dataBase.usersDao().insert(user);
                }
                return flag.getMessage().equals("successful");
            }

            @Override
            protected void onPostExecute(Boolean aBoolean) {
                super.onPostExecute(aBoolean);
                if (aBoolean) {
                    Snackbar.make(view, "отправлено", Snackbar.LENGTH_LONG).show();
                    getActivity().finish();
                } else {
                    Snackbar.make(view, "не отправлено", Snackbar.LENGTH_LONG).show();
                }
            }
        }.execute(user);
    }
}
