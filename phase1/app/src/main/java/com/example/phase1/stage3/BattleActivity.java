package com.example.phase1.stage3;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.phase1.*;

import com.example.phase1.R;

import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Timer;


public class BattleActivity extends AppCompatActivity implements View.OnClickListener {

    User curUser;

    // private BattleView gameView;
    private boolean playerTurn;
    private boolean bossTurn;
    private Button checkBtn;
    private Button attackBtn;
    private Button defenceBtn;
    private Button evadeBtn;

    private boolean p_move = false;

    private int roundNum = 1;

    private String player_move;

    private TextView lifeview;
    private TextView monsterLifeview;
    private TextView monsterMove;
    private TextView roundview;
    private TextView attackview;
    private TextView defenceview;
    private TextView flexibilityview;
    private TextView luckinessview;


    private Timer timer = new Timer();
    private Handler handler = new Handler();


    Property monster_property = new Property(5, 5, 5, 5);
    Monster monster = new Monster(200, monster_property);
    Property player_property = new Property(2, 2, 2, 2);
    //        curUser = UserManager.getInstance().getCurUser();
//        Player player = curUser.getCurPlayer();
    Player player = new Player("player1", player_property);


//    public void startThread(View view){
//
//    }
//
//
//
//    class MyRunnable implements Runnable{
//        @Override
//        public void run() {
//
//            }
//        }


    @Override
    public void onCreate(Bundle savedInstanceState) {
//        while (player.getLivesRemain() > 0 && monster.getLivesRemain() > 0) {
//            roundNum ++;
//            Round round = new Round(player, monster);
//            round.battle1();
//            String move = round.getMonsterString();
//            updatemonsterMove(move);
//            round.battle2(move); // player's move choose
//            int decreaseM = round.getDamage1();
//            int decreaseP = round.getDamage2();
//            player.loseLives(decreaseP);
//            monster.loseLives(decreaseM);
//        }

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

        attackview.setText("Attack:" + player.getProperty().getAttack());
        defenceview.setText("Defence:" + player.getProperty().getDefence());
        flexibilityview.setText("Flexibility:" + player.getProperty().getFlexibility());
        luckinessview.setText("Luckiness:" + player.getProperty().getLuckiness());
        lifeview.setText("Life:" + player.getLivesRemain());
        monsterLifeview.setText("Monster Life:" + monster.getLivesRemain());
        roundview.setText("Round Number:" + roundNum);


//        while (player.getLivesRemain() > 0 && monster.getLivesRemain() > 0) {
//            roundNum ++;
//            Round round = new Round(player, monster);
//            round.battle1();
//            String move = round.getMonsterString();
//            updatemonsterMove(move);
//            round.battle2(player_move); // player's move choose
//            int decreaseM = round.getDamage1();
//            int decreaseP = round.getDamage2();
//            player.loseLives(decreaseP);
//            monster.loseLives(decreaseM);
//            update();
//        }

    }


//    @Override
//    protected void onStart() {
//        super.onStart();
//        while (player.getLivesRemain() > 0 && monster.getLivesRemain() > 0) {
//            roundNum ++;
//            Round round = new Round(player, monster);
//            round.battle1();
//            String move = round.getMonsterString();
//            updatemonsterMove(move);
//            round.battle2(move); // player's move choose
//            int decreaseM = round.getDamage1();
//            int decreaseP = round.getDamage2();
//            player.loseLives(decreaseP);
//            monster.loseLives(decreaseM);
//        }
//    }


//    public void playerMove(){
//        if(p_attack) {
//            player_move = "Attack";
//        }
//        if(p_defence) {
//            player_move = "Defence";
//        }
//        if(p_evade) {
//            player_move = "Evade";
//        }
//    }


