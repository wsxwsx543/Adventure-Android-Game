package com.example.phase1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{
    FileSystem fileSystem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        fileSystem = new FileSystem(this.getApplicationContext());

        //Button initiation reference: https://www.youtube.com/watch?v=GtxVILjLcw8
        final Button registerButton = findViewById(R.id.register);
        final Button backButton = findViewById(R.id.back);

        //OnclickListener setup
        registerButton.setOnClickListener(this);
        backButton.setOnClickListener(this);

    }

    public boolean addNewUser() {
        UserManager userManager = UserManager.getInstance();

        TextView usernameTextView = findViewById(R.id.username);
        TextView password1TextView = findViewById(R.id.password1);
        TextView password2TextView = findViewById(R.id.password2);

        String username = usernameTextView.toString();
        String password1 = password1TextView.toString();
        ;
        String password2 = password2TextView.toString();

        if (username.equals("") || password1.equals("") || password2.equals("")) {
            Toast.makeText(this, "Username and password cannot be empty.", Toast.LENGTH_LONG).show();
            return false;
        } else if(userManager.getUsers().containsKey(username)){
            Toast.makeText(this,
                    "Please change a username, this username has been token.", Toast.LENGTH_LONG).show();
            return false;
        } else {
            if (password1.equals(password2)) {
                userManager.addUser(new User(username, password1));
                fileSystem.save(userManager.getUsers(), "Users.ser");
                Toast.makeText(this, "Create new account successfully.", Toast.LENGTH_LONG).show();
                return true;
            } else {
                Toast.makeText(this, "Password entered are not same.",
                        Toast.LENGTH_LONG).show();
                return false;
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.register:
                if(addNewUser()){
                    startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                }
                break;
            case R.id.back:
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                break;
        }
    }
}
