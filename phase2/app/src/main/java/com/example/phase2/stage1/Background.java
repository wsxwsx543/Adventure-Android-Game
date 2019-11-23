package com.example.phase2.stage1;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.phase2.R;

/**
 * the class to store the background
 */
public class Background {
    /**
     * the x, y coordinates of background
     */
    int x = 0, y = 0;
    /**
     * background in the form of Bitmap
     */
    Bitmap background;

    /**
     * @param screenX the width of the background.
     * @param screenY the length of the background
     * @param res
     */
    Background(int screenX, int screenY, Resources res){

        background = BitmapFactory.decodeResource(res, R.drawable.background2);
        background = Bitmap.createScaledBitmap(background, screenX, screenY, false);

    }

}
