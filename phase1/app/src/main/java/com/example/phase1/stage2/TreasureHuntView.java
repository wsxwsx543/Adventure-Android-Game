package com.example.phase1.stage2;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.example.phase1.FileSystem;
import com.example.phase1.Player;
import com.example.phase1.Property;
import com.example.phase1.User;
import com.example.phase1.UserManager;
import com.example.phase1.stage3.BattleActivity;

import java.util.ArrayList;

public class TreasureHuntView extends SurfaceView implements Runnable {

    //Paint for text
    Paint textPaint;

    //Cursor location
    public double curX = -1, curY = -1;

    //Current user
    private User user = UserManager.getInstance().getCurUser();
    private Property property = user.getCurPlayer().getProperty();
    private Player player = user.getCurPlayer();

    public Box[][] boxes;
    private BoxesMapper boxesMapper;
    private ArrayList<Bitmap> boxesBitmap;
    private int boardWidth, boardLength, startX, startY;
    private int unit_size;
    public Thread thread;
    public SurfaceHolder holder;
    public boolean running;
    private FileSystem fileSystem;

    //Temp var

    public TreasureHuntView(Context context, int boardWidth, int boardLength, int unit_size, int startX, int startY) {
        super(context);

        this.textPaint = new Paint();

        textPaint.setColor(Color.rgb(255, 215, 0));
        textPaint.setTextSize(70);
        textPaint.setTypeface(Typeface.DEFAULT_BOLD);
        textPaint.setAntiAlias(true);

        this.fileSystem = new FileSystem(context);
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
        this(context, 15, 18, 72, 0, 300);
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
                if (register(pair)) {
                    boxes[pair[1]][pair[0]].expand(new ArrayList<>());
                }
                curX = -1;
                curY = -1;
            }

            //update boxes
            update();
            //Draw the boxes
            draw();
            //Check if trap is triggered
            checkEnded();

        }
    }

    private int[] getCurBoxLoc(double curX, double curY) {
        int[] pair = new int[2];
        int x = (int) ((curX - this.startX) / unit_size);
        int y = (int) ((curY - this.startY) / unit_size);
        pair[0] = x;
        pair[1] = y;
        return pair;
    }

    private boolean register(int[] pair) {
        if (pair[0] >= 0 && pair[0] <= boardWidth - 1 && pair[1] >= 0 && pair[1] <= boardLength - 1) {
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

    private void checkEnded() {
        for (int y = 0; y < this.boardLength; y++) {
            for (int x = 0; x < this.boardWidth; x++) {
                if (boxes[y][x] instanceof Trap && boxes[y][x].expanded) {
                    try {
                        thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Intent switchToStage3 = new Intent(getContext(), BattleActivity.class);
                    getContext().startActivity(switchToStage3);
                }
            }
        }
    }

    public void saveUser() {
        fileSystem.save(user, "Users.ser");
    }

    private void draw() {
        if (holder.getSurface().isValid()) {
            Canvas canvas = getHolder().lockCanvas();
            // Draw background
            canvas.drawARGB(255, 255, 255, 255);
            // Draw the stats of the player
            canvas.drawText("Attack: " + property.getAttack(), 20, (float) (startY + boardLength * unit_size + 100), textPaint);
            canvas.drawText("Defence: " + property.getDefence(), 500, (float) (startY + boardLength * unit_size + 100), textPaint);
            canvas.drawText("Flexibility: " + property.getFlexibility(), 20, (float) (startY + boardLength * unit_size + 200), textPaint);
            canvas.drawText("Luckiness: " + property.getLuckiness(), 500, (float) (startY + boardLength * unit_size + 200), textPaint);

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
