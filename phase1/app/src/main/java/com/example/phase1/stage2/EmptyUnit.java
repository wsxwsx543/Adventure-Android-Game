package com.example.phase1.stage2;

import android.content.res.Resources;
import android.graphics.Bitmap;

import com.example.phase1.R;

public class EmptyUnit extends Box {
    public EmptyUnit(int x, int y, int unit_size, Resources resources){
        super(x, y, unit_size, resources);
    }
    public void update(){
        if (expanded){
            bitmapToDraw = getTrapsIndicatorImg(numOfNeighbourTraps);
            bitmapToDraw = Bitmap.createScaledBitmap(this.bitmapToDraw, unit_size, unit_size, true);
        }
    }

}