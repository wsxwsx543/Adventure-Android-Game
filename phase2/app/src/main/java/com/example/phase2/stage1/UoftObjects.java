package com.example.phase2.stage1;

import android.graphics.Bitmap;

public abstract class UoftObjects {
    public abstract int getX();
    public abstract int getY();
    public abstract int getWidth();
    public abstract int getHeight();
    public abstract void setX(int x);
    public abstract void setY(int y);
    public abstract void setWidth(int width);
    public abstract void setHeight(int height);
    public abstract Bitmap getView();
}
