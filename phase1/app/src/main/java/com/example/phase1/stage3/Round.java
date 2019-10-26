package com.example.phase1.stage3;

import com.example.phase1.Player;

public class Round {
    private Player player;
    private Monster monster;
    int round_number = 0;

    public Round(Player player, Monster monster){
        this.player = player;
        this.monster = monster;
        this.round_number += 1;
    }

    int getRound_number(){
      return this.round_number;
    }

}
