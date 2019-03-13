package org.wordy.kurswork.screens.news;

import android.annotation.SuppressLint;
import android.arch.lifecycle.Observer;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import org.wordy.kurswork.R;
import org.wordy.kurswork.data.DataBase;
import org.wordy.kurswork.data.rests.GetInfo;
import org.wordy.kurswork.data.tables.News;
import org.wordy.kurswork.screens.PostActivity;

import java.util.List;

public class NewsFragment extends Fragment implements NewsContract.View {

    private NewsPresenter presenter;
    private NewsModel model;
    private ListView listView;
    private NewsAdapter adapter;
    private GetInfo getInfo;

    private static final String APP_PREFERENCES = "mysettings";
    private static final String APP_PREFERENCES_UPD = "upd";
    private SharedPreferences mSettings;
    private int upd = 0;
    private SharedPreferences.Editor editor;
    private DataBase dataBase;

    public NewsFragment() {
    }

    public static NewsFragment newInstance() {
        return new NewsFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news, container, false);

        mSettings = getActivity().getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);

        if (mSettings.contains(APP_PREFERENCES_UPD)) {
            upd = mSettings.getInt(APP_PREFERENCES_UPD, 0);
        }
        editor = mSettings.edit();
        getInfo = new GetInfo();
        dataBase = DataBase.getDataBase(getContext());

        listView = view.findViewById(R.id.news_list);
        model = new NewsModel(getActivity().getApplication());
        presenter = new NewsPresenter(model, this);

        presenter.getNewsFromDb();

        registerForContextMenu(listView);

        return view;
    }

    @Override
    public void setDataList(List<News> news) {
        adapter = new NewsAdapter(news, getContext());
        listView.setAdapter(adapter);
    }

    @Override
    public void getData() {
        presenter.getNews().observe(this, news -> presenter.setNews(news));
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getActivity().getMenuInflater();
        inflater.inflate(R.menu.context_menu, menu);
    }

    @SuppressLint("StaticFieldLeak")
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()) {
            case R.id.delete: {
                new AsyncTask<Void, Void, Void>() {

                    @Override
                    protected Void doInBackground(Void... voids) {
                        getInfo.delUserById(adapter.getNews(info.position).getId());
                        dataBase.newsDao().delete(adapter.getNews(info.position));
                        return null;
                    }
                }.execute();
                return true;
            }
            case R.id.update: {
                navigateToUpdate(adapter.getNews(info.position).getId());
                return true;
            }
            default:
                return super.onContextItemSelected(item);
        }
    }

    public void navigateToUpdate(int id) {
        Intent intent = new Intent(getActivity(), PostActivity.class);
        editor.putInt(APP_PREFERENCES_UPD, id);
        editor.apply();
        startActivity(intent);
    }



}
