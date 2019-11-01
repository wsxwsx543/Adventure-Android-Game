package com.example.phase1;

import java.io.Serializable;
import java.util.List;

/**
 * A player.
 */
public class Player implements Serializable {

    /**
     * The player's name.
     */
    private String name;

    /**
     * The weapon manager store all the weapons of this player.
     */
    private WeaponManager weaponManager; // Store all weapons.

    /**
     * The property of this player.
     */
    private Property property;

    /**
     * The lives this player remain.
     */
    private int livesRemain;

    /**
     * The x-axis of this player.
     */
    private int x;

    /**
     * The y-axis of this player.
     */
    private int y;
    // Total attack the player create.

    /**
     * The total damage to the monster.
     */
    private int attackCreate;

    /**
     * The current stage of this player.
     */
    private int curStage;
    public Player(String name, Property initialProperty){
        weaponManager = new WeaponManager();
        this.name = name;
        this.property = initialProperty;
        this.livesRemain = 100;
        this.attackCreate = 0;
        this.curStage = 1;
    }

    String getName(){return name;}

    public Property getProperty() {
        return property;
    }

    void setProperty(Property property) {
        this.property = property;
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

    public int getLivesRemain(){return this.livesRemain;}

    public void setLivesRemain(int num){this.livesRemain = num;}

    public void createAttack(int attack){
        this.attackCreate += attack;
    }
    public int getAttackCreate(){return this.attackCreate;}

    public List<String> getWeaponNames(){
        return weaponManager.getWeaponNames();
    }
}
