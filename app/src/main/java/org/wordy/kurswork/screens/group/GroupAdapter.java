package org.wordy.kurswork.screens.group;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.wordy.kurswork.R;
import org.wordy.kurswork.data.tables.Group;

import java.util.List;

public class GroupAdapter extends BaseAdapter {

    private List<Group> groups;
    private Context ctx;
    LayoutInflater inflater;

    public GroupAdapter(List<Group> groups, Context ctx) {
        this.groups = groups;
        this.ctx = ctx;
        this.inflater = (LayoutInflater) ctx
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return groups.size();
    }

    @Override
    public Object getItem(int position) {
        return groups.get(position);
    }

    @Override
    public long getItemId(int position) {
        return groups.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = inflater.inflate(R.layout.item_group, parent, false);
        }

        Group group = getGroup(position);

        ((TextView) view.findViewById(R.id.name)).setText(group.getName());
        ((TextView) view.findViewById(R.id.count)).setText(String.valueOf(String.valueOf(group.getCount())));
        ((TextView) view.findViewById(R.id.faculty)).setText(group.getFaculty());
        ((TextView) view.findViewById(R.id.date)).setText(group.getDate_last_modify());

        return view;
    }

    Group getGroup(int position) {
        return (Group) getItem(position);
    }

}
