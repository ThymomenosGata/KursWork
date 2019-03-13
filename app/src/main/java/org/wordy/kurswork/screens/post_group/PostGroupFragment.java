package org.wordy.kurswork.screens.post_group;

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
import org.wordy.kurswork.data.tables.Group;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PostGroupFragment extends Fragment {

    private EditText mName, mCount, mFaculty;

    private Button mSend;
    private PostInfo postInfo;
    private GetInfo getInfo;
    private DataBase dataBase;

    private static final String APP_PREFERENCES = "mysettings";
    private static final String APP_PREFERENCES_UPD = "upd";
    private SharedPreferences mSettings;
    private int upd = 0;
    private Group group;

    public PostGroupFragment() {
    }

    public static PostGroupFragment newInstance() {
        return new PostGroupFragment();
    }

    @SuppressLint("StaticFieldLeak")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_post_groups, container, false);

        mSettings = getActivity().getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);

        if (mSettings.contains(APP_PREFERENCES_UPD)) {
            upd = mSettings.getInt(APP_PREFERENCES_UPD, 0);
        }

        mName = view.findViewById(R.id.name);
        mCount = view.findViewById(R.id.count);
        mFaculty = view.findViewById(R.id.faculty);
        mSend = view.findViewById(R.id.send);

        group = new Group();

        postInfo = new PostInfo();
        getInfo = new GetInfo();
        dataBase = DataBase.getDataBase(getActivity().getApplicationContext());

        if (upd == 0) {
            mSend.setOnClickListener(v -> {
                Date date = new Date();
                @SuppressLint("SimpleDateFormat")
                DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                Group group = new Group(mName.getText().toString(), Integer.valueOf(mCount.getText().toString()),
                        mFaculty.getText().toString(), dateFormatter.format(date));
                setData(view, group);
            });
        } else {
            new AsyncTask<Void, Void, Group>() {

                @Override
                protected Group doInBackground(Void... voids) {
                    return dataBase.groupsDao().getUserById(upd);
                }

                @Override
                protected void onPostExecute(Group groupq) {
                    super.onPostExecute(groupq);
                    mName.setText(groupq.getName());
                    mCount.setText(String.valueOf(groupq.getCount()));
                    mFaculty.setText(groupq.getFaculty());
                    group.setId(groupq.getId());
                    group.setFaculty(groupq.getFaculty());
                    group.setName(groupq.getName());
                    group.setCount(groupq.getCount());
                }
            }.execute();
            mSend.setOnClickListener(v -> {
                Date date = new Date();
                @SuppressLint("SimpleDateFormat")
                DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                group.setName(mName.getText().toString());
                group.setFaculty(mFaculty.getText().toString());
                group.setCount(Integer.valueOf(mCount.getText().toString()));
                group.setDate_last_modify(dateFormatter.format(date));
                setUpdData(view, group);
            });
        }
        return view;
    }

    @SuppressLint("StaticFieldLeak")
    private void setData(View view, Group group) {
        new AsyncTask<Group, Void, Boolean>() {

            @Override
            protected Boolean doInBackground(Group... groups) {
                boolean flag = postInfo.insertGroup(groups[0]);
                groups[0] = getInfo.getGroupByName(groups[0].getName());
                dataBase.groupsDao().insert(groups[0]);
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
        }.execute(group);
    }

    @SuppressLint("StaticFieldLeak")
    private void setUpdData(View view, Group group) {
        new AsyncTask<Group, Void, Boolean>() {

            @Override
            protected Boolean doInBackground(Group... groups) {
                boolean flag = getInfo.updateGroup(groups[0]);
                if(flag) {
                    dataBase.groupsDao().insert(group);
                }
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
        }.execute(group);
    }

}

