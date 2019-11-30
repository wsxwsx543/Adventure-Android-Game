package com.example.phase2.stage1.Model;

import android.graphics.Bitmap;

public abstract class MazeObjects {
    public abstract int getX();
    public abstract int getY();
    public abstract int getWidth();
    public abstract int getHeight();
    public abstract void setX(int x);
    public abstract void setY(int y);
    public abstract void setWidth(int width);
    public abstract void setHeight(int height);
    public abstract void setType(String type);
    public abstract String getType();
    public abstract Bitmap getView();
}