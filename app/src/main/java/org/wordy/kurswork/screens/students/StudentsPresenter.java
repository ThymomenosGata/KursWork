package org.wordy.kurswork.screens.students;

import android.annotation.SuppressLint;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import org.wordy.kurswork.data.tables.Professor;
import org.wordy.kurswork.data.tables.Result;
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

    @SuppressLint("StaticFieldLeak")
    public void update(Students students) {
        view.navigateToUpdate(students.getId());
    }

    @SuppressLint("StaticFieldLeak")
    public void delete(Students students) {
        new AsyncTask<Void, Void, Result>() {

            @Override
            protected Result doInBackground(Void... voids) {
                Result result = model.delStudent(students.getId());
                if (result.getMessage().equals("successful")) {
                    model.deleteStudentInDb(students);
                }
                return result;
            }

            @Override
            protected void onPostExecute(Result result) {
                super.onPostExecute(result);
                if (!result.getMessage().equals("successful")) {
                    view.showDialog(result.getMessage());
                }
            }
        }.execute();
    }
}
