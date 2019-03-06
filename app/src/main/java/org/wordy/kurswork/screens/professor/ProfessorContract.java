package org.wordy.kurswork.screens.professor;

import org.wordy.kurswork.data.tables.Professor;

import java.util.List;

public interface ProfessorContract {

    interface View {

    }

    interface Model {
        Boolean getProfessorsFromDB();
        void setmCurrentProfessors(List<Professor> mCurrentProfessors);
        List<Professor> getmCurrentProfessors();
    }

    interface Presenter {

    }

}
