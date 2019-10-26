package com.example.phase1.stage1;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.phase1.R;

public class g1background {
    int x = 0, y = 0;
    Bitmap background;

    g1background(int screenX, int screenY, Resources res){

        background = BitmapFactory.decodeResource(res, R.drawable.g1_dialoguebackground);
        background = Bitmap.createScaledBitmap(background, screenX, screenY, false);

    }

}
