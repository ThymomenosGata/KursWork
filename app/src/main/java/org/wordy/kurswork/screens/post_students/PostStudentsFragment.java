package org.wordy.kurswork.screens.post_students;

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
import android.widget.Spinner;

import org.wordy.kurswork.R;
import org.wordy.kurswork.data.DataBase;
import org.wordy.kurswork.data.rests.GetInfo;
import org.wordy.kurswork.data.rests.PostInfo;
import org.wordy.kurswork.data.tables.Professor;
import org.wordy.kurswork.data.tables.Students;
import org.wordy.kurswork.screens.post_professor.SpinnerAdapter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PostStudentsFragment extends Fragment {

    private EditText mName, mSurname, mMiddlename, evgScore;
    private Spinner mSpinner, mSpin;
    private SpinnerAdapter spinnerAdapter;
    private SpinnerAdapterGroup adapterGroup;
    private Button button;

    private PostInfo postInfo;
    private GetInfo getInfo;
    private DataBase dataBase;

    private static final String APP_PREFERENCES = "mysettings";
    private static final String APP_PREFERENCES_UPD = "upd";
    private SharedPreferences mSettings;
    private int upd = 0;
    private Students students;
    private Date date;

    public PostStudentsFragment() {
    }

    public static PostStudentsFragment newInstance() {
        return new PostStudentsFragment();
    }

    @SuppressLint("StaticFieldLeak")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_post_students, container, false);

        mSettings = getActivity().getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);

        if (mSettings.contains(APP_PREFERENCES_UPD)) {
            upd = mSettings.getInt(APP_PREFERENCES_UPD, 0);
        }

        students = new Students();

        mName = view.findViewById(R.id.name);
        mSurname = view.findViewById(R.id.surname);
        mMiddlename = view.findViewById(R.id.middlename);
        evgScore = view.findViewById(R.id.avg_score);
        mSpinner = view.findViewById(R.id.users);
        mSpin = view.findViewById(R.id.group);
        button = view.findViewById(R.id.send);

        postInfo = new PostInfo();
        getInfo = new GetInfo();
        dataBase = DataBase.getDataBase(getActivity().getApplicationContext());

        dataBase.usersDao().getAll().observe(this, users -> {
            spinnerAdapter = new SpinnerAdapter(getActivity(), R.layout.item_spinner_users, users);
            mSpinner.setAdapter(spinnerAdapter);
        });

        dataBase.groupsDao().getAll().observe(this, groups -> {
            adapterGroup = new SpinnerAdapterGroup(getActivity(), R.layout.item_spinner_users, groups);
            mSpin.setAdapter(adapterGroup);
        });

        date = new Date();
        @SuppressLint("SimpleDateFormat")
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

        if (upd == 0) {
            button.setOnClickListener(v -> {
                Students students = new Students(0, mSurname.getText().toString(),
                        mName.getText().toString(), mMiddlename.getText().toString(),
                        Integer.valueOf(evgScore.getText().toString()), spinnerAdapter.getItem(mSpinner.getSelectedItemPosition()).getId(),
                        dateFormatter.format(date), adapterGroup.getItem(mSpin.getSelectedItemPosition()).getId());
                setData(view, students);
            });
        } else {
            new AsyncTask<Void, Void, Students>() {

                @Override
                protected Students doInBackground(Void... voids) {
                    return dataBase.studentsDao().getStudentById(upd);
                }

                @Override
                protected void onPostExecute(Students students1) {
                    super.onPostExecute(students1);
                    mName.setText(students1.getName());
                    mSurname.setText(students1.getSurname());
                    mMiddlename.setText(students1.getMiddlename());
                    evgScore.setText(String.valueOf(students1.getAvg_score()));
                    students.setId(students1.getId());
                    students.setSurname(students1.getSurname());
                    students.setName(students1.getName());
                    students.setMiddlename(students1.getMiddlename());
                    students.setAvg_score(students1.getAvg_score());
                    students.setGroupID(students1.getGroupID());
                    students.setDate_last_modify(students1.getDate_last_modify());
                    students.setUserID(students1.getUserID());

                }
            }.execute();

            button.setOnClickListener(v -> {
                students.setSurname(mSurname.getText().toString());
                students.setName(mName.getText().toString());
                students.setMiddlename(mMiddlename.getText().toString());
                students.setAvg_score(Integer.valueOf(evgScore.getText().toString()));
                students.setGroupID(adapterGroup.getItem(mSpin.getSelectedItemPosition()).getId());
                students.setDate_last_modify(dateFormatter.format(date));
                students.setUserID(spinnerAdapter.getItem(mSpinner.getSelectedItemPosition()).getId());
                setUpdData(view, students);
            });
        }

        return view;
    }

    @SuppressLint("StaticFieldLeak")
    private void setData(View view, Students students) {
        new AsyncTask<Students, Void, Boolean>() {

            @Override
            protected Boolean doInBackground(Students... students1) {
                boolean flag = postInfo.insertStudents(students1[0]);
                students1[0] = getInfo.getStudentByName(students1[0].getName());
                dataBase.studentsDao().insert(students1[0]);
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
        }.execute(students);
    }

    @SuppressLint("StaticFieldLeak")
    private void setUpdData(View view, Students student) {
        new AsyncTask<Students, Void, Boolean>() {

            @Override
            protected Boolean doInBackground(Students... students1) {
                boolean flag = getInfo.updateStudents(students1[0]);
                dataBase.studentsDao().insert(student);
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
        }.execute(student);
    }
}
