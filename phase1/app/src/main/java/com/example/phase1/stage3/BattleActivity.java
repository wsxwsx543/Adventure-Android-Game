package com.example.phase1.stage3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.phase1.R;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class BattleActivity extends AppCompatActivity implements View.OnClickListener {

    // private BattleView gameView;
    private boolean playerTurn;
    private boolean bossTurn;
    private int roundNum;
    private Button checkBtn;
    private Button attackBtn;
    private Button defenceBtn;
    private Button evadeBtn;

    private int life;
    private int monsterLife;
    private int attack;
    private int defence;
    private int flexibility;
    private int luckiness;

    private TextView lifeview;
    private TextView monsterLifeview;
    private TextView monsterMove;
    private TextView roundview;
    private TextView attackview;
    private TextView defenceview;
    private TextView flexibilityview;
    private TextView luckinessview;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battle);
        // gameView = new BattleView(this);
        // setContentView(gameView);

        lifeview = findViewById(R.id.life);
        monsterLifeview = findViewById(R.id.monster_life);
        monsterMove = findViewById(R.id.monster_status);
        roundview = findViewById(R.id.round_num);
        attackview = findViewById(R.id.your_attack);
        defenceview = findViewById(R.id.your_defence);
        flexibilityview = findViewById(R.id.your_flexibility);
        luckinessview = findViewById(R.id.your_luckiness);

        checkBtn = findViewById(R.id.checkBtn);
        checkBtn.setOnClickListener(this);
        attackBtn = findViewById(R.id.attackBtn);
        attackBtn.setOnClickListener(this);
        defenceBtn = findViewById(R.id.defenceBtn);
        defenceBtn.setOnClickListener(this);
        evadeBtn = findViewById(R.id.evadeBtn);
        evadeBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

    }
}