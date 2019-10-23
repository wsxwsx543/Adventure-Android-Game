package com.example.phase1;

import java.util.ArrayList;
import java.util.List;

public class WeaponManager {
    private List<Weapon> weapons;

    public WeaponManager(){
        weapons = new ArrayList<>();
    }

    void addWeapon(Weapon weapon){
        weapons.add(weapon);
    }

    void removeWeapon(String name){
        for (Weapon weapon: weapons){
            if(weapon.getName().equals(name))
                weapons.remove(weapon);
        }
    }

    Weapon takeWeapon(String name){
        for(Weapon weapon: weapons){
            if(weapon.getName().equals(name))
                return weapon;
        }
        return new Weapon("None", 0, 0, 0, 0);
    }

    Property calculateProperty(){
        Property property = new Property(0, 0, 0, 0);
        for(Weapon weapon: weapons){
            property.addPropertyToSelf(weapon.getAttack(), weapon.getDefence(), weapon.getFlexibility(),
                    weapon.getLuckiness());
        }
        return property;
    }
}
