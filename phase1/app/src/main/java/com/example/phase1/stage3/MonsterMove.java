package com.example.phase1.stage3;

import com.example.phase1.*;

class MonsterMove implements Move {
    private int id;
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
    public MonsterMove(int id, String moveName, Property property){
        this.id = id;
        this.moveName = moveName;
        this.property = property;
    }
        @Override
    public String getMove() {
        return this.moveName;
    }

    public int getMoveId() {
        return id;
    }

    Property MP = new Property(100, 100, 0, 0);

    public String getName(int id){
        if(id == 0){
            return "Doubt";
        } else if(id == 1){
            return "Alert";
        } else if(id == 2){
            return "Attack";
        } else if(id == 3){
            return "Power";
        } else { return null;}
    }

    public String getString(int id){
        if(id == 0){
            return "Monster is doubting.";
        } else if (id == 1){
            return "Monster is alerting.";
        } else if (id == 2){
            return "Monster is going to attack.";
        } else if (id == 3){
            return "Monster seems getting power up.";
        } else {return null;}
    }
}
