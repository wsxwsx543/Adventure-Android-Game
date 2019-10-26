package com.example.phase1.stage3;

import com.example.phase1.*;

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
