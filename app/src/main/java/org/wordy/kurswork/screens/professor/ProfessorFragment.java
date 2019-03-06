package org.wordy.kurswork.screens.professor;

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
import org.wordy.kurswork.data.tables.Professor;
import org.wordy.kurswork.screens.news.NewsModel;
import org.wordy.kurswork.screens.news.NewsPresenter;

import java.util.List;

public class ProfessorFragment extends Fragment implements ProfessorContract.View {

    private ProfessorPresenter presenter;
    private ProfessorModel model;
    private ListView listView;
    private ProfessorAdapter adapter;

    public ProfessorFragment() {
    }

    public static ProfessorFragment newInstance() {
        return new ProfessorFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_professor, container, false);

        listView = view.findViewById(R.id.professors_list);
        model = new ProfessorModel(getActivity().getApplication());
        presenter = new ProfessorPresenter(model, this);

        presenter.getProfessorsFromDb();

        return view;
    }

    @Override
    public void setDataList(List<Professor> professors) {
        adapter = new ProfessorAdapter(professors, getContext());
        listView.setAdapter(adapter);
    }

    @Override
    public void getData() {
        presenter.getProfessors().observe(this, new Observer<List<Professor>>() {
            @Override
            public void onChanged(@Nullable List<Professor> professors) {
                presenter.setProfessors(professors);
            }
        });
    }
}
