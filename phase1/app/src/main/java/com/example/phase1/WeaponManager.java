package com.example.phase1;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

class WeaponManager implements Serializable {
    private List<Weapon> weapons;

    WeaponManager(){
        weapons = new ArrayList<>();
    }

    // Add a new weapon to this WeaponManager.
    void addWeapon(Weapon weapon){
        weapons.add(weapon);
    }

    // Remove the weapon with specific name
    void removeWeapon(String name){
        for (Weapon weapon: weapons){
            if(weapon.getName().equals(name))
                weapons.remove(weapon);
        }
    }

    // Return the weapon with specific name
    Weapon takeWeapon(String name){
        for(Weapon weapon: weapons){
            if(weapon.getName().equals(name))
                return weapon;
        }
        return new Weapon("None", 0, 0, 0, 0);
    }

    // Return a new Property object which is summation of all weapons.
    Property calculateProperty(){
        Property property = new Property(0, 0, 0, 0);
        for(Weapon weapon: weapons){
            property.addPropertyToSelf(weapon.getProperty());
        }
        return property;
    }
}
