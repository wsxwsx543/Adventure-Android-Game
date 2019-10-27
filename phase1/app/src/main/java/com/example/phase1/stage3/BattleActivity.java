package com.example.phase1.stage3;

import android.app.Activity;
// import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;

import com.example.phase1.R;

import androidx.appcompat.app.AppCompatActivity;

public class BattleActivity extends AppCompatActivity {

    // private BattleView gameView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battle);
        // gameView = new BattleView(this);
        // setContentView(gameView);
    }
}