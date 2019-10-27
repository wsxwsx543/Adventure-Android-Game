package com.example.phase1.stage3;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.view.SurfaceView;

import com.example.phase1.R;

public class BattleView extends SurfaceView {


    private Bitmap monster;

    private Bitmap background;

    private Paint liveRemains = new Paint();

    public BattleView(Context context) {
        super(context);

        monster = BitmapFactory.decodeResource(getResources(), R.drawable.bat_brains_6);

        background = BitmapFactory.decodeResource(getResources(), R.drawable.forest_background);

        liveRemains.setColor(Color.WHITE);

        liveRemains.setTextSize(70);

        liveRemains.setTypeface(Typeface.DEFAULT_BOLD);

        liveRemains.setAntiAlias(true);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(monster, 0, 0, null);
    }
}
