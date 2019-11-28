package com.example.phase2.stage3;

import com.example.phase2.appcore.game.Property;
import java.util.Random;

/* moveName
a: doubt
b: alert
c: attack
d: power attack
e: flying attack
f: magic attack
 */

public class MoveFactory {
    Property chooseMove(Property monsterProprty, int id){
        Random R = new Random();
        int x = R.nextInt(10);
        int y = R.nextInt(10);
        if (id == 0) {
            return monsterProprty;
        } else if (id == 1) {
            monsterProprty.addPropertyToSelf(10, 10, 20, 20);
            return monsterProprty;
        } else if (id == 2) {
            monsterProprty.addPropertyToSelf(25, 0, x, y);
            return monsterProprty;
        } else if (id == 3) {
            monsterProprty.addPropertyToSelf(35, 0, x, y);
            return monsterProprty;
        } else if (id == 4) {
            monsterProprty.addPropertyToSelf(40, 100, x - 3, y);
            return monsterProprty;
        } else if (id == 5) {
            monsterProprty.addPropertyToSelf(40, 0, 20, y);
            return monsterProprty;
        } else if (id == 6) {
            monsterProprty.addPropertyToSelf(-10, 0, -10, -10);
            return monsterProprty;
        } else {
            return null;
        }
    }

    String getString(int id){
        if (id == 0) {
            return "Monster is doubting.";
        } else if (id == 1) {
            return "Monster is alerting.";
        } else if (id == 2) {
            return "Monster is going to attack.";
        } else if (id == 3) {
            return "Monster seems getting power up.";
        } else if (id == 4) {
            return "Monster is flying";
        } else if (id == 5) {
            return "Monster is using fire";
        } else if (id == 6) {
            return "Monster is tired";
        } else {
            return null;
        }
    }
}
