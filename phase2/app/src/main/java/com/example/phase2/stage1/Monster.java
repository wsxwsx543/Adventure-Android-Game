package com.example.phase2.stage1;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.phase2.R;

/**
 * The monster class
 */
public class Monster {
    /**
     * The x, y coordinate and the length and width of the Monster image
     */
    private int x , y, width = 72, height = 72;

    Bitmap monsterview;

    /**
     * @param curr_x the x coordinate of monster
     * @param curr_y the y coordinate of monster
     */
    Monster(int curr_x, int curr_y, Resources res){
        this.x = curr_x;
        this.y = curr_y;

        monsterview = BitmapFactory.decodeResource(res, R.drawable.g1_utoronto);

        monsterview = Bitmap.createScaledBitmap(monsterview, width, height, false);
    }

    /**
     * Return the image of monster
     */
    Bitmap getMonsterView(){ return monsterview;}

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public int getWidth(){
        return width;
    }

    public int getHeight(){
        return height;
    }

    public void setX(int x){
        this.x = x;
    }

    public void setY(int y){
        this.y = y;
    }

    public void setWidth(int width){
        this.width = width;
    }

    public void setHeight(int height){
        this.height = height;
    }

}