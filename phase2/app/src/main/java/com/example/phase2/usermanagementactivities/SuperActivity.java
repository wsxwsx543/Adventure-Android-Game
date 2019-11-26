package com.example.phase2.usermanagementactivities;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.example.phase2.appcore.GameApp;
import com.example.phase2.appcore.UserManager;
import com.example.phase2.datamanagement.FileSystem;
import com.example.phase2.Initializable;
import com.example.phase2.R;
import com.example.phase2.scoreboard.ScoreBoard;

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
        else if(app.getColorTheme().equals("pink")){
            setTheme(R.style.pink);
        }
        else if(app.getColorTheme().equals("green")){
            setTheme(R.style.green);
        }
        fileSystem = new FileSystem(this.getApplicationContext());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        fileSystem.save(UserManager.getInstance().getUsers(), "Users.ser");
        fileSystem.save(ScoreBoard.getInstance().getUserPlayers(), "ScoreBoard.ser");
    }

    @Override
    protected void onStop() {
        super.onStop();
        fileSystem.save(UserManager.getInstance().getUsers(), "Users.ser");
        fileSystem.save(ScoreBoard.getInstance().getUserPlayers(), "ScoreBoard.ser");
    }
}