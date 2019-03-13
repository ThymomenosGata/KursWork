package org.wordy.kurswork.screens.post_news;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.wordy.kurswork.R;
import org.wordy.kurswork.data.tables.Professor;
import org.wordy.kurswork.data.tables.User;

import java.util.List;

public class SpinnerAdapterProfessors extends ArrayAdapter<Professor> {

    private List<Professor> professors;
    private Activity mContext;

    public SpinnerAdapterProfessors(@NonNull Activity context, int resource, @NonNull List<Professor> objects) {
        super(context, resource, objects);
        this.mContext = context;
        this.professors = objects;
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

        Professor professor = professors.get(position);

        if (professor != null) {
            TextView login = row.findViewById(R.id.users);
            login.setText(professor.getName());
        }
        return row;
    }

    @Override
    public Professor getItem(int position) {
        return professors.get(position);
    }

    public int getPosItem(int id) {
        for (int i = 0; i < professors.size(); i++) {
            if (professors.get(i).getId() == id) {
                return i;
            }
        }
        return -1;
    }

}
