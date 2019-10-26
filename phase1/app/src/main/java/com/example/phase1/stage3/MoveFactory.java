package com.example.phase1.stage3;

import com.example.phase1.*;

public class MoveFactory {
    //use player do move method to get property of player after each move
    public Property playerDoMove(String moveName, Player player){

        Property PP = player.getProperty();

        if(moveName == "a"){
            PP.addPropertyToSelf(30, 0, 0, 0);
            return PP;
        } else if(moveName == "b"){
            PP.addPropertyToSelf(-100, 50, -10, -10);
            return PP;
        } else if(moveName == "c"){
            PP.addPropertyToSelf(0,0,5,0);
            return PP;
        } else { return null;}
    }
    //use monster do move method to get property of monster after each move
    public Property monsterDoMove(String moveName, Monster monster){
        Property MP = monster.getProperty();

        if(moveName == "a"){
            return MP;
        } else if(moveName == "b"){
            MP.addPropertyToSelf(0,0,10,0);
            return MP;
        } else if(moveName == "c"){
            MP.addPropertyToSelf(20,0,0,0);
            return MP;
        } else if(moveName == "d"){
            MP.addPropertyToSelf(50,0,10,0);
            return MP;
        } else { return null;}
    }
}
