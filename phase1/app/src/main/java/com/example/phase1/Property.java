package com.example.phase1;

import java.io.Serializable;

public class Property implements Serializable {
    private int attack; // Obvious
    private int defence; // Obvious
    private int flexibility; // Obvious
    private int luckiness; // Obvious

    public Property(int attack, int defence, int flexibility, int luckiness) {
        this.attack = attack;
        this.defence = defence;
        this.flexibility = flexibility;
        this.luckiness = luckiness;
    }

    public int getAttack() {
        return attack;
    }

    public int getDefence() {
        return defence;
    }

    public int getFlexibility() {
        return flexibility;
    }

    public int getLuckiness() {
        return luckiness;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public void setDefence(int defence) {
        this.defence = defence;
    }

    public void setFlexibility(int flexibility) {
        this.flexibility = flexibility;
    }

    public void setLuckiness(int luckiness) {
        this.luckiness = luckiness;
    }

    // Setter for this class, add new properties to itself.
    public void addPropertyToSelf(int attack, int defence, int flexibility, int luckiness) {
        this.attack += attack;
        this.defence += defence;
        this.flexibility += flexibility;
        this.luckiness += luckiness;
    }

    public void addPropertyToSelf(Property property) {
        this.attack += property.attack;
        this.defence += property.defence;
        this.luckiness += property.luckiness;
        this.flexibility += property.flexibility;
    }

    // Return a new Property object which equals to addition of itself and new Property object.
    Property addProperty(int attack, int defence, int flexibility, int luckiness) {
        return new Property(attack + this.attack, defence + this.defence,
                flexibility + this.flexibility, luckiness + this.luckiness);
    }

    Property addProperty(Property property) {
        return new Property(this.attack + property.attack,
                this.defence + property.defence,
                this.flexibility + property.flexibility,
                this.luckiness + property.luckiness);
    }

    public String toString(){
        return new String("Attack: " + String.valueOf(this.attack) +
                " Defence: " + String.valueOf(this.defence) +
                " Flexibility: " + String.valueOf(this.flexibility) +
                " Luckiness: " + String.valueOf(this.luckiness));
    }
}
