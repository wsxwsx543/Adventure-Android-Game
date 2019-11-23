package com.example.phase2.stage1;

import android.content.res.Resources;

public class UoftObjectsFactory {
    public static UoftObjects getUoftObjects(String what, int curr_x, int curr_y, Resources res){
        if("Treasure".equalsIgnoreCase(what)) return new Treasure(curr_x, curr_y, res);
        else if("Monster".equalsIgnoreCase(what)) return new Monster(curr_x, curr_y, res);
        else if("Door".equalsIgnoreCase(what)) return new Door(curr_x, curr_y, res);

        return null;
    }
}
