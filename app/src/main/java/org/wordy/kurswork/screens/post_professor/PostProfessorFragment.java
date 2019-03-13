package org.wordy.kurswork.screens.post_professor;

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

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PostProfessorFragment extends Fragment {

    private EditText mName, mSurname, mMiddlename, mPosition, mExperience;
    private Spinner mSpinner;
    private SpinnerAdapter spinnerAdapter;
    private Button button;

    private PostInfo postInfo;
    private GetInfo getInfo;
    private DataBase dataBase;

    private static final String APP_PREFERENCES = "mysettings";
    private static final String APP_PREFERENCES_UPD = "upd";
    private SharedPreferences mSettings;
    private int upd = 0;
    private Professor professor;
    private Date date;

    public PostProfessorFragment() {
    }

    public static PostProfessorFragment newInstance() {
        return new PostProfessorFragment();
    }

    @SuppressLint("StaticFieldLeak")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmetn_post_professors, container, false);

        mSettings = getActivity().getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);

        if (mSettings.contains(APP_PREFERENCES_UPD)) {
            upd = mSettings.getInt(APP_PREFERENCES_UPD, 0);
        }

        professor = new Professor();

        mName = view.findViewById(R.id.name);
        mSurname = view.findViewById(R.id.surname);
        mMiddlename = view.findViewById(R.id.middlename);
        mPosition = view.findViewById(R.id.position);
        mExperience = view.findViewById(R.id.experience);
        mSpinner = view.findViewById(R.id.spinner);
        button = view.findViewById(R.id.send);

        postInfo = new PostInfo();
        getInfo = new GetInfo();
        dataBase = DataBase.getDataBase(getActivity().getApplicationContext());

        dataBase.usersDao().getAll().observe(this, users -> {
            spinnerAdapter = new SpinnerAdapter(getActivity(), R.layout.item_spinner_users, users);
            mSpinner.setAdapter(spinnerAdapter);
        });

        date = new Date();
        @SuppressLint("SimpleDateFormat")
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

        if (upd == 0) {
            button.setOnClickListener(v -> {
                Professor professor = new Professor(0, mSurname.getText().toString(),
                        mName.getText().toString(), mMiddlename.getText().toString(),
                        mPosition.getText().toString(), Integer.valueOf(mExperience.getText().toString()),
                        spinnerAdapter.getItem(mSpinner.getSelectedItemPosition()).getId(), dateFormatter.format(date));
                setData(view, professor);
            });
        } else {
            new AsyncTask<Void, Void, Professor>() {

                @Override
                protected Professor doInBackground(Void... voids) {
                    return dataBase.professorDao().getProfessorById(upd);
                }

                @Override
                protected void onPostExecute(Professor professorq) {
                    super.onPostExecute(professorq);
                    mName.setText(professorq.getName());
                    mSurname.setText(professorq.getSurname());
                    mMiddlename.setText(professorq.getMiddlename());
                    mPosition.setText(professorq.getPosition());
                    mExperience.setText(String.valueOf(professorq.getExperience()));
                    professor.setId(professorq.getId());
                    professor.setSurname(professorq.getSurname());
                    professor.setName(professorq.getName());
                    professor.setMiddlename(professorq.getMiddlename());
                    professor.setPosition(professorq.getPosition());
                    professor.setExperience(professorq.getExperience());
                    professor.setUserID(professorq.getUserID());
                    professor.setDate_last_modify(professorq.getDate_last_modify());
                }
            }.execute();

            button.setOnClickListener(v -> {
                professor.setSurname(mSurname.getText().toString());
                professor.setName(mName.getText().toString());
                professor.setMiddlename(mMiddlename.getText().toString());
                professor.setPosition(mPosition.getText().toString());
                professor.setExperience(Integer.valueOf(mExperience.getText().toString()));
                professor.setUserID(spinnerAdapter.getItem(mSpinner.getSelectedItemPosition()).getId());
                professor.setDate_last_modify(dateFormatter.format(date));
                setUpdData(view, professor);
            });

        }

        return view;
    }

    @SuppressLint("StaticFieldLeak")
    private void setData(View view, Professor professor) {
        new AsyncTask<Professor, Void, Boolean>() {

            @Override
            protected Boolean doInBackground(Professor... professors) {
                boolean flag = postInfo.insertProfessor(professors[0]);
                professors[0] = getInfo.getProfessorByName(professor.getName());
                dataBase.professorDao().insert(professors[0]);
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
        }.execute(professor);
    }

    @SuppressLint("StaticFieldLeak")
    private void setUpdData(View view, Professor professor) {
        new AsyncTask<Professor, Void, Boolean>() {

            @Override
            protected Boolean doInBackground(Professor... professors) {
                boolean flag = getInfo.updateProfessor(professors[0]);
                dataBase.professorDao().insert(professor);
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
        }.execute(professor);
    }

}
