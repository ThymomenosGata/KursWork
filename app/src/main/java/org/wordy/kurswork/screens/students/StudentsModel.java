package org.wordy.kurswork.screens.students;

import android.app.Application;

import org.wordy.kurswork.data.DataBase;
import org.wordy.kurswork.data.connect.GetInfo;
import org.wordy.kurswork.data.tables.Students;

import java.util.List;

public class StudentsModel implements StudentsContract.Model {

    private DataBase dataBase;
    private GetInfo getInfo;
    private static List<Students> mCurrentStudents;

    public StudentsModel(Application application) {
        this.dataBase = DataBase.getDataBase(application);
        this.getInfo = new GetInfo();
    }

    @Override
    public List<Students> getmCurrentStudents() {
        return mCurrentStudents;
    }

    @Override
    public void setmCurrentStudents(List<Students> mCurrentStudents) {
        StudentsModel.mCurrentStudents = mCurrentStudents;
    }

    @Override
    public Boolean getStudentsFromDB() {
        List<Students> students = getInfo.selectStudent();
        for (Students student : students) {
            dataBase.studentsDao().insert(student);
        }
        return true;
    }

}
