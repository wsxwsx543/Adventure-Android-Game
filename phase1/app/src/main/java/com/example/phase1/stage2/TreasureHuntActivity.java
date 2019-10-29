package com.example.phase1.stage2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

public class TreasureHuntActivity extends AppCompatActivity {
    private TreasureHuntView treasureHuntView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
