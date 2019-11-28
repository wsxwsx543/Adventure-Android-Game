package com.example.phase2.stage3;

import com.example.phase2.appcore.Player;

public class MoveFactory {
    public Move getMove(String moveType, Player player, Monster monster){
        if ("MonsterMove".equalsIgnoreCase(moveType)) {
            return new MonsterMove(monster);
        }
        else if ("PlayerMove".equalsIgnoreCase(moveType)){
            return new PlayerMove(player);
        }
        return null;
    }
}
