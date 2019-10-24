package com.example.phase1;

public class MonsterTurn {
    Monster boss;
    Property new_property;
    // int hp;

    public MonsterTurn(Monster monster){
         //this.hp = boss.hp;
        this.boss = monster;
    }

    void addProperty(MonsterMove move){
        this.new_property = boss.property.addProperty(move);
    }

    Property getProperty(){
        return this.new_property;
    }
}
