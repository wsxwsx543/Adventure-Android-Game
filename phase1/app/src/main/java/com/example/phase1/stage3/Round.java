package com.example.phase1.stage3;

import com.example.phase1.*;

public class Round {
    private Player player;
    private Monster monster;
    int round_number = 0;
    private MoveFactory moveFactory;

    public Round(Player player, Monster monster){
        this.player = player;
        this.monster = monster;
        this.round_number += 1;
    }

    public void battle(){
        Property playermove = moveFactory.playerDoMove("a", player);
        Property monstermove = moveFactory.monsterDoMove("a", monster);
    }

    int getRound_number(){
      return this.round_number;
    }

}
