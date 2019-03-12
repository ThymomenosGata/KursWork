package org.wordy.kurswork.screens.post_news;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.wordy.kurswork.R;

public class PostNewsFragment extends Fragment {

    public PostNewsFragment() {
    }

    public static PostNewsFragment newInstance() {
        return new PostNewsFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_post_news, container, false);
        return view;
    }
}
