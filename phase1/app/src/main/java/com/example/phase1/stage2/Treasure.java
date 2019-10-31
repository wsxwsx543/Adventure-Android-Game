package com.example.phase1.stage2;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.phase1.R;
import com.example.phase1.User;
import com.example.phase1.UserManager;

public class Treasure extends Box {

    public String treasureType;
    public boolean looted;

    public Treasure(int x, int y, int unit_size, Resources res){
        super(x, y, unit_size, res);
    }
    public void update(){
        if (expanded){
            setType();
            this.looted = false;
            bitmapToDraw = BitmapFactory.decodeResource(res, R.drawable.baoxiang2);
            bitmapToDraw = Bitmap.createScaledBitmap(this.bitmapToDraw, unit_size, unit_size, true);
        }
    }

    //Method that randomly sets the type of the treasure
    private void setType(){
        int decider = (int)(Math.random() * 4);
        switch (decider){
            case 0:
                treasureType = "Attack";
                break;
            case 1:
                treasureType = "Defense";
                break;
            case 2:
                treasureType = "Luckiness";
                break;
            case 3:
                treasureType = "Flexibility";
                break;
        }
    }

    public void loot(){
        if (this.expanded && (!this.looted)) {
            int originalStat;
            switch (treasureType) {
                case "Attack":
                    originalStat = UserManager.getInstance().getCurUser().getCurPlayer().getProperty().getAttack();
                    UserManager.getInstance().getCurUser().getCurPlayer().getProperty().setAttack(originalStat + 1);
                    break;
                case "Defense":
                    originalStat = UserManager.getInstance().getCurUser().getCurPlayer().getProperty().getDefence();
                    UserManager.getInstance().getCurUser().getCurPlayer().getProperty().setDefence(originalStat + 1);
                    break;
                case "Luckiness":
                    originalStat = UserManager.getInstance().getCurUser().getCurPlayer().getProperty().getDefence();
                    UserManager.getInstance().getCurUser().getCurPlayer().getProperty().setLuckiness(originalStat + 1);
                    break;
                case "Flexibility":
                    originalStat = UserManager.getInstance().getCurUser().getCurPlayer().getProperty().getFlexibility();
                    UserManager.getInstance().getCurUser().getCurPlayer().getProperty().setFlexibility(originalStat + 1);
                    break;
            }
            looted = true;
        }
    }
}
