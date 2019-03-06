package org.wordy.kurswork.screens.professor;

import android.app.Application;

import org.wordy.kurswork.data.DataBase;
import org.wordy.kurswork.data.connect.GetInfo;
import org.wordy.kurswork.data.tables.Professor;

import java.util.List;

public class ProfessorModel implements ProfessorContract.Model {

    private DataBase dataBase;
    private GetInfo getInfo;
    private static List<Professor> mCurrentProfessors;

    public ProfessorModel(Application application) {
        this.dataBase = DataBase.getDataBase(application);
        this.getInfo = new GetInfo();
    }

    @Override
    public List<Professor> getmCurrentProfessors() {
        return mCurrentProfessors;
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

}
