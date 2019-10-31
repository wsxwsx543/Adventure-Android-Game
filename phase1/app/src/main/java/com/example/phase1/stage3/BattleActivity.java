package com.example.phase1.stage3;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.phase1.*;

import com.example.phase1.R;

import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Timer;


public class BattleActivity extends AppCompatActivity implements View.OnClickListener {

    private User curUser;
    private Player player;
    private Monster monster;
    private FileSystem fileSystem;

    private Button checkBtn;
    private Button attackBtn;
    private Button defenceBtn;
    private Button evadeBtn;

    private boolean p_move = false;

    private int roundNum = 1;

    private String player_move;
    private Property monsterP;

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



    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battle);

        Property monster_property = new Property(10, 10, 0, 0);
        monster = new Monster(200, monster_property);
        curUser = UserManager.getInstance().getCurUser();
        player = curUser.getCurPlayer();
        fileSystem = new FileSystem(this.getApplicationContext());


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
    }


    @Override
    public void onClick(View v) {
        Round round = new Round(player, monster);
        switch (v.getId()) {
            case R.id.attackBtn:
                if (p_move) {
                    player_move = "Attack";
                    round.battle2(player_move, monsterP);
                    int decreaseM = round.getDamage1();
                    int decreaseP = round.getDamage2();
                    player.loseLives(decreaseP);
                    monster.loseLives(decreaseM);
                    update();
                    p_move = false;
                }
                winOrlose(checklife(monster, player));
                break;
            case R.id.defenceBtn:
                if (p_move) {
                    player_move = "Defence";
                    round.battle2(player_move, monsterP);
                    int decreaseM = round.getDamage1();
                    int decreaseP = round.getDamage2();
                    player.loseLives(decreaseP);
                    monster.loseLives(decreaseM);
                    update();
                    p_move = false;
                }
                winOrlose(checklife(monster, player));
                break;
            case R.id.evadeBtn:
                if (p_move) {
                    player_move = "Evade";
                    round.battle2(player_move, monsterP);
                    int decreaseM = round.getDamage1();
                    int decreaseP = round.getDamage2();
                    player.loseLives(decreaseP);
                    monster.loseLives(decreaseM);
                    update();
                    p_move = false;
                }
                winOrlose(checklife(monster, player));
                break;
            case R.id.checkBtn:
                if (p_move) {
                    break;
                } else {
                    p_move = true;
//                    Round round = new Round(player, monster);
                    monsterP = round.battle1();
                    String move = round.getMonsterString();
                    monsterMove.setText(move);
                    break;
                }

            default:
                break;
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

    private void winOrlose(int num){
        if (num == 1){
            //playerlose
            startActivity(new Intent(BattleActivity.this, LoseActivity.class));
            player.setCurStage(3);
            fileSystem.save(UserManager.getInstance().getUsers(), "Users.ser");
        }
        if (num == 2){
            //playerwin
            startActivity(new Intent(BattleActivity.this, WinActivity.class));
            player.setCurStage(3);
            fileSystem.save(UserManager.getInstance().getUsers(), "Users.ser");
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

}