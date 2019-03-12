package org.wordy.kurswork.screens.post_user;

import android.annotation.SuppressLint;
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
import org.wordy.kurswork.data.tables.User;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PostUserFragment extends Fragment {

    private EditText mLogin, mPassword;
    private Button mSend;
    private PostInfo postInfo;
    private GetInfo getInfo;
    private DataBase dataBase;
    public User user;

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
        mLogin = view.findViewById(R.id.login);
        mPassword = view.findViewById(R.id.password);
        mSend = view.findViewById(R.id.send);

        postInfo = new PostInfo();
        getInfo = new GetInfo();
        dataBase = DataBase.getDataBase(getActivity().getApplicationContext());

        mSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Date date = new Date();
                @SuppressLint("SimpleDateFormat")
                DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                user = new User(0, mLogin.getText().toString(), mPassword.getText().toString(), 0, dateFormatter.format(date));
                new AsyncTask<Void, Void, Boolean>() {

                    @Override
                    protected Boolean doInBackground(Void... voids) {
                        boolean flag = postInfo.insertUsers(user);
                        user = getInfo.getUserByLogin(user.getLogin());
                        dataBase.usersDao().insert(user);
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
                }.execute();
            }
        });

        return view;
    }
}
