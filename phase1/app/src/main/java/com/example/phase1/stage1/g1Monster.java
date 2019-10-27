package com.example.phase1.stage1;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.phase1.R;

public class g1Monster {
    int x = 216, y = 216, width = 72, height = 72;

    Bitmap monsterview;

    g1Monster(g1View view, int ScreenY, Resources res){
        monsterview = BitmapFactory.decodeResource(res, R.drawable.g1_utoronto);

        monsterview = Bitmap.createScaledBitmap(monsterview, width, height, false);
    }

    Bitmap getMonsterView(){ return monsterview;}

//    public void action(){
//        double d = Math.random();
//        if (d < 0.25 && this.x <= view.getScreenX() - this.width){this.x += this.width;}
//        else if(0.25 <= d && d < 0.5 && this.x >= this.width){this.x -= this.width;}
//        else if(0.5 <= d && d < 0.75 && this.y <= view.getScreenY() - this.height){this.y += this.height;}
//        else if(this.y >= this.height){this.y -= this.height;}
//    }
}
