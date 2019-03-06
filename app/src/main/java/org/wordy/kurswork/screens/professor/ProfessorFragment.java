package org.wordy.kurswork.screens.professor;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.wordy.kurswork.R;

public class ProfessorFragment extends Fragment {

    public ProfessorFragment() {
    }

    public static ProfessorFragment newInstance() {
        return new ProfessorFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_professor, container, false);
        return view;
    }

}
