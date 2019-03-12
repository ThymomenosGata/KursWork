package org.wordy.kurswork.screens.post_group;

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
    private Group group;

    public PostGroupFragment() {
    }

    public static PostGroupFragment newInstance() {
        return new PostGroupFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_post_groups, container, false);

        mName = view.findViewById(R.id.name);
        mCount = view.findViewById(R.id.count);
        mFaculty = view.findViewById(R.id.faculty);
        mSend = view.findViewById(R.id.send);

        postInfo = new PostInfo();
        getInfo = new GetInfo();
        dataBase = DataBase.getDataBase(getActivity().getApplicationContext());
        mSend.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("StaticFieldLeak")
            @Override
            public void onClick(View v) {
                Date date = new Date();
                @SuppressLint("SimpleDateFormat")
                DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                group = new Group(mName.getText().toString(), Integer.valueOf(mCount.getText().toString()),
                        mFaculty.getText().toString(), dateFormatter.format(date));
                new AsyncTask<Void, Void, Boolean>() {

                    @Override
                    protected Boolean doInBackground(Void... voids) {
                        boolean flag = postInfo.insertGroup(group);
                        group = getInfo.getGroupByName(group.getName());
                        dataBase.groupsDao().insert(group);
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
