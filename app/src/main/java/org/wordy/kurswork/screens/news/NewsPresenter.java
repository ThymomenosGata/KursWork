package org.wordy.kurswork.screens.news;

import android.annotation.SuppressLint;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import org.wordy.kurswork.data.tables.News;
import org.wordy.kurswork.data.tables.Result;

import java.util.List;

public class NewsPresenter implements NewsContract.Presenter {

    private NewsModel model;
    private NewsContract.View view;

    public NewsPresenter(NewsModel model, NewsContract.View view) {
        this.model = model;
        this.view = view;
    }

    @SuppressLint("StaticFieldLeak")
    @Override
    public void getNewsFromDb() {
        new AsyncTask<Void, Void, Boolean>() {
            @Override
            protected Boolean doInBackground(Void... voids) {
                return model.getNewsFromDB();
            }

            @Override
            protected void onPostExecute(Boolean aBoolean) {
                super.onPostExecute(aBoolean);
                if (aBoolean) {
                    view.getData();
                }
            }
        }.execute();
    }

    @Override
    public void setNews(List<News> news) {
        model.setmCurrentNews(news);
        view.setDataList(model.getmCurrentNews());
    }

    @Override
    public LiveData<List<News>> getNews() {
        return model.getData();
    }

    @SuppressLint("StaticFieldLeak")
    public void update(News news) {
        view.navigateToUpdate(news.getId());
    }

    @SuppressLint("StaticFieldLeak")
    public void delete(News news) {
        new AsyncTask<Void, Void, Result>() {

            @Override
            protected Result doInBackground(Void... voids) {
                Result result = model.delNews(news.getId());
                if (result.getMessage().equals("successful")) {
                    model.deleteNewsInDb(news);
                }
                return result;
            }

            @Override
            protected void onPostExecute(Result result) {
                super.onPostExecute(result);
                if (!result.getMessage().equals("successful")) {
                    view.showDialog(result.getMessage());
                }
            }
        }.execute();
    }
}
