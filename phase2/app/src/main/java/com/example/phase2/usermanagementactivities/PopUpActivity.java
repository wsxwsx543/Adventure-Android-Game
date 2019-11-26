package com.example.phase2.usermanagementactivities;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;

import com.example.phase2.appcore.UserManager;
import com.example.phase2.R;

public class PopUpActivity extends SuperActivity implements View.OnClickListener{
    Button yesBtn;
    Button noBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop_up);
        init();
    }

    @Override
    public void init(){
        super.init();
        yesBtn = findViewById(R.id.yesBtn);
        yesBtn.setOnClickListener(this);
        noBtn = findViewById(R.id.noBtn);
        noBtn.setOnClickListener(this);

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
                UserManager.getInstance().getCurUser().getCurPlayer().setWin(true);
                break;
            case R.id.noBtn:
                UserManager.getInstance().getCurUser().getCurPlayer().setWin(false);
                break;
        }
        startActivity(new Intent(PopUpActivity.this, ChooseOrCreatePlayerActivity.class));
    }

}


