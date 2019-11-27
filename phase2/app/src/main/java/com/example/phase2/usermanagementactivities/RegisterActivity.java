package com.example.phase2.usermanagementactivities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.phase2.Initializable;
import com.example.phase2.R;
import com.example.phase2.appcore.User;
import com.example.phase2.appcore.UserManager;
import com.example.phase2.usermanagementactivities.models.RegisterModel;
import com.example.phase2.usermanagementactivities.presenters.RegisterPresenter;
import com.example.phase2.usermanagementactivities.views.SetStringView;

/** A register activity. */
public class RegisterActivity extends SuperActivity implements View.OnClickListener, Initializable, SetStringView {

    private RegisterPresenter registerPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    @Override
    public void onClick(View v) {
        TextView usernameTextView = findViewById(R.id.username);
        TextView password1TextView = findViewById(R.id.password1);
        TextView password2TextView = findViewById(R.id.password2);

        String username = usernameTextView.getText().toString();
        String password1 = password1TextView.getText().toString();;
        String password2 = password2TextView.getText().toString();

        switch (v.getId()) {
            case R.id.register: {
                if (registerPresenter.showResult(fileSystem, username, password1, password2)) {
                    startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                }
                break;
            }
            case R.id.back: {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                break;
            }
        }
    }

    @Override
    public void init() {
        super.init();
        setContentView(R.layout.activity_register);
        this.registerPresenter = new RegisterPresenter(new RegisterModel(), this);

        //Button initiation reference: https://www.youtube.com/watch?v=GtxVILjLcw8
        final Button registerButton = findViewById(R.id.register);
        final Button backButton = findViewById(R.id.back);

        //OnclickListener setup
        registerButton.setOnClickListener(this);
        backButton.setOnClickListener(this);
    }

    @Override
    public void setResult(String result) {
        Toast.makeText(this, result, Toast.LENGTH_LONG).show();
    }
}
