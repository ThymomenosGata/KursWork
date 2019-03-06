package org.wordy.kurswork.screens.students;

import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import org.wordy.kurswork.R;
import org.wordy.kurswork.data.tables.Students;
import org.wordy.kurswork.data.tables.User;
import org.wordy.kurswork.screens.user.UserAdapter;

import java.util.List;

public class StudentsFragment extends Fragment implements StudentsContract.View {

    private StudentsPresenter presenter;
    private StudentsModel model;
    private ListView listView;
    private StudentsAdapter adapter;

    public StudentsFragment() {
    }

    public static StudentsFragment newInstance() {
        return new StudentsFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_students, container, false);

        listView = view.findViewById(R.id.students_list);
        model = new StudentsModel(getActivity().getApplication());
        presenter = new StudentsPresenter(model, this);

        presenter.getStudentsFromDb();

        return view;
    }

    @Override
    public void setDataList(List<Students> students) {
        adapter = new StudentsAdapter(students, getContext());
        listView.setAdapter(adapter);
    }

    @Override
    public void getData() {
        presenter.getStudents().observe(this, new Observer<List<Students>>() {
            @Override
            public void onChanged(@Nullable List<Students> students) {
                presenter.setStudents(students);
            }
        });
    }
}
