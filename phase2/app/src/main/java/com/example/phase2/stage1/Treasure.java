package com.example.phase2.stage1;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.phase2.R;

/**
 * The treasure class
 */
public class Treasure {
    /**
     * The x, y coordinate and the length and width of the treasure image
     */
    private int x, y, width = 72, height = 72;
    private Bitmap treasurerview;

    /**
     * @param res
     */
    Treasure(int curr_x, int curr_y, Resources res){
        this.x = curr_x;
        this.y = curr_y;

        treasurerview = BitmapFactory.decodeResource(res, R.drawable.baoxiang2);

        treasurerview = Bitmap.createScaledBitmap(treasurerview, width, height, false);
    }

    /**
     * Return the treasure image
     */
    Bitmap getTreasurerview(){ return treasurerview;}

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