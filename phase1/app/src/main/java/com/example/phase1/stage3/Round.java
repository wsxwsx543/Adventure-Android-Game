package com.example.phase1.stage3;

import com.example.phase1.*;

import java.util.Random;

public class Round {
    private int roundNumber = 0;
    private Player player;
    private Monster monster;
    private MoveFactory moveFactory;
    private MonsterMove monsterMove;
    private Property MP;
    private Property PP;
    private String monsterString;
    private double damage1;  //player to monster
    private double damage2;  //monster to player

    // put round number in battle activity

    public Round(Player player, Monster monster){
        this.player = player;
        this.monster = monster;
    }


    public void battle1() {
        int id;
        Random R = new Random();
        if (monster.getLivesRemain() >= 1000) {
            id = R.nextInt(4);
            MP = moveFactory.monsterDoMove(id, monster);
        } else {
            id = R.nextInt(4) + 2;
            MP = moveFactory.monsterDoMove(id, monster);
        }
        monsterString = monsterMove.getString(id);
    }

    public String getMonsterString() {
        return monsterString;
    }

    public void battle2(String move){
        PP = moveFactory.playerDoMove(move, player);//decided by input

        roundNumber += 1;
        int damageToPlayer = MP.getAttack() - PP.getDefence();
        int damageToMonster = PP.getAttack() - MP.getDefence();
        int flex = PP.getFlexibility() - MP.getFlexibility();
        int luck = PP.getLuckiness() - MP.getLuckiness();

        if (damageToMonster > 0){
            if (luck > 0) {
                damage1 = 1.5 * damageToMonster;
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
    }

    public double getDamage1() {
        return damage1;
    }

    public double getDamage2() {
        return damage2;
    }

    public int getRoundNumber() {
        return roundNumber;
    }

    public String getResult() {
        String d1 = String.valueOf(damage1);
        String d2 = String.valueOf(damage2);
        return "You get " + d1 + "damage, And you do "+ d2 + "damage to the monster!";
    }
}
