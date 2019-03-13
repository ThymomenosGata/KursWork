package org.wordy.kurswork.screens.post_professor;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.wordy.kurswork.R;
import org.wordy.kurswork.data.tables.User;

import java.util.List;

public class SpinnerAdapter extends ArrayAdapter<User> {

    private List<User> users;
    private Activity mContext;

    public SpinnerAdapter(@NonNull Activity context, int resource, @NonNull List<User> objects) {
        super(context, resource, objects);
        this.mContext = context;
        this.users = objects;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return getView(position, convertView, parent);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View row = convertView;
        if (row == null) {
            LayoutInflater inflater = mContext.getLayoutInflater();
            row = inflater.inflate(R.layout.item_spinner_users, parent, false);
        }

        User user = users.get(position);

        if (user != null) {
            TextView login = row.findViewById(R.id.users);
            login.setText(user.getLogin());
        }
        return row;
    }

    @Override
    public User getItem(int position) {
        return users.get(position);
    }

    public int getPosItem(int id) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId() == id) {
                return i;
            }
        }
        return -1;
    }

}
