package com.example.phase2.stage2;

import android.os.Bundle;

import com.example.phase2.usersystem.SuperActivity;


public class TreasureHuntActivity extends SuperActivity {
    private TreasureHuntView treasureHuntView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.init();
        // Initiate the view that this activity is using
        treasureHuntView = new TreasureHuntView(this);
        setContentView(treasureHuntView);
    }

    protected void onPause(){
        super.onPause();
        treasureHuntView.pause();
    }
    protected void onResume(){
        super.onResume();
        treasureHuntView.resume();
    }

}
