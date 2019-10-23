package com.example.phase1;

public class Property {
    int attack;
    int defence;
    int flexibility;
    int luckiness;

    public Property(int attack, int defence, int flexibility, int luckiness){
        this.attack = attack;
        this.defence = defence;
        this.flexibility = flexibility;
        this.luckiness = luckiness;
    }

    public void addPropertyToSelf(int attack, int defence, int flexibility, int luckiness){
        this.attack += attack;
        this.defence += defence;
        this.flexibility += flexibility;
        this.luckiness += luckiness;
    }

    public Property addProperty(Property property){
        return new Property(this.attack + property.attack,
                           this.defence + property.defence,
                           this.flexibility + property.flexibility,
                           this.luckiness + property.luckiness);
    }

    public String toString(){
        return "Attack: " + String.valueOf(attack) + "\n" +
                "Defence: " + String.valueOf(defence) + "\n" +
                "Flexibility: " + String.valueOf(flexibility) + "\n" +
                "Luckniss: " + String.valueOf(luckiness) + "\n";
    }
}
