package org.wordy.kurswork.screens.post_professor;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.wordy.kurswork.R;

public class PostProfessorFragment extends Fragment {

    public PostProfessorFragment() {
    }

    public static PostProfessorFragment newInstance() {
        return new PostProfessorFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmetn_post_professors, container, false);
        return view;
    }
}
