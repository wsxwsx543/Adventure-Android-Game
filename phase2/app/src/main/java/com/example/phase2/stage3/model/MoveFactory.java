package com.example.phase2.stage3.model;

import com.example.phase2.appcore.game.Player;


public class MoveFactory {
    public Move getMove(String type, Player player, Monster monster){
        if(type.equalsIgnoreCase("MonsterMove")) {
            return new MonsterMove(monster);
        }
        else if(type.equalsIgnoreCase("PlayerMove")){
            return new PlayerMove(player);
        }

        return null;
    }
}
