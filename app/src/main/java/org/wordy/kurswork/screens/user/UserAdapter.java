package org.wordy.kurswork.screens.user;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.wordy.kurswork.R;
import org.wordy.kurswork.data.tables.User;

import java.util.List;

public class UserAdapter extends BaseAdapter {

    private List<User> users;
    private Context ctx;
    LayoutInflater inflater;

    public UserAdapter(List<User> users, Context ctx) {
        this.users = users;
        this.ctx = ctx;
        this.inflater = (LayoutInflater) ctx
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return users.size();
    }

    @Override
    public Object getItem(int position) {
        return users.get(position);
    }

    @Override
    public long getItemId(int position) {
        return users.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = inflater.inflate(R.layout.item_user, parent, false);
        }

        User user = getUser(position);

        ((TextView) view.findViewById(R.id.login)).setText(user.getLogin());
        if (user.getIs_blocked() == 0) {
            ((TextView) view.findViewById(R.id.isblocked)).setText("Не заблокирован");
        } else {
            ((TextView) view.findViewById(R.id.isblocked)).setText("Заблокирован");
        }
        ((TextView) view.findViewById(R.id.date)).setText(user.getDate_last_modify());

        return view;
    }

    User getUser(int position) {
        return (User) getItem(position);
    }
}
