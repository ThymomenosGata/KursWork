package org.wordy.kurswork.screens.group;

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
import org.wordy.kurswork.data.tables.Group;

import java.util.List;

public class GroupFragment extends Fragment implements GroupContract.View {

    private GroupPresenter presenter;
    private GroupModel model;
    private ListView listView;
    private GroupAdapter adapter;

    public GroupFragment() {
    }

    public static GroupFragment newInstance() {
        return new GroupFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_group, container, false);

        listView = view.findViewById(R.id.students_list);
        model = new GroupModel(getActivity().getApplication());
        presenter = new GroupPresenter(model, this);

        presenter.getGroupsFromDb();

        return view;
    }

    @Override
    public void setDataList(List<Group> groups) {
        adapter = new GroupAdapter(groups, getContext());
        listView.setAdapter(adapter);
    }

    @Override
    public void getData() {
        presenter.getGroups().observe(this, new Observer<List<Group>>() {
            @Override
            public void onChanged(@Nullable List<Group> groups) {
                presenter.setGroups(groups);
            }
        });
    }
}
