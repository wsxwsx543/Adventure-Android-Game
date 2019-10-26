package com.example.phase1;

import java.io.Serializable;

public class Weapon implements Serializable {
    private String name;
    private Property property;

    Weapon(String name, int attack, int defence, int flexibility, int luckiness) {
        this.property = new Property(attack, defence, flexibility, luckiness);
        this.name = name;
    }

    Weapon(String name, Property property) {
        this.property = property;
        this.name = name;
    }

    void setProperty(Property property) {
        this.property = property;
    }

    String getName() {
        return name;
    }

    Property getProperty() {
        return property;
    }

    int getAttack() {
        return property.getAttack();
    }

    int getDefence() {
        return property.getDefence();
    }

    int getLuckiness() {
        return property.getLuckiness();
    }

    int getFlexibility() {
        return property.getFlexibility();
    }
}
