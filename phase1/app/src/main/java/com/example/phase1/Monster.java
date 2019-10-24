package com.example.phase1;

public class Monster {
    int hp;
    Property property;

    public Monster(int hp, Property monsterProperty){
        this.hp = hp;
        this.property = monsterProperty;
    }

    int getHp() {
        return this.hp;
    }

    Property getProperty(){
        return property;
    }
}
