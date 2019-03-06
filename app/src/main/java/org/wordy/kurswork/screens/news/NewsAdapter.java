package org.wordy.kurswork.screens.news;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.wordy.kurswork.R;
import org.wordy.kurswork.data.tables.News;

import java.util.List;

public class NewsAdapter extends BaseAdapter {

    private List<News> news;
    private Context ctx;
    LayoutInflater inflater;

    public NewsAdapter(List<News> news, Context ctx) {
        this.news = news;
        this.ctx = ctx;
        this.inflater = (LayoutInflater) ctx
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getCount() {
        return news.size();
    }

    @Override
    public Object getItem(int position) {
        return news.get(position);
    }

    @Override
    public long getItemId(int position) {
        return news.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = inflater.inflate(R.layout.item_news, parent, false);
        }

        News news = getNews(position);

        ((TextView) view.findViewById(R.id.title)).setText(news.getTitle());
        ((TextView) view.findViewById(R.id.small_description)).setText(String.valueOf(String.valueOf(news.getSmall_sedcription())));
        ((TextView) view.findViewById(R.id.full_description)).setText(news.getFull_description());
        ((TextView) view.findViewById(R.id.date)).setText(news.getDate_last_modify());

        return view;
    }

    News getNews(int position) {
        return (News) getItem(position);
    }
}
