package org.wordy.kurswork.screens.login;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.wordy.kurswork.R;
import org.wordy.kurswork.screens.MainActivity;
import org.wordy.kurswork.screens.RegistrationActivity;

public class LoginActivity extends AppCompatActivity implements LoginContract.View {

    private LoginModel model;
    private LoginPresenter presenter;

    private EditText mLogin, mPassword;
    private Button mButtonLogin, mButtonReg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mLogin = findViewById(R.id.login);
        mPassword = findViewById(R.id.password);
        mButtonLogin = findViewById(R.id.log_in);
        mButtonReg = findViewById(R.id.registration);

        model = new LoginModel(getApplication());
        presenter = new LoginPresenter(model, this);

        mButtonLogin.setOnClickListener(v -> presenter.checkUser(mLogin.getText().toString(), mPassword.getText().toString()));
        mButtonReg.setOnClickListener(v -> presenter.navigateToRegistration());
    }

    @Override
    public void showDialog(String message) {
        new AlertDialog.Builder(LoginActivity.this)
                .setTitle(getResources().getString(R.string.warning))
                .setMessage(message)
                .setPositiveButton(android.R.string.ok, null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    @Override
    public void navigateToMainActivity() {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void navigateToRegistrationActivity() {
        Intent intent = new Intent(LoginActivity.this, RegistrationActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

}
