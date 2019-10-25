package com.example.phase1;

interface Move {
    String getMove();
}

class MonsterMove implements Move {
    private String moveName;
    private Property property;
/* movename
a: doubt
b: alert
c: attack
d: power attack
e: flying attack
f: magic attack
 */
    public MonsterMove(String moveName, Property property){
        this.moveName = moveName;
        this.property = property;
    }
        @Override
    public String getMove() {
        return this.moveName;
    }
}


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
        this.playerProperty = this.player.property;
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