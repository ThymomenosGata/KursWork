package org.wordy.kurswork.data.tables;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import org.json.JSONException;
import org.json.JSONObject;

@Entity(
        tableName = "news"
)
public class News {
    @PrimaryKey
    int id;
    String title;
    String small_sedcription;
    String full_description;
    String date_publish;
    String date_last_modify;
    int is_published;
    int author;

    public News() {
    }

    @Ignore
    public News(int id, String title, String small_sedcription, String full_description, String date_publish, String date_last_modify, int is_published, int author) {
        this.id = id;
        this.title = title;
        this.small_sedcription = small_sedcription;
        this.full_description = full_description;
        this.date_publish = date_publish;
        this.date_last_modify = date_last_modify;
        this.is_published = is_published;
        this.author = author;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSmall_sedcription() {
        return small_sedcription;
    }

    public void setSmall_sedcription(String small_sedcription) {
        this.small_sedcription = small_sedcription;
    }

    public String getFull_description() {
        return full_description;
    }

    public void setFull_description(String full_description) {
        this.full_description = full_description;
    }

    public String getDate_publish() {
        return date_publish;
    }

    public void setDate_publish(String date_publish) {
        this.date_publish = date_publish;
    }

    public String getDate_last_modify() {
        return date_last_modify;
    }

    public void setDate_last_modify(String date_last_modify) {
        this.date_last_modify = date_last_modify;
    }

    public int getIs_published() {
        return is_published;
    }

    public void setIs_published(int is_published) {
        this.is_published = is_published;
    }

    public int getAuthor() {
        return author;
    }

    public void setAuthor(int author) {
        this.author = author;
    }

    public static News fromJson(JSONObject json) throws JSONException {
        News news = new News();
        news.id = json.getInt("id");
        news.title = json.getString("title");
        news.small_sedcription = json.getString("small_sedcription");
        news.full_description = json.getString("full_description");
        news.date_publish = json.getString("date_published");
        news.date_last_modify = json.getString("date_last_modify");
        news.is_published = json.getInt("is_published");
        news.author = json.getInt("author");
        return news;
    }
}
