package com.example.phase2.stage3;

import com.example.phase2.appcore.game.Property;
import com.example.phase2.appcore.game.Player;
import java.util.Random;


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
