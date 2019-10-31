package com.example.phase1.stage1;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.phase1.R;

public class g1Treasure {
    int x = 1008, y = 720, width = 72, height = 72;
    Bitmap treasurerview;

    g1Treasure(g1View view, int ScreenY, Resources res){
        treasurerview = BitmapFactory.decodeResource(res, R.drawable.baoxiang2);

        treasurerview = Bitmap.createScaledBitmap(treasurerview, width, height, false);
    }

    Bitmap getTreasurerview(){ return treasurerview;}
}
