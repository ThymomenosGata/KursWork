package org.wordy.kurswork.screens.user;

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
import org.wordy.kurswork.data.tables.User;

import java.util.List;

public class UserFragment extends Fragment implements UserContract.View {

    private UserPresenter presenter;
    private UserModel model;
    private ListView listView;
    private UserAdapter adapter;

    public UserFragment() {
    }

    public static UserFragment newInstance() {
        return new UserFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user, container, false);

        listView = view.findViewById(R.id.user_list);
        model = new UserModel(getActivity().getApplication());
        presenter = new UserPresenter(model, this);

        presenter.getUsersFromDb();


        return view;
    }

    @Override
    public void getData() {
        presenter.getUsers().observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(@Nullable List<User> users) {
                presenter.setUsers(users);
            }
        });
    }

    @Override
    public void setDataList(List<User> users) {
        adapter = new UserAdapter(users, getContext());
        listView.setAdapter(adapter);
    }

}