    @Override
    public void onClick(View v) {
//        if(!((Button) v).getText().toString().equals("")){
//            return;
//        }

//        if (playerTurn){            //do not know what playerTurn is
        Round round = new Round(player, monster);
        switch (v.getId()) {
            case R.id.attackBtn:
                if (p_move) {
                    player_move = "Attack";
                    round.battle2(player_move);
                    int decreaseM = round.getDamage1();
                    int decreaseP = round.getDamage2();
                    player.loseLives(decreaseP);
                    monster.loseLives(decreaseM);
//                    player.loseLives(10);
//                    monster.loseLives(20);
                    update();
                    p_move = false;
                }
                break;
            case R.id.defenceBtn:
                if (p_move) {
                    player_move = "Defence";
                    round.battle2(player_move);
                    int decreaseM = round.getDamage1();
                    int decreaseP = round.getDamage2();
                    player.loseLives(decreaseP);
                    monster.loseLives(decreaseM);
                    update();
                    p_move = false;
                }
                break;
            case R.id.evadeBtn:
                if (p_move) {
                    player_move = "Evade";
                    round.battle2(player_move);
                    int decreaseM = round.getDamage1();
                    int decreaseP = round.getDamage2();
                    player.loseLives(decreaseP);
                    monster.loseLives(decreaseM);
                    update();
                    p_move = false;
                }

                    if (checklife(monster, player) == 1){
                        //playerlose
                        startActivity(new Intent(BattleActivity.this, LoseActivity.class));
                    }
                    if (checklife(monster, player) == 2){
                        //playerwin
                        startActivity(new Intent(BattleActivity.this, WinActivity.class));
                    }
                break;
            case R.id.checkBtn:
                if (p_move) {
                    break;
                } else {
                    p_move = true;
//                    Round round = new Round(player, monster);
                    round.battle1();
                    String move = round.getMonsterString();
                    monsterMove.setText(move);
                    break;
                }

            default:
                break;


//                    if (player.getLivesRemain() > 0 && monster.getLivesRemain() > 0) {
//                        roundNum ++;
//                        Round round = new Round(player, monster);
//                        round.battle1();
//                        String move = round.getMonsterString();
//                        monsterMove.setText(move);
////                        updatemonsterMove(move);
//                        round.battle2(player_move); // player's move choose
//                        int decreaseM = round.getDamage1();
//                        int decreaseP = round.getDamage2();
//                        player.loseLives(decreaseP);
//                        monster.loseLives(decreaseM);
//                        update();
//                        break;
////                    }
//                        }
        }

    }


    private int checklife(Monster monster, Player player) {
        // return 0 if both life greater than 0, return 1 if player lose, return 2 if monster lose
        if (monster.getLivesRemain() > 0 && player.getLivesRemain() > 0) {
            return 0;
        } else if (player.getLivesRemain() <= 0) {
            return 1;
        } else {
            return 2;
        }
    }

    private void update() {
        roundNum++;
        roundview.setText("Round Number:" + roundNum);
        attackview.setText("Attack:" + player.getProperty().getAttack());
        defenceview.setText("Defence:" + player.getProperty().getDefence());
        flexibilityview.setText("Flexibility:" + player.getProperty().getFlexibility());
        luckinessview.setText("Luckiness:" + player.getProperty().getLuckiness());
        lifeview.setText("Life:" + player.getLivesRemain());
        monsterLifeview.setText("Monster Life:" + monster.getLivesRemain());
    }

//    private void updateRound(){
//        roundview.setText("Round Number:" + roundNum);
//    }
//
//    private void updatemonsterMove(String move){
//        monsterMove.setText(move);  //get monstermove
//    }
//
//    private void updateAttack(){
//        attackview.setText("Attack:" + player.getProperty().getAttack());
//    }
//
//    private void updateDefence(){
//        defenceview.setText("Defence:" + player.getProperty().getDefence());
//    }
//
//    private void updateFlexibility(){
//        flexibilityview.setText("Flexibility:" + player.getProperty().getFlexibility());
//    }
//
//    private void updateLuckiness(){
//        luckinessview.setText("Luckiness:" + player.getProperty().getLuckiness());
//    }
//
//    private void updateLife(){
//        lifeview.setText("Life:" + player.getLivesRemain());
//    }
//
//    private void updateMonsterLife(){
//        monsterLifeview.setText("Monster Life:" + monster.getLivesRemain());
//    }


}