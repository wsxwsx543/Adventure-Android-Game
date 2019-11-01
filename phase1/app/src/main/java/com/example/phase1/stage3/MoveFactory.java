package com.example.phase1.stage3;

import com.example.phase1.*;

import java.util.Random;

public class MoveFactory {

    private Property PP;

    public MoveFactory() {
    }

    /**
     * use playerDoMove method to get property of player after each move
     * @param moveName the name of player's move
     * @param player the player
     * @return the property of the player after move
     */
    public Property playerDoMove(String moveName, Player player){

        Property p = player.getProperty();
        PP = new Property(p.getAttack(), p.getDefence(), p.getFlexibility(), p.getLuckiness());


        if(moveName.equals("Attack")){
            PP.addPropertyToSelf(10, 0, 0, 0);
            return PP;
        } else if(moveName.equals("Defence")){
            PP.addPropertyToSelf(-100, 30, -10, -10);
            return PP;
        } else if(moveName.equals("Evade")){
            PP.addPropertyToSelf(0,0,5,10);
            return PP;
        } else { return null;}
    }


    /**
     * use monsterDoMove method to get property of monster after each move
     * @param id an random number to decide what the monster would do
     * @param monster the monster we decided
     * @return the property of monster after the move
     */
    public Property monsterDoMove(int id, Monster monster){

        Property m = monster.getProperty();
        Property MP = new Property(m.getAttack(), m.getDefence(), m.getFlexibility(), m.getLuckiness());
        Random R = new Random();
        int x = R.nextInt(10);
        int y = R.nextInt(10);

        if(id == 0){
            return MP;
        } else if(id == 1){
            MP.addPropertyToSelf(10,10,20,20);
            return MP;
        } else if(id == 2){
            MP.addPropertyToSelf(25,0,x,y);
            return MP;
        } else if(id == 3){
            MP.addPropertyToSelf(35,0,x,y);
            return MP;
        } else if(id == 4){
            MP.addPropertyToSelf(40,100,x-3,y);
            return MP;
        } else if(id == 5){
            MP.addPropertyToSelf(40,0,20,y);
            return MP;
        } else if(id == 6){
            MP.addPropertyToSelf(-10,0,-10,-10);
            return MP;
        } else {return null;}
    }
}
