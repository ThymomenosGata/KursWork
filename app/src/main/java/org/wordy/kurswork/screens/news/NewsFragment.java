package org.wordy.kurswork.screens.news;

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
import org.wordy.kurswork.data.tables.News;
import org.wordy.kurswork.screens.group.GroupAdapter;
import org.wordy.kurswork.screens.group.GroupModel;
import org.wordy.kurswork.screens.group.GroupPresenter;

import java.util.List;

public class NewsFragment extends Fragment implements NewsContract.View {

    private NewsPresenter presenter;
    private NewsModel model;
    private ListView listView;
    private NewsAdapter adapter;

    public NewsFragment() {
    }

    public static NewsFragment newInstance() {
        return new NewsFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news, container, false);

        listView = view.findViewById(R.id.news_list);
        model = new NewsModel(getActivity().getApplication());
        presenter = new NewsPresenter(model, this);

        presenter.getNewsFromDb();

        return view;
    }

    @Override
    public void setDataList(List<News> news) {
        adapter = new NewsAdapter(news, getContext());
        listView.setAdapter(adapter);
    }

    @Override
    public void getData() {
        presenter.getNews().observe(this, new Observer<List<News>>() {
            @Override
            public void onChanged(@Nullable List<News> news) {
                presenter.setNews(news);
            }
        });
    }
}
