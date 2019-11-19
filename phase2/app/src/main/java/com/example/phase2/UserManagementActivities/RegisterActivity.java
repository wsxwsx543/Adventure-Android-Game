package com.example.phase2.UserManagementActivities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.phase2.DataManagement.FileSystem;
import com.example.phase2.Initializable;
import com.example.phase2.AppCoreClasses.GameApp;
import com.example.phase2.R;
import com.example.phase2.AppCoreClasses.User;
import com.example.phase2.AppCoreClasses.UserManager;

/** A register activity. */
public class RegisterActivity extends SuperActivity implements View.OnClickListener, Initializable {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    /**
     * Return whether a account is created successfully or not.
     * @return a boolean value.
     */
    public boolean addNewUser() {
        UserManager userManager = UserManager.getInstance();

        TextView usernameTextView = findViewById(R.id.username);
        TextView password1TextView = findViewById(R.id.password1);
        TextView password2TextView = findViewById(R.id.password2);

        String username = usernameTextView.getText().toString();
        String password1 = password1TextView.getText().toString();;
        String password2 = password2TextView.getText().toString();

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
        switch (v.getId()) {
            case R.id.register: {
                if (addNewUser()) {
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

        //Button initiation reference: https://www.youtube.com/watch?v=GtxVILjLcw8
        final Button registerButton = findViewById(R.id.register);
        final Button backButton = findViewById(R.id.back);

        //OnclickListener setup
        registerButton.setOnClickListener(this);
        backButton.setOnClickListener(this);
    }
}
