package com.example.phase2.usermanagementactivities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.phase2.Initializable;
import com.example.phase2.R;
import com.example.phase2.appcore.User;
import com.example.phase2.appcore.UserManager;
import com.example.phase2.scoreboard.ScoreBoard;
import com.example.phase2.usermanagementactivities.models.LoginModel;
import com.example.phase2.usermanagementactivities.presenters.LoginPresenter;
import com.example.phase2.usermanagementactivities.views.LoginView;

import java.util.HashMap;

/**
 * A login activity.
 */

public class LoginActivity extends SuperActivity implements View.OnClickListener, Initializable, LoginView {

    private LoginPresenter loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    @Override
    public void onClick(View v) {
        final EditText usernameEditText = findViewById(R.id.username);
        final EditText passwordEditText = findViewById(R.id.password);
        String username = usernameEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        switch (v.getId()) {
            case R.id.login: {
                if(loginPresenter.showResult(fileSystem, username, password))
                    startActivity(new Intent(LoginActivity.this, ChooseOrCreatePlayerActivity.class));
                break;
            }
            case R.id.register: {
                loginPresenter.register(fileSystem);
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                break;
            }
        }
    }

    @Override
    public void init() {
        super.init();
        this.loginPresenter = new LoginPresenter(new LoginModel(), this);
        setContentView(R.layout.activity_login);

        //Button initiation reference: https://www.youtube.com/watch?v=GtxVILjLcw8
        final Button loginButton = findViewById(R.id.login);
        final Button registerButton = findViewById(R.id.register);

        //OnClickListener setup
        loginButton.setOnClickListener(this);
        registerButton.setOnClickListener(this);
    }

    @Override
    public void setLoginResult(String result) {
        Toast.makeText(this, result, Toast.LENGTH_LONG).show();
    }
}
