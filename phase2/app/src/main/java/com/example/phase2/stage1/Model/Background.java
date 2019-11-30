package com.example.phase2.stage1.Model;

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
    public int x = 0, y = 0;
    /**
     * background in the form of Bitmap
     */
    public Bitmap backgroundView;

    /**
     * @param screenX the width of the background.
     * @param screenY the length of the background
     * @param res
     */
    Background(int screenX, int screenY, Resources res){

        backgroundView = BitmapFactory.decodeResource(res, R.drawable.background2);
        backgroundView = Bitmap.createScaledBitmap(backgroundView, screenX, screenY, false);

    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Bitmap getBackgroundView() {
        return backgroundView;
    }
}
