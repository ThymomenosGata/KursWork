package org.wordy.kurswork.screens.students;

import android.arch.lifecycle.LiveData;

import org.wordy.kurswork.data.tables.Students;

import java.util.List;

public interface StudentsContract {

    interface View {
        void setDataList(List<Students> students);

        void getData();
    }

    interface Model {
        Boolean getStudentsFromDB();

        void setmCurrentStudents(List<Students> mCurrentStudents);

        List<Students> getmCurrentStudents();

        LiveData<List<Students>> getData();
    }

    interface Presenter {
        void getStudentsFromDb();

        void setStudents(List<Students> users);

        LiveData<List<Students>> getStudents();
    }

}
