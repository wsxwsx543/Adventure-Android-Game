package com.example.phase1;

import java.io.Serializable;

public class Weapon implements Serializable {
    private String name;
    private Property property;

    public Weapon(String name, int attack, int defence, int flexibility, int luckiness) {
        this.property = new Property(attack, defence, flexibility, luckiness);
        this.name = name;
    }

    public Weapon(String name, Property property) {
        this.property = property;
        this.name = name;
    }

    public void setProperty(Property property) {
        this.property = property;
    }

    public String getName() {
        return name;
    }

    public Property getProperty() {
        return property;
    }

    public int getAttack() {
        return property.getAttack();
    }

    public int getDefence() {
        return property.getDefence();
    }

    public int getLuckiness() {
        return property.getLuckiness();
    }

    public int getFlexibility() {
        return property.getFlexibility();
    }
}
