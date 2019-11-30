package com.example.phase2.stage1.model;

import android.graphics.Paint;

import com.example.phase2.appcore.user.User;
import com.example.phase2.datamanagement.FileSystem;

import java.util.List;

/**
 * model which contains all properties of monster, treasure, door
 */
public interface IMazeModel {
    public int getScreenX();

    public int getScreenY();

    public Background getCurBackground();

    public List<MazeObjects> getMyMonsters();

    public List<MazeObjects> getMyTreasures();

    public List<MazeObjects> getMyDoors();

    public Hero getHero();

    public User getCurUser();

    public FileSystem getFileSystem();

    public String getHasKey();

    public void setHasKey(String hasKey);

    public int getAttack();

    public void setAttack(int attack);

    public int getDefence();

    public void setDefence(int defence);

    public int getFlexibility();

    public void setFlexibility(int flexibility);

    public int getLuckiness();

    public void setLuckiness(int luckiness);

    public int getLife();

    public void setLife(int life);

    public int getGiftLife();

    public void setGiftLife(int giftLife);

    public int getGiftAttack();

    public void setGiftAttack(int giftAttack);

    public int getGiftDefence();

    public void setGiftDefence(int giftDefence);

    public int getGiftFlexibility();

    public void setGiftFlexibility(int giftFlexibility);

    public int getGiftLuckiness();

    public void setGiftLuckiness(int giftLuckiness);

    public boolean getIsPlaying();

    public Paint getPaint();

    public Paint getTextPaint();

    public void setTextPaint();

    public void setIsPlaying(boolean playingOrNot);


}
