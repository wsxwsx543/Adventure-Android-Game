package com.example.phase1.stage3;

import com.example.phase1.*;

import java.util.Random;

/** A round. */
public class Round {

    /** The player of this round. */
    private Player player;
    /** The monster of this round. */
    private Monster monster;
    /** The move factory of this monster's move. */
    private MoveFactory moveFactory;
    /** The property of this monster. */
    private Property MP;
    /** The property of this player. */
    private Property PP;
    /** The string that show the monster's move. */
    private String monsterString;
    /** The damage from player to monster. */
    private int damage1;
    /** The damage from monster to player. */
    private int damage2;


    /** Constructs a new round of given player and monster */
    public Round(Player player, Monster monster){
        this.player = player;
        this.monster = monster;
        this.moveFactory = new MoveFactory();
    }

    /**
     * To return monster's move
     * @return monster's property
     */
    public Property battle1() {
        int id;
        Random R = new Random();
        if (monster.getLivesRemain() >= 100) {
            id = R.nextInt(4);
            MP = moveFactory.monsterDoMove(id, monster);
        } else {
            id = R.nextInt(4) + 3;
            MP = moveFactory.monsterDoMove(id, monster);
        }
        monsterString = MonsterMove.getString(id);
        return MP;
    }

    /**
     * @return Return the monster's string
     */
    public String getMonsterString() {
        return monsterString;
    }

    /**
     * Doing the damage calculation after player's choice
     * @param move the move player choose
     * @param MP the monster's property we get from battle1
     */
    public void battle2(String move, Property MP){
        PP = moveFactory.playerDoMove(move, player); //decided by input

        int damageToPlayer = MP.getAttack() - PP.getDefence();
        int damageToMonster = PP.getAttack() - MP.getDefence();
        int flex = PP.getFlexibility() - MP.getFlexibility();
        int luck = PP.getLuckiness() - MP.getLuckiness();

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
    }

    /**
     * @return the damage player did to the monster
     */
    public int getDamage1() {
        return damage1;
    }

    /**
     * @return get the damage monster did to the player
     */
    public int getDamage2() {
        return damage2;
    }


}
