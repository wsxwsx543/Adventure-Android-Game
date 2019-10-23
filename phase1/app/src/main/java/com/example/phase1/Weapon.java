package com.example.phase1;

public class Weapon {
    private String name;
    private Property property;

    public Weapon(String name, int attack, int defence, int flexibility, int luckiness){
        this.property = new Property(attack, defence, flexibility, luckiness);
        this.name = name;
    }

    String getName(){
        return name;
    }

    Property getProperty(){
        return property;
    }

    int getAttack(){
        return property.attack;
    }

    int getDefence(){
        return property.defence;
    }

    int getLuckiness(){
        return property.luckiness;
    }

    int getFlexibility(){
        return property.flexibility;
    }
}
