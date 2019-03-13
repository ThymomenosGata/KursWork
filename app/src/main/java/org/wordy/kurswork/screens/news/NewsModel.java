package org.wordy.kurswork.screens.news;

import android.app.Application;
import android.arch.lifecycle.LiveData;

import org.wordy.kurswork.data.DataBase;
import org.wordy.kurswork.data.rests.GetInfo;
import org.wordy.kurswork.data.tables.News;

import java.util.List;

public class NewsModel implements NewsContract.Model {

    private DataBase dataBase;
    private GetInfo getInfo;
    private static List<News> mCurrentNews;

    public NewsModel(Application application) {
        this.dataBase = DataBase.getDataBase(application);
        this.getInfo = new GetInfo();
    }

    @Override
    public List<News> getmCurrentNews() {
        return mCurrentNews;
    }

    @Override
    public LiveData<List<News>> getData() {
        return dataBase.newsDao().getAll();
    }

    @Override
    public Boolean updateUsers(News news) {
        if (getInfo.updateNews(news)) {
            dataBase.newsDao().insert(news);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void setmCurrentNews(List<News> mCurrentNews) {
        NewsModel.mCurrentNews = mCurrentNews;
    }

    @Override
    public Boolean getNewsFromDB() {
        List<News> news = getInfo.selectNews();
        for (News news1 : news) {
            dataBase.newsDao().insert(news1);
        }
        return true;
    }

}
