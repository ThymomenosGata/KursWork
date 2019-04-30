package org.wordy.kurswork.screens.news;

import android.app.Application;
import android.arch.lifecycle.LiveData;

import org.wordy.kurswork.data.DataBase;
import org.wordy.kurswork.data.rests.DeleteInfo;
import org.wordy.kurswork.data.rests.GetInfo;
import org.wordy.kurswork.data.tables.Group;
import org.wordy.kurswork.data.tables.News;
import org.wordy.kurswork.data.tables.Result;

import java.util.List;

public class NewsModel implements NewsContract.Model {

    private DataBase dataBase;
    private GetInfo getInfo;
    private DeleteInfo deleteInfo;
    private static List<News> mCurrentNews;

    public NewsModel(Application application) {
        this.dataBase = DataBase.getDataBase(application);
        this.getInfo = new GetInfo();
        this.deleteInfo = new DeleteInfo();
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

    @Override
    public Result delNews(int id) {
        return deleteInfo.delNewsById(id);
    }

    @Override
    public void deleteNewsInDb(News news) {
        dataBase.newsDao().delete(news);
    }

}
