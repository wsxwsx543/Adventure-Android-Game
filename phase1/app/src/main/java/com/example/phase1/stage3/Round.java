package com.example.phase1.stage3;

import com.example.phase1.*;

import java.util.Random;

public class Round {
    private Player player;
    private Monster monster;
    private MoveFactory moveFactory;
    private MonsterMove monsterMove;
    private Property MP;
    private Property PP;
    private String monsterString;
    private int damage1;  // player to monster
    private int damage2;  //monster to player

    // put round number in battle activity

    public Round(Player player, Monster monster){
        this.player = player;
        this.monster = monster;
        this.moveFactory = new MoveFactory();
        this.MP = new Property(0, 0, 0, 0);
    }


    public void battle1() {
        int id;
        Random R = new Random();
        if (monster.getLivesRemain() >= 100) {
            id = R.nextInt(4);
            this.MP = moveFactory.monsterDoMove(id, monster);
        } else {
            id = R.nextInt(4) + 2;
            this.MP = moveFactory.monsterDoMove(id, monster);
        }
        monsterString = MonsterMove.getString(id);
    }

    public String getMonsterString() {
        return monsterString;
    }

    public void battle2(String move){
        PP = moveFactory.playerDoMove(move, player); //decided by input

        int damageToPlayer = this.MP.getAttack() - PP.getDefence();
        int damageToMonster = PP.getAttack() - this.MP.getDefence();
        int flex = PP.getFlexibility() - this.MP.getFlexibility();
        int luck = PP.getLuckiness() - this.MP.getLuckiness();

        if (damageToMonster > 0){
            if (luck > 0) {
                damage1 = 2 * damageToMonster;
            } else {
                damage1 = damageToMonster;
            }
        } else {damage1 = 0;}

        if (damageToPlayer > 0){
            if (flex > 0){
                damage2 = 0;
            } else  {
                damage2 = damageToPlayer;
            }
        } else {damage2 = 0;}
        //Property player_property = player.getProperty(); //copy
        // player.getProperty().addPropertyToSelf(playermove);
        // monster.getProperty().addPropertyToSelf(monstermove);
    }

    public int getDamage1() {
        return damage1;
    }

    public int getDamage2() {
        return damage2;
    }


}
