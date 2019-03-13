package org.wordy.kurswork.screens.post_news;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import org.wordy.kurswork.R;
import org.wordy.kurswork.data.DataBase;
import org.wordy.kurswork.data.rests.GetInfo;
import org.wordy.kurswork.data.rests.PostInfo;
import org.wordy.kurswork.data.tables.News;
import org.wordy.kurswork.data.tables.Professor;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PostNewsFragment extends Fragment {

    private EditText mTitle, mSmallDescrition, mFullDescription;
    private Spinner spinner;
    private Button mSend;
    private PostInfo postInfo;
    private GetInfo getInfo;
    private DataBase dataBase;
    private SpinnerAdapterProfessors adapterProfessors;

    private static final String APP_PREFERENCES = "mysettings";
    private static final String APP_PREFERENCES_UPD = "upd";
    private SharedPreferences mSettings;
    private int upd = 0;
    private News news;
    private Date date;

    public PostNewsFragment() {
    }

    public static PostNewsFragment newInstance() {
        return new PostNewsFragment();
    }

    @SuppressLint("StaticFieldLeak")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_post_news, container, false);

        mSettings = getActivity().getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);

        if (mSettings.contains(APP_PREFERENCES_UPD)) {
            upd = mSettings.getInt(APP_PREFERENCES_UPD, 0);
        }

        spinner = view.findViewById(R.id.spinner2);
        mTitle = view.findViewById(R.id.title);
        mSmallDescrition = view.findViewById(R.id.small_description);
        mFullDescription = view.findViewById(R.id.full_description);
        mSend = view.findViewById(R.id.send);

        news = new News();

        postInfo = new PostInfo();
        getInfo = new GetInfo();
        dataBase = DataBase.getDataBase(getActivity().getApplicationContext());

        date = new Date();
        @SuppressLint("SimpleDateFormat")
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

        dataBase.professorDao().getAll().observe(this, professors -> {
            adapterProfessors = new SpinnerAdapterProfessors(getActivity(), R.layout.item_spinner_users, professors);
            spinner.setAdapter(adapterProfessors);
        });

        if (upd == 0) {
            mSend.setOnClickListener(v -> {
                News news = new News(0, mTitle.getText().toString(), mSmallDescrition.getText().toString(),
                        mFullDescription.getText().toString(), dateFormatter.format(date),
                        dateFormatter.format(date), 0, adapterProfessors.getItem(spinner.getSelectedItemPosition()).getId());
                setData(view, news);
            });
        } else {
            new AsyncTask<Void, Void, News>() {

                @Override
                protected News doInBackground(Void... voids) {
                    return dataBase.newsDao().getNewsById(upd);
                }

                @Override
                protected void onPostExecute(News news1) {
                    super.onPostExecute(news1);
                    mTitle.setText(news1.getTitle());
                    mSmallDescrition.setText(news1.getSmall_sedcription());
                    mFullDescription.setText(news1.getFull_description());

                    news.setId(news1.getId());
                    news.setTitle(news1.getTitle());
                    news.setSmall_sedcription(news1.getSmall_sedcription());
                    news.setFull_description(news1.getFull_description());
                    news.setAuthor(news1.getAuthor());
                    news.setIs_published(news1.getIs_published());
                    news.setDate_publish(news1.getDate_publish());
                    news.setDate_last_modify(news1.getDate_last_modify());
                }
            }.execute();

            mSend.setOnClickListener(v -> {
                news.setTitle(mTitle.getText().toString());
                news.setIs_published(0);
                news.setAuthor(adapterProfessors.getItem(spinner.getSelectedItemPosition()).getId());
                news.setFull_description(mFullDescription.getText().toString());
                news.setSmall_sedcription(mSmallDescrition.getText().toString());
                news.setDate_last_modify(dateFormatter.format(date));
                setUpdData(view, news);
            });
        }

        return view;
    }

    @SuppressLint("StaticFieldLeak")
    private void setData(View view, News news) {
        new AsyncTask<News, Void, Boolean>() {

            @Override
            protected Boolean doInBackground(News... news1) {
                boolean flag = postInfo.insertNews(news1[0]);
                news1[0] = getInfo.getNewsByName(news1[0].getTitle());
                dataBase.newsDao().insert(news1[0]);
                return flag;
            }

            @Override
            protected void onPostExecute(Boolean aBoolean) {
                super.onPostExecute(aBoolean);
                if (aBoolean) {
                    Snackbar.make(view, "отправлено", Snackbar.LENGTH_LONG).show();
                    getActivity().finish();
                } else {
                    Snackbar.make(view, "не отправлено", Snackbar.LENGTH_LONG).show();
                }
            }
        }.execute(news);
    }

    @SuppressLint("StaticFieldLeak")
    private void setUpdData(View view, News news) {
        new AsyncTask<News, Void, Boolean>() {

            @Override
            protected Boolean doInBackground(News... news1) {
                boolean flag = getInfo.updateNews(news1[0]);
                dataBase.newsDao().insert(news);
                return flag;
            }

            @Override
            protected void onPostExecute(Boolean aBoolean) {
                super.onPostExecute(aBoolean);
                if (aBoolean) {
                    Snackbar.make(view, "отправлено", Snackbar.LENGTH_LONG).show();
                    getActivity().finish();
                } else {
                    Snackbar.make(view, "не отправлено", Snackbar.LENGTH_LONG).show();
                }
            }
        }.execute(news);
    }

}
