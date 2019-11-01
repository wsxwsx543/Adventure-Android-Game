package com.example.phase1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class ChooseOrCreatePlayerActivity extends AppCompatActivity implements View.OnClickListener{
    FileSystem fileSystem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_or_create_player);

        final Button selectButton = findViewById(R.id.select);
        final Button createButton = findViewById(R.id.create);
        final Button logoutButton = findViewById(R.id.logout);

        selectButton.setOnClickListener(this);
        createButton.setOnClickListener(this);
        logoutButton.setOnClickListener(this);

        fileSystem = new FileSystem(this.getApplicationContext());
        // System.out.println(UserManager.getInstance().getCurUser().getUsername());
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
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        fileSystem.save(UserManager.getInstance(), "Users.ser");
    }

    @Override
    protected void onStop() {
        super.onStop();
        fileSystem.save(UserManager.getInstance(), "Users.ser");
    }
}
