package com.example.phase2.stage1;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.phase2.R;

/**
 * The monster class
 */
public class Monster extends MazeObjects{
    /**
     * The x, y coordinate and the length and width of the Monster image
     */
    private int x , y, width = 90, height = 90;

    Bitmap monsterView;

    /**
     * @param curr_x the x coordinate of monster
     * @param curr_y the y coordinate of monster
     */
    Monster(int curr_x, int curr_y, Resources res){
        this.x = curr_x;
        this.y = curr_y;

        monsterView = BitmapFactory.decodeResource(res, R.drawable.monster);

        monsterView = Bitmap.createScaledBitmap(monsterView, width, height, false);
    }

    @Override
    public int getX(){
        return x;
    }
    @Override
    public int getY(){
        return y;
    }
    @Override
    public int getWidth(){
        return width;
    }
    @Override
    public int getHeight(){
        return height;
    }
    @Override
    public void setX(int x){
        this.x = x;
    }
    @Override
    public void setY(int y){
        this.y = y;
    }
    @Override
    public void setWidth(int width){
        this.width = width;
    }
    @Override
    public void setHeight(int height){
        this.height = height;
    }
    @Override
    public Bitmap getView() {
        return monsterView;
    }

}