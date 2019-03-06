package org.wordy.kurswork.screens.students;

import android.arch.lifecycle.LiveData;

import org.wordy.kurswork.data.tables.Students;

import java.util.List;

public class StudentsPresenter implements StudentsContract.Presenter {

    private StudentsModel model;
    private StudentsContract.View view;

    public StudentsPresenter(StudentsModel model, StudentsContract.View view) {
        this.model = model;
        this.view = view;
    }

    @Override
    public void getStudentsFromDb() {

    }

    @Override
    public void setStudents(List<Students> users) {

    }

    @Override
    public LiveData<List<Students>> getStudents() {
        return null;
    }
}
