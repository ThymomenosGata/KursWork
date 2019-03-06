package org.wordy.kurswork.screens.news;

import org.wordy.kurswork.data.tables.News;

import java.util.List;

public interface NewsContract {

    interface View {

    }

    interface Model {
        Boolean getNewsFromDB();
        void setmCurrentNews(List<News> mCurrentNews);
        List<News> getmCurrentNews();
    }

    interface Presenter {

    }

}
