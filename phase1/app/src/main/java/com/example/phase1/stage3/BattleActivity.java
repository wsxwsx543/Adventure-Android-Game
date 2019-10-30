package com.example.phase1.stage3;


import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.phase1.*;
import com.example.phase1.R;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class BattleActivity extends AppCompatActivity implements View.OnClickListener {

    User curUser;

    // private BattleView gameView;
    private boolean playerTurn;
    private boolean bossTurn;
    private int roundNum = 1;
    private Button checkBtn;
    private Button attackBtn;
    private Button defenceBtn;
    private Button evadeBtn;

    private String player_move;

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

    Property monster_property = new Property(5, 5, 5, 5);
    Monster monster = new Monster(200, monster_property);
    Property player_property = new Property(2, 2, 2, 2);
    //        curUser = UserManager.getInstance().getCurUser();
//        Player player = curUser.getCurPlayer();
    Player player = new Player("player1", player_property);



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

        while (player.getLivesRemain() > 0 && monster.getLivesRemain() > 0) {
            roundNum ++;
            Round round = new Round(player, monster);
            round.battle1();
            String move = round.getMonsterString();
            updatemonsterMove(move);
            round.battle2(move); // player's move choose
            int decreaseM = round.getDamage1();
            int decreaseP = round.getDamage2();
            player.loseLives(decreaseP);
            monster.loseLives(decreaseM);
        }

//        Property monster_property = new Property(5, 5, 5, 5);
//        Monster monster = new Monster(200, monster_property);
//        Property player_property = new Property(2, 2, 2, 2);
//        curUser = UserManager.getInstance().getCurUser();
//        Player player = curUser.getCurPlayer();
//        Player player = new Player("player1", player_property);

    }


    @Override
    public void onClick(View v) {
        if(!((Button) v).getText().toString().equals("")){
            return;
        }
        if (playerTurn){            //do not know what playerTurn is

        }

        if(bossTurn){

        }
    }

    private void updateRound(){
        roundview.setText("Round Number:" + roundNum);
    }

    private void updatemonsterMove(String move){
        monsterMove.setText(move);  //get monstermove
    }

    private void updateAttack(){
        attackview.setText("Attack:" + player.getProperty().getAttack());
    }

    private void updateDefence(){
        defenceview.setText("Defence:" + player.getProperty().getDefence());
    }

    private void updateFlexibility(){
        flexibilityview.setText("Flexibility:" + player.getProperty().getFlexibility());
    }

    private void updateLuckiness(){
        luckinessview.setText("Luckiness:" + player.getProperty().getLuckiness());
    }

    private void updateLife(){
        lifeview.setText("Life:" + player.getLivesRemain());
    }

    private void updateMonsterLife(){
        monsterLifeview.setText("Monster Life:" + monster.getLivesRemain());
    }


}