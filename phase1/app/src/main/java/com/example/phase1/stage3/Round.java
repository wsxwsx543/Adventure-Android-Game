package com.example.phase1.stage3;

import com.example.phase1.*;

public class Round {
    private Player player;
    private Monster monster;
    private MoveFactory moveFactory;
    // put round number in battle activity

    public Round(Player player, Monster monster){
        this.player = player;
        this.monster = monster;
    }

    public void battle(){
        Property playermove = moveFactory.playerDoMove("a", player);
        Property monstermove = moveFactory.monsterDoMove("a", monster);
        Property player_property = player.getProperty(); //copy
        // player.getProperty().addPropertyToSelf(playermove);
        // monster.getProperty().addPropertyToSelf(monstermove);
    }


}
