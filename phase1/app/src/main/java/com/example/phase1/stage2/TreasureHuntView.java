package com.example.phase1.stage2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.SurfaceView;

import java.util.ArrayList;

public class TreasureHuntView extends SurfaceView {

    public ArrayList<Box> boxes;
    private ArrayList<Bitmap>  boxesBitmap;
    public TreasureHuntView(Context context) {
        super(context);

        for (int i = 0; i < boxes.size(); i++){
            boxesBitmap.add(BitmapFactory.decodeResource(getResources(), boxes.get(i).unit.imageID));
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


    }
}
