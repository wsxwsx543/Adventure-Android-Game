package com.example.phase2.stage2;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class TreasureHuntActivity extends AppCompatActivity {
    private TreasureHuntView treasureHuntView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Initiate the view that this activity is using
        treasureHuntView = new TreasureHuntView(this);
        setContentView(treasureHuntView);
    }

    @Override
    protected void onStop() {
        super.onStop();
        treasureHuntView.saveUser();
    }
    protected void onDestroy(){
        super.onDestroy();
        treasureHuntView.saveUser();
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
