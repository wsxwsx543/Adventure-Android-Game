package com.example.phase1;

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
}
