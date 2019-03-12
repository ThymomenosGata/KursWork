package org.wordy.kurswork.screens;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import org.wordy.kurswork.R;
import org.wordy.kurswork.screens.post_group.PostGroupFragment;
import org.wordy.kurswork.screens.post_news.PostNewsFragment;
import org.wordy.kurswork.screens.post_professor.PostProfessorFragment;
import org.wordy.kurswork.screens.post_students.PostStudentsFragment;
import org.wordy.kurswork.screens.post_user.PostUserFragment;

public class PostActivity extends AppCompatActivity {

    private static final String APP_PREFERENCES = "mysettings";
    private static final String APP_PREFERENCES_ID = "id";
    private SharedPreferences mSettings;
    private int flag = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        mSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);

        if(mSettings.contains(APP_PREFERENCES_ID)) {
            flag = mSettings.getInt(APP_PREFERENCES_ID, 0);
        }

        switch (flag) {
            case 1: {
                loadFragment(PostUserFragment.newInstance());
                break;
            }
            case 2: {
                loadFragment(PostStudentsFragment.newInstance());
                break;
            }
            case 3: {
                loadFragment(PostProfessorFragment.newInstance());
                break;
            }
            case 4: {
                loadFragment(PostGroupFragment.newInstance());
                break;
            }
            case 5: {
                loadFragment(PostNewsFragment.newInstance());
                break;
            }
        }

    }

    private void loadFragment(Fragment fragment) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.container, fragment);
        ft.commit();
    }
}
