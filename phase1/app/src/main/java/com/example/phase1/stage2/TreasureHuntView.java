package com.example.phase1.stage2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import java.util.ArrayList;

public class TreasureHuntView extends SurfaceView implements Runnable {


    public float curX = -1, curY = -1;
    public Box[][] boxes;
    private BoxesMapper boxesMapper;
    private ArrayList<Bitmap> boxesBitmap;
    private int boardWidth, boardLength, startX, startY;
    private int unit_size;
    public Thread thread;
    public SurfaceHolder holder;
    public boolean running;

    public TreasureHuntView(Context context, int boardWidth, int boardLength, int unit_size, int startX, int startY) {
        super(context);


        this.holder = getHolder();
        this.boardWidth = boardWidth;
        this.boardLength = boardLength;
        this.startX = startX;
        this.startY = startY;
        this.unit_size = unit_size;

        boxesMapper = new BoxesMapper(this.boardWidth, this.boardLength, this.unit_size, this.startX, this.startY);
        boxes = boxesMapper.getBoxes();
    }

    public TreasureHuntView(Context context) {
        this(context, 10, 15, 108, 0, 200);
    }

    public void pause() {
        running = false;
        while (true) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            break;
        }
        thread = null;
    }

    public void resume() {
        running = true;
        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        while (running) {
            if (!holder.getSurface().isValid()) {
                continue;
            }
            //Lock canvas so we can draw
            Canvas canvas = holder.lockCanvas();

            //Update Boxes on touch
            System.out.println(curX + " " + curY);
            if (curX != -1 && curY != -1) {
                getCurBox(curX, curY).expand(new ArrayList<>());
                curX = -1;
                curY = -1;
            }

            // Draw background
            canvas.drawARGB(255, 255, 255, 255);

            //Draw the boxes
            for (int y = 0; y < this.boardLength; y++) {
                for (int x = 0; x < this.boardWidth; x++) {
                    Box thisBox = boxes[y][x];
                    thisBox.update();
                    Bitmap bitmap = BitmapFactory.decodeResource(getResources(), thisBox.imageID);
                    bitmap = Bitmap.createScaledBitmap(bitmap, unit_size, unit_size, true);
                    canvas.drawBitmap(bitmap, thisBox.getX(), thisBox.getY(), null);
                }
            }
            //Unlock boxes
            holder.unlockCanvasAndPost(canvas);
        }
    }

    private Box getCurBox(float curX, float curY) {
        int x = (int) ((curX - this.startX) / unit_size);
        int y = (int) ((curY - this.startY) / unit_size);
        return boxes[y][x];
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            curX = event.getX();
            curY = event.getY();
        }
        return super.onTouchEvent(event);
    }
}
