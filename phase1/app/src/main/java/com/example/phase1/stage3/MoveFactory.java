package com.example.phase1.stage3;

import com.example.phase1.*;

import java.util.Random;

public class MoveFactory {

    private Property PP;

    //use player do move method to get property of player after each move
    public Property playerDoMove(String moveName, Player player){

        Property p = player.getProperty();
        PP = new Property(p.getAttack(), p.getDefence(), p.getFlexibility(), p.getLuckiness());


        if(moveName.equals("Attack")){
            PP.addPropertyToSelf(30, 0, 0, 0);
            return PP;
        } else if(moveName.equals("Defence")){
            PP.addPropertyToSelf(-100, 50, -10, -10);
            return PP;
        } else if(moveName.equals("Evade")){
            PP.addPropertyToSelf(0,0,5,10);
            return PP;
        } else { return null;}
    }

    public Property getRoundProperty(){
        return PP;
    }

    //use monster do move method to get property of monster after each move
    public Property monsterDoMove(int id, Monster monster){

        Property m = monster.getProperty();
        Property MP = new Property(m.getAttack(), m.getDefence(), m.getFlexibility(), m.getLuckiness());
        Random R = new Random();
        int x = R.nextInt(10);
        int y = R.nextInt(10);

        if(id == 0){
            return MP;
        } else if(id == 1){
            MP.addPropertyToSelf(0,0,10,0);
            return MP;
        } else if(id == 2){
            MP.addPropertyToSelf(20,0,x,y);
            return MP;
        } else if(id == 3){
            MP.addPropertyToSelf(50,0,10,y);
            return MP;
        } else { return null;}
    }
}
