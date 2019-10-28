package com.example.phase1;

import java.io.Serializable;

public class Player implements Serializable {
    private String name;
    private WeaponManager weaponManager; // Store all weapons.

    String getName(){return name;}

    private Property property;
    private int livesRemain;

    public Property getProperty() {
        return property;
    }
    void setProperty(Property property) {
        this.property = property;
    }

    // x, y is the location of this player.
    private int x;
    private int y;

    // Total attack the player create.
    private int attackCreate;

    private int curStage;

    public Player(String name, Property initialProperty){
        weaponManager = new WeaponManager();
        this.name = name;
        this.property = initialProperty;
        this.livesRemain = 100;
        this.attackCreate = 0;
        this.curStage = 0;
    }

    public int getCurStage() {
        return curStage;
    }
    public void setCurStage(int curStage) {
        this.curStage = curStage;
    }

    void setLocation(int x, int y){
        this.x = x;
        this.y = y;
    }

    int getX(){return this.x;}
    int getY(){return this.y;}

    // Move in x direction.
    void moveInX(int move){
        this.x += move;
    }

    // Move in y direction.
    void moveInY(int move){
        this.y += move;
    }

    // Add a new weapon to this player.
    void addWeapon(Weapon weapon){
        weaponManager.addWeapon(weapon);
        Property weaponsProperty = weaponManager.calculateProperty();
        this.property = this.property.addProperty(weaponsProperty);
    }

    void loseLives(int num){
        this.livesRemain -= num;
    }
    void addLives(int num) {this.livesRemain += num;}
    void setLivesRemain(int num){this.livesRemain = num;}
    int getLivesRemain(){return this.livesRemain;}

    void createAttack(int attack){
        this.attackCreate += attack;
    }
    int getAttackCreate(){return this.attackCreate;}
}
