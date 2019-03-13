package org.wordy.kurswork.screens.news;

import android.arch.lifecycle.LiveData;

import org.wordy.kurswork.data.tables.News;

import java.util.List;

public interface NewsContract {

    interface View {
        void setDataList(List<News> Groups);

        void getData();
    }

    interface Model {
        Boolean getNewsFromDB();

        void setmCurrentNews(List<News> mCurrentNews);

        List<News> getmCurrentNews();

        LiveData<List<News>> getData();

        Boolean updateUsers(News news);
    }

    interface Presenter {
        void getNewsFromDb();

        void setNews(List<News> news);

        LiveData<List<News>> getNews();
    }

}
