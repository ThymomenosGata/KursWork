package org.wordy.kurswork.screens.students;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.wordy.kurswork.R;
import org.wordy.kurswork.data.tables.Students;

import java.util.List;

public class StudentsAdapter extends BaseAdapter {

    private List<Students> students;
    private Context ctx;
    LayoutInflater inflater;

    public StudentsAdapter(List<Students> students, Context ctx) {
        this.students = students;
        this.ctx = ctx;
        this.inflater = (LayoutInflater) ctx
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return students.size();
    }

    @Override
    public Object getItem(int position) {
        return students.get(position);
    }

    @Override
    public long getItemId(int position) {
        return students.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = inflater.inflate(R.layout.item_students, parent, false);
        }

        Students student = getStudent(position);

        ((TextView) view.findViewById(R.id.surname)).setText(student.getSurname());
        ((TextView) view.findViewById(R.id.name)).setText(student.getName());
        ((TextView) view.findViewById(R.id.middlename)).setText(student.getMiddlename());
        ((TextView) view.findViewById(R.id.group)).setText(String.valueOf(student.getGroupID()));

        return view;
    }

    Students getStudent(int position) {
        return (Students) getItem(position);
    }
}
