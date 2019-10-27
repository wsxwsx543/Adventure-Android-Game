package com.example.phase1.stage1;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.phase1.R;

public class g1hero {
    int x = 0, y = 360, width = 72, height = 72;
    boolean isGoingUp = false, isGoingdown = false, isGoingLeft = false, isGoingRight = false;
    Bitmap heroFront;

    g1hero(int screenY, Resources res){
        heroFront = BitmapFactory.decodeResource(res, R.drawable.g1_player_front);

        heroFront = Bitmap.createScaledBitmap(heroFront, width, height, false);

    }

    Bitmap getg1hero(){
        return heroFront;
    }
}
