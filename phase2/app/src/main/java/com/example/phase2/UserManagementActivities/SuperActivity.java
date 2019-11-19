package com.example.phase2.UserManagementActivities;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.example.phase2.AppCoreClasses.GameApp;
import com.example.phase2.AppCoreClasses.UserManager;
import com.example.phase2.DataManagement.FileSystem;
import com.example.phase2.Initializable;
import com.example.phase2.R;

public class SuperActivity extends AppCompatActivity implements Initializable {
    FileSystem fileSystem;
    GameApp app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void init() {
        app = (GameApp) getApplication();
        if(app.getColorTheme().equals("blue")){
            setTheme(R.style.blue);
        }
        else if(app.getColorTheme().equals("yellow")){
            setTheme(R.style.yellow);
        }
        fileSystem = new FileSystem(this.getApplicationContext());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        fileSystem.save(UserManager.getInstance().getUsers(), "Users.ser");
    }

    @Override
    protected void onStop() {
        super.onStop();
        fileSystem.save(UserManager.getInstance().getUsers(), "Users.ser");
    }
}
