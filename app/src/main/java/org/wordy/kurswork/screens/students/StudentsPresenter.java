package org.wordy.kurswork.screens.students;

import android.annotation.SuppressLint;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import org.wordy.kurswork.data.tables.Students;

import java.util.List;

public class StudentsPresenter implements StudentsContract.Presenter {

    private StudentsModel model;
    private StudentsContract.View view;

    public StudentsPresenter(StudentsModel model, StudentsContract.View view) {
        this.model = model;
        this.view = view;
    }

    @SuppressLint("StaticFieldLeak")
    @Override
    public void getStudentsFromDb() {
        new AsyncTask<Void, Void, Boolean>() {
            @Override
            protected Boolean doInBackground(Void... voids) {
                return model.getStudentsFromDB();
            }

            @Override
            protected void onPostExecute(Boolean aBoolean) {
                super.onPostExecute(aBoolean);
                if (aBoolean) {
                    view.getData();
                }
            }
        }.execute();
    }

    @Override
    public void setStudents(List<Students> students) {
        model.setmCurrentStudents(students);
        view.setDataList(model.getmCurrentStudents());
    }

    @Override
    public LiveData<List<Students>> getStudents() {
        return model.getData();
    }
}
