package org.wordy.kurswork.screens.post_students;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.wordy.kurswork.R;
import org.wordy.kurswork.data.tables.Group;

import java.util.List;

public class SpinnerAdapterGroup extends ArrayAdapter<Group> {

    private List<Group> groups;
    private Activity mContext;

    public SpinnerAdapterGroup(@NonNull Activity context, int resource, @NonNull List<Group> objects) {
        super(context, resource, objects);
        this.mContext = context;
        this.groups = objects;
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

        Group group = groups.get(position);

        if (group != null) {
            TextView login = row.findViewById(R.id.users);
            login.setText(group.getName());
        }
        return row;
    }

    @Override
    public Group getItem(int position) {
        return groups.get(position);
    }

    public int getPosItem(int id) {
        for (int i = 0; i < groups.size(); i++) {
            if (groups.get(i).getId() == id) {
                return i;
            }
        }
        return -1;
    }

}
