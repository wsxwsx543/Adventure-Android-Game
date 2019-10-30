package com.example.phase1.stage2;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.phase1.R;

public class Treasure extends Box {
    public Treasure(int x, int y, int unit_size, Resources res){
        super(x, y, unit_size, res);
    }
    public void update(){
        if (expanded){
            bitmapToDraw = BitmapFactory.decodeResource(res, R.drawable.baoxiang2);
            bitmapToDraw = Bitmap.createScaledBitmap(this.bitmapToDraw, unit_size, unit_size, true);
        }
    }
}
