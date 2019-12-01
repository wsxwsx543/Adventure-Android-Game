package com.example.phase2.stage3.model;

import com.example.phase2.appcore.game.Player;
import com.example.phase2.appcore.game.Property;
import com.example.phase2.stage3.model.Monster;
import com.example.phase2.stage3.model.Move;
import com.example.phase2.stage3.model.MoveFactory;

import java.util.Random;

/**
 * A round of a game.
 */
public class Round {

    /**
     * The player of this round.
     */
    private Player player;
    /**
     * The monster of this round.
     */
    private Monster monster;
    /**
     * The property of this monster.
     */
    private Property monsterProperty;
//    /**
//     * The property of this player.
//     */
//    private Property PP;
//    /**
//     * The string that show the monster's move.
//     */
    private String monsterString;
    /**
     * The damage from player to monster.
     */
    private int damage1;
    /**
     * The damage from monster to player.
     */
    private int damage2;

    private MoveFactory moveFactory;
//    /**
//     * The move of player.
//     */
//    private PlayerMove playerMove;
//    /**
//     * The move of player.
//     */
//    private MonsterMove monsterMove;


    /**
     * Constructs a new round of given player and monster.
     */
    public Round(Player player, Monster monster) {
        this.player = player;
        this.monster = monster;
        moveFactory = new MoveFactory();
    }


    /**
     * A battle that monster its move. To return monster's move.
     */
    private void monsterDoMove() {
        int id;
        Random R = new Random();
        Move move = moveFactory.getMove("MonsterMove", this.player, this.monster);
        if (monster.getLivesRemain() >= 100) {
            id = R.nextInt(4);
            monsterProperty = move.doMove(id);
        } else {
            id = R.nextInt(4) + 3;
            monsterProperty = move.doMove(id);
        }
        monsterString = move.getString(id);
    }

    public Property getMonsterProperty(){
        monsterDoMove();
        return monsterProperty;
    }

    /**
     * A method that get monster's move in a string type.
     *
     * @return Return the monster's string.
     */
    public String getMonsterString() {
        return monsterString;
    }

    /**
     * Doing the damage calculation after player's choice.
     *
     * @param moveNum the move player choose.
     * @param MP   the monster's property we get from battle1.
     */
    public void battle(int moveNum, Property MP) {
        Move move = moveFactory.getMove("PlayerMove", this.player, this.monster);
        Property playerProperty = move.doMove(moveNum); //decided by input

        int damageToPlayer = MP.getAttack() - playerProperty.getDefence();
        int damageToMonster = playerProperty.getAttack() - MP.getDefence();
        int flex = playerProperty.getFlexibility() - MP.getFlexibility();
        int luck = playerProperty.getLuckiness() - MP.getLuckiness();

        if (damageToMonster > 0) {
            if (luck > 0) {
                damage1 = 2 * damageToMonster;
            } else {
                damage1 = damageToMonster;
            }
        } else {
            damage1 = 0;
        }

        if (damageToPlayer > 0) {
            if (flex > 0) {
                damage2 = 0;
            } else {
                damage2 = damageToPlayer;
            }
        } else {
            damage2 = 0;
        }
    }

    /**
     * @return the damage player did to the monster.
     */
    public int getDamage1() {
        return damage1;
    }

    /**
     * @return get the damage monster did to the player.
     */
    public int getDamage2() {
        return damage2;
    }

}
