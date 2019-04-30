package org.wordy.kurswork.screens.news;

import android.arch.lifecycle.LiveData;

import org.wordy.kurswork.data.tables.News;
import org.wordy.kurswork.data.tables.Result;

import java.util.List;

public interface NewsContract {

    interface View {
        void setDataList(List<News> Groups);

        void getData();

        void navigateToUpdate(int id);

        void showDialog(String message);
    }

    interface Model {
        Boolean getNewsFromDB();

        void setmCurrentNews(List<News> mCurrentNews);

        List<News> getmCurrentNews();

        LiveData<List<News>> getData();

        void deleteNewsInDb(News news);

        Result delNews(int id);
    }

    interface Presenter {
        void getNewsFromDb();

        void setNews(List<News> news);

        LiveData<List<News>> getNews();
    }

}
