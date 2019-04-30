package org.wordy.kurswork.screens.professor;

import android.annotation.SuppressLint;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import org.wordy.kurswork.data.tables.Professor;
import org.wordy.kurswork.data.tables.Result;

import java.util.List;

public class ProfessorPresenter implements ProfessorContract.Presenter {

    private ProfessorModel model;
    private ProfessorContract.View view;

    public ProfessorPresenter(ProfessorModel model, ProfessorContract.View view) {
        this.model = model;
        this.view = view;
    }

    @SuppressLint("StaticFieldLeak")
    @Override
    public void getProfessorsFromDb() {
        new AsyncTask<Void, Void, Boolean>() {
            @Override
            protected Boolean doInBackground(Void... voids) {
                return model.getProfessorsFromDB();
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
    public void setProfessors(List<Professor> professors) {
        model.setmCurrentProfessors(professors);
        view.setDataList(model.getmCurrentProfessors());
    }

    @Override
    public LiveData<List<Professor>> getProfessors() {
        return model.getData();
    }

    @SuppressLint("StaticFieldLeak")
    public void update(Professor professor) {
        view.navigateToUpdate(professor.getId());
    }

    @SuppressLint("StaticFieldLeak")
    public void delete(Professor professor) {
        new AsyncTask<Void, Void, Result>() {

            @Override
            protected Result doInBackground(Void... voids) {
                Result result = model.delProfessor(professor.getId());
                if (result.getMessage().equals("successful")) {
                    model.deleteProfessorInDb(professor);
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
