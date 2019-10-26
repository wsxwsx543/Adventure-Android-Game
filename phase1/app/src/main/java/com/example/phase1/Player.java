package com.example.phase1;

import java.io.Serializable;

public class Player implements Serializable {
    String name;
    private WeaponManager weaponManager;

    public Property getProperty() {
        return property;
    }

    private Property property;
    int livesRemain;

    public void setProperty(Property property) {
        this.property = property;
    }

    private int row;
    private int col;
    int attackCreate;

    public Player(String name, Property initialProperty, int livesRemain){
        weaponManager = new WeaponManager();
        this.name = name;
        this.property = initialProperty;
        this.livesRemain = livesRemain;
        this.attackCreate = 0;
    }

    void setLocation(int row, int col){
        this.row = row;
        this.col = col;
    }

    void moveInRow(int move){
        this.row += move;
    }

    void moveInCol(int move){
        this.col += move;
    }

    void addWeapon(Weapon weapon){
        weaponManager.addWeapon(weapon);
        Property weaponsProperty = weaponManager.calculateProperty();
        this.property = this.property.addProperty(weaponsProperty);
    }

    void loseLives(int num){
        this.livesRemain --;
    }

    void createAttack(int attack){
        this.attackCreate += attack;
    }

    public String toString(){
        return "Player: " + this.name + "\n" +
                property.toString();
    }
}
