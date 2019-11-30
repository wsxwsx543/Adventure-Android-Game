package com.example.phase2.stage1.model;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.phase2.R;

public class Door extends MazeObjects {
    /**
     * The x, y coordinate and the length and width of the door image
     */
    private int x, y, width = 90, height = 90;
    private Bitmap doorView;
    private String type;

    /**
     * @param res
     */
    Door(int curr_x, int curr_y, Resources res, String type){
        this.x = curr_x;
        this.y = curr_y;

        this.type = type;

        doorView = BitmapFactory.decodeResource(res, R.drawable.door2);

        doorView = Bitmap.createScaledBitmap(doorView, width, height, false);
    }

    /**
     * functions to get and set attributes of door
     */
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
        return doorView;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public void setType(String type) {
        this.type = type;
    }
}