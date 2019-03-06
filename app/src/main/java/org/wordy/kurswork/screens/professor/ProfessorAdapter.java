package org.wordy.kurswork.screens.professor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.wordy.kurswork.R;
import org.wordy.kurswork.data.tables.Professor;

import java.util.List;

public class ProfessorAdapter extends BaseAdapter {

    private List<Professor> professors;
    private Context ctx;
    LayoutInflater inflater;

    public ProfessorAdapter(List<Professor> professors, Context ctx) {
        this.professors = professors;
        this.ctx = ctx;
        this.inflater = (LayoutInflater) ctx
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return professors.size();
    }

    @Override
    public Object getItem(int position) {
        return professors.get(position);
    }

    @Override
    public long getItemId(int position) {
        return professors.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = inflater.inflate(R.layout.item_professor, parent, false);
        }

        Professor professor = getProfessor(position);

        ((TextView) view.findViewById(R.id.surname)).setText(professor.getSurname());
        ((TextView) view.findViewById(R.id.name)).setText(professor.getName());
        ((TextView) view.findViewById(R.id.middlename)).setText(professor.getMiddlename());
        ((TextView) view.findViewById(R.id.position)).setText(professor.getPosition());

        return view;
    }

    Professor getProfessor(int position) {
        return (Professor) getItem(position);
    }

}
