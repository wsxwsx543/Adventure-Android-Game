package com.example.phase1.stage3;

import com.example.phase1.*;


class PlayerMove implements Move {
    private Player player;
    private Property playerProperty;
    private String moveName;
/* movename
a: attack
b: defence
c: escape
d:
 */
    public PlayerMove(String moveName, Player player) {
        this.moveName = moveName;
        this.player = player;
        this.playerProperty = this.player.getProperty();
    }
    @Override
    public String getMove() {
        return this.moveName;
    }
}


/* public class MoveFactory {


    //use getShape method to get object of type shape
    public Property playerDoMove(String moveName){

        if(moveType == null){
            return null;
        }
        if(moveType.equalsIgnoreCase("MonsterMove")){
            if(moveName == "a"){
                Property MonsterSta
            }

        } else if(moveType.equalsIgnoreCase("PlayerMove")) {
            return new PlayerMove;
        }

        return null;
    }
}
class MoveFortune {
    public Move doMove(Move move, String movename){
    this.move =move;

    }
}

*/