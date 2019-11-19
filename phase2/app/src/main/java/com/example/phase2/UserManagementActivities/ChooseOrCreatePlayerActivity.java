package com.example.phase2.UserManagementActivities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.phase2.DataManagement.FileSystem;
import com.example.phase2.Initializable;
import com.example.phase2.AppCoreClasses.GameApp;
import com.example.phase2.R;
import com.example.phase2.AppCoreClasses.UserManager;

/** An activity used to choose a player or create a new player. */
public class ChooseOrCreatePlayerActivity extends SuperActivity implements View.OnClickListener, Initializable {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.select:
                startActivity(new Intent(ChooseOrCreatePlayerActivity.this, SelectPlayerActivity.class));
                break;
            case R.id.create:
                startActivity(new Intent(ChooseOrCreatePlayerActivity.this, CreatePlayerActivity.class));
                break;
            case R.id.logout:
                UserManager.getInstance().setCurUser(null);
                startActivity(new Intent(ChooseOrCreatePlayerActivity.this, LoginActivity.class));
                break;
            case R.id.settings:
                startActivity(new Intent(ChooseOrCreatePlayerActivity.this, SettingActivity.class));
                break;
        }
    }

    @Override
    public void init() {
        super.init();
        setContentView(R.layout.activity_choose_or_create_player);

        final Button selectButton = findViewById(R.id.select);
        final Button createButton = findViewById(R.id.create);
        final Button logoutButton = findViewById(R.id.logout);
        final Button settingButton = findViewById(R.id.settings);

        selectButton.setOnClickListener(this);
        createButton.setOnClickListener(this);
        logoutButton.setOnClickListener(this);
        settingButton.setOnClickListener(this);
    }
}
