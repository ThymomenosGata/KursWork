package org.wordy.kurswork.screens.students;

import org.wordy.kurswork.data.tables.Students;

import java.util.List;

public interface StudentsContract {

    interface View {

    }

    interface Model {
        Boolean getStudentsFromDB();
        void setmCurrentStudents(List<Students> mCurrentStudents);
        List<Students> getmCurrentStudents();
    }

    interface Presenter {

    }

}
