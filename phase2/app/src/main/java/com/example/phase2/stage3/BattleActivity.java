package com.example.phase2.stage3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.phase2.datamanagement.FileSystem;
import com.example.phase2.appcore.scoreboard.ScoreBoard;
import com.example.phase2.usersystem.views.results.LoseActivity;
import com.example.phase2.appcore.game.Player;
import com.example.phase2.appcore.game.Property;
import com.example.phase2.R;
import com.example.phase2.appcore.user.User;
import com.example.phase2.appcore.user.UserManager;
import com.example.phase2.usersystem.views.app.SuperActivity;
import com.example.phase2.usersystem.views.results.WinActivity;


/** An activity shows the battle of monster and player for stage 3. */
public class BattleActivity extends SuperActivity implements View.OnClickListener {

    /** The current user. */
    User curUser;
    /** The current player and current monster. */
    private Player player;
    private Monster monster;
    /** The file system. */
    private FileSystem fileSystem;
    /** The four buttons. Where check button check monster's move. */
    Button checkBtn;
    Button attackBtn;
    Button defenceBtn;
    Button evadeBtn;
    /** The boolean value decide whether is the round that player could move. */
    private boolean p_move = false;
    /** The current round number of the battle game. */
    private int roundNum = 1;
    /** The move that player choose. */
    String player_move;
    /** Monster's property. */
    private Property monsterProperty;
    /** The text views. */
    private TextView monsterMove;
    private TextView lifeView;
    private TextView monsterLifeView;
    private TextView roundView;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.init();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battle);

        Property monster_property = new Property(10, 10, 0, 0);
        monster = new Monster(300, monster_property);
        curUser = UserManager.getInstance().getCurUser();
        player = curUser.getCurPlayer();
        fileSystem = new FileSystem(this.getApplicationContext());
        player.setCurStage(3);
        fileSystem.save(UserManager.getInstance().getUsers(), "Users.ser");

        monsterMove = findViewById(R.id.monster_status);
        lifeView = findViewById(R.id.life);
        monsterLifeView = findViewById(R.id.monster_life);
        roundView = findViewById(R.id.round_num);
        TextView attackView = findViewById(R.id.your_attack);
        TextView defenceView = findViewById(R.id.your_defence);
        TextView flexibilityView = findViewById(R.id.your_flexibility);
        TextView luckinessView = findViewById(R.id.your_luckiness);

        checkBtn = findViewById(R.id.checkBtn);
        checkBtn.setOnClickListener(this);
        attackBtn = findViewById(R.id.attackBtn);
        attackBtn.setOnClickListener(this);
        defenceBtn = findViewById(R.id.defenceBtn);
        defenceBtn.setOnClickListener(this);
        evadeBtn = findViewById(R.id.evadeBtn);
        evadeBtn.setOnClickListener(this);

        String atk = ("Attack:" + player.getProperty().getAttack());
        String def = ("Defence:" + player.getProperty().getDefence());
        String fle = ("Flexibility:" + player.getProperty().getFlexibility());
        String luc = ("Luckiness:" + player.getProperty().getLuckiness());
        String lif = ("Life:" + player.getLivesRemain());
        String mon_lif = ("Monster Life:" + monster.getLivesRemain());
        String rn = ("Round Number:" + roundNum);
        attackView.setText(atk);
        defenceView.setText(def);
        flexibilityView.setText(fle);
        luckinessView.setText(luc);
        lifeView.setText(lif);
        monsterLifeView.setText(mon_lif);
        roundView.setText(rn);
    }


    @Override
    public void onClick(View v) {
        Round round = new Round(player, monster);
        switch (v.getId()) {
            case R.id.attackBtn:
                if (p_move) {
                    player_move = "Attack";
                    battle(player_move, round);
                    update();
                    p_move = false;
                }
                winOrLose(checkLife(monster, player));
                break;
            case R.id.defenceBtn:
                if (p_move) {
                    player_move = "Defence";
                    battle(player_move, round);
                    update();
                    p_move = false;
                }
                winOrLose(checkLife(monster, player));
                break;
            case R.id.evadeBtn:
                if (p_move) {
                    player_move = "Evade";
                    battle(player_move, round);
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
                    monsterProperty = round.getMP();
                    String move = round.getMonsterString();
                    monsterMove.setText(move);
                    break;
                }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        fileSystem.save(UserManager.getInstance().getUsers(), "Users.ser");
        fileSystem.save(ScoreBoard.getInstance().getUserPlayers(), "ScoreBoard.ser");
    }

    @Override
    protected void onPause() {
        super.onPause();
        fileSystem.save(UserManager.getInstance().getUsers(), "Users.ser");
        fileSystem.save(ScoreBoard.getInstance().getUserPlayers(), "ScoreBoard.ser");
    }

    /**
     * A battle between the player and the monster in one round.
     * @param playerMove the player's choose of move.
     * @param round the current game round.
     */
    private void battle(String playerMove, Round round){
        round.battle(playerMove, monsterProperty);
        int decreaseM = round.getDamage1();
        int decreaseP = round.getDamage2();
        if (player.getLivesRemain() > decreaseP) {
            player.loseLives(decreaseP);
        } else player.loseLives(player.getLivesRemain());
        if (monster.getLivesRemain() > decreaseM) {
            monster.loseLives(decreaseM);
        } else monster.loseLives(monster.getLivesRemain());
    }


    /**
     * Check the remain live of the monster.
     * @param monster current monster.
     * @param player current player.
     * @return the integer indication. 1 represents player lose, and 2 represent monster lose.
     */
    private int checkLife(Monster monster, Player player) {
        if (monster.getLivesRemain() > 0 && player.getLivesRemain() > 0) {
            return 0;
        } else if (player.getLivesRemain() <= 0) {
            return 1;
        } else {
            return 2;
        }
    }

    /**
     * Check whether player lose or player win.
     * @param num integer indicator of player's status.
     */
    private void winOrLose(int num) {
        if (num == 1) {
            //player lose
            startActivity(new Intent(BattleActivity.this, LoseActivity.class));
            player.setCurStage(4);
            fileSystem.save(UserManager.getInstance().getUsers(), "Users.ser");
        }
        if (num == 2) {
            //player win
            startActivity(new Intent(BattleActivity.this, WinActivity.class));
            player.setCurStage(4);
            // player.setSave(true);
            fileSystem.save(UserManager.getInstance().getUsers(), "Users.ser");
        }
    }

    /** Update all round number, player's remainLive, and monster's remainLive after each battle. */
    private void update() {
        roundNum++;
        String round_n = ("Round Number:" + roundNum);
        String life = ("Life:" + player.getLivesRemain());
        String monster_life = ("Monster Life:" + monster.getLivesRemain());
        roundView.setText(round_n);
        lifeView.setText(life);
        monsterLifeView.setText(monster_life);
    }
}