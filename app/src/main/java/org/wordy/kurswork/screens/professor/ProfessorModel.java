package org.wordy.kurswork.screens.professor;

import android.app.Application;
import android.arch.lifecycle.LiveData;

import org.wordy.kurswork.data.DataBase;
import org.wordy.kurswork.data.rests.DeleteInfo;
import org.wordy.kurswork.data.rests.GetInfo;
import org.wordy.kurswork.data.tables.Professor;
import org.wordy.kurswork.data.tables.Result;

import java.util.List;

public class ProfessorModel implements ProfessorContract.Model {

    private DataBase dataBase;
    private GetInfo getInfo;
    private DeleteInfo deleteInfo;
    private static List<Professor> mCurrentProfessors;

    public ProfessorModel(Application application) {
        this.dataBase = DataBase.getDataBase(application);
        this.getInfo = new GetInfo();
        this.deleteInfo = new DeleteInfo();
    }

    @Override
    public List<Professor> getmCurrentProfessors() {
        return mCurrentProfessors;
    }

    @Override
    public LiveData<List<Professor>> getData() {
        return dataBase.professorDao().getAll();
    }

    @Override
    public void setmCurrentProfessors(List<Professor> mCurrentProfessors) {
        ProfessorModel.mCurrentProfessors = mCurrentProfessors;
    }

    @Override
    public Boolean getProfessorsFromDB() {
        List<Professor> professors = getInfo.selectProfessor();
        for (Professor professor : professors) {
            dataBase.professorDao().insert(professor);
        }
        return true;
    }

    @Override
    public Result delProfessor(int id) {
        return deleteInfo.delProfessorById(id);
    }

    @Override
    public void deleteProfessorInDb(Professor professor) {
        dataBase.professorDao().delete(professor);
    }

}
