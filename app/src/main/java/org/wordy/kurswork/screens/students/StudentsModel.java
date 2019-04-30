package org.wordy.kurswork.screens.students;

import android.app.Application;
import android.arch.lifecycle.LiveData;

import org.wordy.kurswork.data.DataBase;
import org.wordy.kurswork.data.rests.DeleteInfo;
import org.wordy.kurswork.data.rests.GetInfo;
import org.wordy.kurswork.data.tables.Group;
import org.wordy.kurswork.data.tables.Professor;
import org.wordy.kurswork.data.tables.Result;
import org.wordy.kurswork.data.tables.Students;

import java.util.List;

public class StudentsModel implements StudentsContract.Model {

    private DataBase dataBase;
    private GetInfo getInfo;
    private DeleteInfo deleteInfo;
    private static List<Students> mCurrentStudents;

    public StudentsModel(Application application) {
        this.dataBase = DataBase.getDataBase(application);
        this.getInfo = new GetInfo();
        this.deleteInfo = new DeleteInfo();
    }

    @Override
    public List<Students> getmCurrentStudents() {
        return mCurrentStudents;
    }

    @Override
    public LiveData<List<Students>> getData() {
        return dataBase.studentsDao().getAll();
    }

    @Override
    public void setmCurrentStudents(List<Students> mCurrentStudents) {
        StudentsModel.mCurrentStudents = mCurrentStudents;
    }

    @Override
    public Boolean getStudentsFromDB() {
        List<Students> students = getInfo.selectStudents();
        for (Students student : students) {
            dataBase.studentsDao().insert(student);
        }
        return true;
    }

    @Override
    public Result delStudent(int id) {
        return deleteInfo.delStudentbyId(id);
    }

    @Override
    public void deleteStudentInDb(Students students) {
        dataBase.studentsDao().delete(students);
    }
}
