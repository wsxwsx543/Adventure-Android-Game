package com.example.phase2.stage3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;

import com.example.phase2.AppCoreClasses.GameApp;
import com.example.phase2.AppCoreClasses.UserManager;
import com.example.phase2.DataManagement.FileSystem;
import com.example.phase2.R;
import com.example.phase2.ScoreBoard.ScoreBoard;
import com.example.phase2.UserManagementActivities.ChooseOrCreatePlayerActivity;

public class PopUpActivity extends AppCompatActivity implements View.OnClickListener {
    FileSystem fileSystem;
    GameApp app;
    Button yesBtn;
    Button noBtn;
    private boolean save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        app = (GameApp) getApplication();
        setContentView(R.layout.activity_pop_up);

        fileSystem = new FileSystem(this.getApplicationContext());
        yesBtn = findViewById(R.id.yesBtn);
        yesBtn.setOnClickListener(this);
        noBtn = findViewById(R.id.noBtn);
        noBtn.setOnClickListener(this);

        if(app.getColorTheme().equals("blue")){
            setTheme(R.style.blue);
        }
        else if(app.getColorTheme().equals("yellow")){
            setTheme(R.style.yellow);
        }

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        getWindow().setLayout((int)(width * 0.9), (int)(height * 0.3));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.yesBtn:
                save = true;
                break;
            case R.id.noBtn:
                save = false;
                break;
        }
//        if (save) {
//            // choose to store this record
//        }
//        else{
//            // choose not to store this record
//        }
        startActivity(new Intent(PopUpActivity.this, ChooseOrCreatePlayerActivity.class));
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        fileSystem.save(UserManager.getInstance().getUsers(), "Users.ser");
        fileSystem.save(ScoreBoard.getInstance(), "ScoreBoard.ser");
    }

    @Override
    protected void onStop() {
        super.onStop();
        fileSystem.save(UserManager.getInstance().getUsers(), "Users.ser");
        fileSystem.save(ScoreBoard.getInstance(), "ScoreBoard.ser");
    }

}


