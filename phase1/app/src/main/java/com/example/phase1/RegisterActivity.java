package com.example.phase1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{

    UserManager userManager = new UserManager();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //Button initiation reference: https://www.youtube.com/watch?v=GtxVILjLcw8
        final Button registerButton = findViewById(R.id.register);
        final Button backButton = findViewById(R.id.back);

        //OnclickListener setup
        registerButton.setOnClickListener(this);
        backButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.register:
                System.out.println("register pressed");
                userManager.addUser(new User(5));
                break;
            case R.id.back:
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                break;
        }
    }
}
