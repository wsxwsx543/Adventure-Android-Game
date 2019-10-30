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

    public void setLocation(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getX(){return this.x;}
    public int getY(){return this.y;}

    // Move in x direction.
    public void moveInX(int move){
        this.x += move;
    }

    // Move in y direction.
    public void moveInY(int move){
        this.y += move;
    }

    // Add a new weapon to this player.
    public void addWeapon(Weapon weapon){
        weaponManager.addWeapon(weapon);
        Property weaponsProperty = weaponManager.calculateProperty();
        this.property = this.property.addProperty(weaponsProperty);
    }

    public void loseLives(int num){
        this.livesRemain -= num;
    }
    public void addLives(int num) {this.livesRemain += num;}
    public void setLivesRemain(int num){this.livesRemain = num;}
    public int getLivesRemain(){return this.livesRemain;}

    public void createAttack(int attack){
        this.attackCreate += attack;
    }
    public int getAttackCreate(){return this.attackCreate;}
}
