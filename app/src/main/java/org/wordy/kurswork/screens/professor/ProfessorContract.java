package org.wordy.kurswork.screens.professor;

import android.arch.lifecycle.LiveData;

import org.wordy.kurswork.data.tables.Professor;
import org.wordy.kurswork.data.tables.Result;

import java.util.List;

public interface ProfessorContract {

    interface View {
        void setDataList(List<Professor> professors);

        void getData();

        void showDialog(String message);

        void navigateToUpdate(int id);
    }

    interface Model {
        Boolean getProfessorsFromDB();

        void setmCurrentProfessors(List<Professor> mCurrentProfessors);

        List<Professor> getmCurrentProfessors();

        LiveData<List<Professor>> getData();

        Result delProfessor(int id);

        void deleteProfessorInDb(Professor professor);
    }

    interface Presenter {
        void getProfessorsFromDb();

        void setProfessors(List<Professor> professors);

        LiveData<List<Professor>> getProfessors();
    }

}
