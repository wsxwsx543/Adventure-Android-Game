package com.example.phase1.stage1;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.phase1.R;

/**
 * The treasure class
 */
public class g1Treasure {
    /**
     * The x, y coordinate and the length and width of the treasure image
     */
    int x = 1008, y = 720, width = 72, height = 72;
    Bitmap treasurerview;

    /**
     * @param res
     */
    g1Treasure(Resources res){
        treasurerview = BitmapFactory.decodeResource(res, R.drawable.baoxiang2);

        treasurerview = Bitmap.createScaledBitmap(treasurerview, width, height, false);
    }

    /**
     * Return the treasure image
     */
    Bitmap getTreasurerview(){ return treasurerview;}
}
