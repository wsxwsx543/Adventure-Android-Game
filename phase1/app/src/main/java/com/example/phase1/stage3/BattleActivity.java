package com.example.phase1.stage3;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.phase1.*;

import com.example.phase1.R;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;


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

    private TextView lifeView;
    private TextView monsterLifeView;
    private TextView monsterMove;
    private TextView roundView;
    private TextView attackView;
    private TextView defenceView;
    private TextView flexibilityView;
    private TextView luckinessView;



    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battle);

        Property monster_property = new Property(10, 10, 0, 0);
        monster = new Monster(200, monster_property);
        curUser = UserManager.getInstance().getCurUser();
        player = curUser.getCurPlayer();
        fileSystem = new FileSystem(this.getApplicationContext());


        lifeView = findViewById(R.id.life);
        monsterLifeView = findViewById(R.id.monster_life);
        monsterMove = findViewById(R.id.monster_status);
        roundView = findViewById(R.id.round_num);
        attackView = findViewById(R.id.your_attack);
        defenceView = findViewById(R.id.your_defence);
        flexibilityView = findViewById(R.id.your_flexibility);
        luckinessView = findViewById(R.id.your_luckiness);

        checkBtn = findViewById(R.id.checkBtn);
        checkBtn.setOnClickListener(this);
        attackBtn = findViewById(R.id.attackBtn);
        attackBtn.setOnClickListener(this);
        defenceBtn = findViewById(R.id.defenceBtn);
        defenceBtn.setOnClickListener(this);
        evadeBtn = findViewById(R.id.evadeBtn);
        evadeBtn.setOnClickListener(this);

        attackView.setText("Attack:" + player.getProperty().getAttack());
        defenceView.setText("Defence:" + player.getProperty().getDefence());
        flexibilityView.setText("Flexibility:" + player.getProperty().getFlexibility());
        luckinessView.setText("Luckiness:" + player.getProperty().getLuckiness());
        lifeView.setText("Life:" + player.getLivesRemain());
        monsterLifeView.setText("Monster Life:" + monster.getLivesRemain());
        roundView.setText("Round Number:" + roundNum);
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
                winOrLose(checkLife(monster, player));
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
                winOrLose(checkLife(monster, player));
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
                winOrLose(checkLife(monster, player));
                break;
            case R.id.checkBtn:
                if (p_move) {
                    break;
                } else {
                    p_move = true;
                    monsterP = round.battle1();
                    String move = round.getMonsterString();
                    monsterMove.setText(move);
                    break;
                }

            default:
                break;
        }

    }


    private int checkLife(Monster monster, Player player) {
        if (monster.getLivesRemain() > 0 && player.getLivesRemain() > 0) {
            return 0;
        } else if (player.getLivesRemain() <= 0) {
            return 1;
        } else {
            return 2;
        }
    }

    private void winOrLose(int num){
        if (num == 1){
            //player lose
            startActivity(new Intent(BattleActivity.this, LoseActivity.class));
            player.setCurStage(3);
            fileSystem.save(UserManager.getInstance().getUsers(), "Users.ser");
        }
        if (num == 2){
            //player win
            startActivity(new Intent(BattleActivity.this, WinActivity.class));
            player.setCurStage(3);
            fileSystem.save(UserManager.getInstance().getUsers(), "Users.ser");
        }
    }

    private void update() {
        roundNum++;
        roundView.setText("Round Number:" + roundNum);
        attackView.setText("Attack:" + player.getProperty().getAttack());
        defenceView.setText("Defence:" + player.getProperty().getDefence());
        flexibilityView.setText("Flexibility:" + player.getProperty().getFlexibility());
        luckinessView.setText("Luckiness:" + player.getProperty().getLuckiness());
        lifeView.setText("Life:" + player.getLivesRemain());
        monsterLifeView.setText("Monster Life:" + monster.getLivesRemain());
    }

}