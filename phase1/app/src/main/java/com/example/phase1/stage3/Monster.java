package com.example.phase1.stage3;

import com.example.phase1.Property;

public class Monster {
    private int livesRemain;
    private Property property;

    public Monster(int livesRemain, Property monsterProperty){
        this.livesRemain = livesRemain;
        this.property = monsterProperty;                    
    }

    int getLivesRemain() {
        return this.livesRemain;
    }

    Property getProperty(){
        return property;
    }

    public void loseLives(int num){
        this.livesRemain -= num;
    }
}
