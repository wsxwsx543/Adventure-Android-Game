package com.example.phase1.stage2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;

public class TreasureHuntView extends SurfaceView implements Runnable {

    private Paint paint;
    public float curX = -1, curY = -1;
    public Box[][] boxes;
    private BoxesMapper boxesMapper;
    private ArrayList<Bitmap> boxesBitmap;
    private int boardWidth, boardLength, startX, startY;
    private int unit_size;
    public Thread thread;
    public SurfaceHolder holder;
    public boolean running;


    //Temp var

    private boolean switcher = false;

    public TreasureHuntView(Context context, int boardWidth, int boardLength, int unit_size, int startX, int startY) {
        super(context);

        this.paint = new Paint();
        this.holder = getHolder();
        this.boardWidth = boardWidth;
        this.boardLength = boardLength;
        this.startX = startX;
        this.startY = startY;
        this.unit_size = unit_size;

        boxesMapper = new BoxesMapper(this.boardWidth, this.boardLength, this.unit_size, this.startX, this.startY, getResources());
        boxes = boxesMapper.getBoxes();
    }

    public TreasureHuntView(Context context) {
        this(context, 10, 15, 108, 0, 200);
    }

    public void pause() {
        try {
            running = false;
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void resume() {
        running = true;
        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        while (running) {
            //Lock canvas so we can draw

            if (curX != -1 && curY != -1) {
                int[] pair = getCurBoxLoc(curX, curY);
                if (register(pair)){
                    boxes[pair[1]][pair[0]].expand(new ArrayList<>());
                }
            }

            //update boxes
            update();
            //Draw the boxes
            draw();

        }
    }

    private int[] getCurBoxLoc(float curX, float curY) {
        int[] pair = new int[2];
        int x = (int) ((curX - this.startX) / unit_size);
        int y = (int) ((curY - this.startY) / unit_size);
        pair[0] = x;
        pair[1] = y;
        return pair;
    }
    private boolean register(int[] pair){
        if (pair[0] >= 0 && pair[0] <= boardWidth - 1&& pair[1] >= 0 && pair[1] <= boardLength - 1){
            return true;
        }
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            curX = event.getX();
            curY = event.getY();
        }
        return true;
    }

    private void update() {
        for (int y = 0; y < this.boardLength; y++) {
            for (int x = 0; x < this.boardWidth; x++) {
                boxes[y][x].update();
            }
        }
    }

    private void draw(){
        if (holder.getSurface().isValid()) {
            Canvas canvas = getHolder().lockCanvas();
            // Draw background
            canvas.drawARGB(255, 255, 255, 255);

            for (int y = 0; y < this.boardLength; y++) {
                for (int x = 0; x < this.boardWidth; x++) {
                    Box thisBox = boxes[y][x];
                    canvas.drawBitmap(thisBox.bitmapToDraw, thisBox.getX(), thisBox.getY(), null);
                }
            }
            holder.unlockCanvasAndPost(canvas);
        }
    }
}
