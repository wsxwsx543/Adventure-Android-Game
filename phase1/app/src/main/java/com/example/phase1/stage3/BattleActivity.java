package com.example.phase1.stage3;


import androidx.appcompat.app.AppCompatActivity;

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
    private boolean p_check;
    private Button attackBtn;
    private boolean p_attack;
    private Button defenceBtn;
    private boolean p_defence;
    private Button evadeBtn;
    private boolean p_evade;

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



//    while (player.getLivesRemain() > 0 && monster.getLivesRemain() > 0) {
//        roundNum ++;
//        Round round = new Round(player, monster);
//        round.battle1();
//        String move = round.getMonsterString();
//        updatemonsterMove(move);
//        round.battle2(move); // player's move choose
//        int decreaseM = round.getDamage1();
//        int decreaseP = round.getDamage2();
//        player.loseLives(decreaseP);
//        monster.loseLives(decreaseM);
//    }



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
            switch (v.getId()){
                case R.id.attackBtn:
                    p_attack = true;
                    player_move = "Attack";
                    break;
                case R.id.defenceBtn:
                    p_defence = true;
                    player_move = "Defence";
                    break;
                case R.id.evadeBtn:
                    p_evade = true;
                    player_move = "Evade";
                    break;
                case R.id.checkBtn:
                    p_check = true;
                    Round round = new Round(player, monster);
                    round.battle1();
                    String move = round.getMonsterString();
//                    String move = "why why why why";
                    monsterMove.setText(move);
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

    private void update(){
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
    private void updatemonsterMove(String move){
        monsterMove.setText(move);  //get monstermove
    }
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