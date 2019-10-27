package com.example.phase1.stage1;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceView;

public class g1View extends SurfaceView implements Runnable{

    private Thread thread;
    private boolean isPlaying;
    private int screenX, screenY;
    private Paint paint;
    private g1hero hero;
    private g1Monster monster;
    private g1background background1;

    public g1View(Context context, int screenX, int screenY){
        super(context);

        this.screenX = screenX;
        this.screenY = screenY;

        background1 = new g1background(screenX, screenY, getResources());
        hero = new g1hero(screenY, getResources());
        monster = new g1Monster(this, screenY, getResources());

        paint = new Paint();
    }

    public int getScreenX(){return screenX;}
    public int getScreenY(){return screenY;}

    @Override
    public void run() {
        while (isPlaying){
            update();
            draw();
            action();
//            sleep();

        }

    }

    public void action(){
        double d = Math.random();
        if (d < 0.25){
            monster.x += monster.width;
        } else if(0.25 <= d && d < 0.5){
            monster.x -= monster.width;
        } else if(0.5 <= d && d < 0.75){
            monster.y += monster.height;
        } else{
            monster.y -= monster.height;
        }
        if (monster.y < 360)
            monster.y = 360;

        if (monster.y >= 1368)
            monster.y = 1368;

        if (monster.x < 0)
            monster.x = 0;

        if (monster.x >= screenX - monster.width)
            monster.x = screenX - monster.width;
        sleep();
    }

    private void update(){
        if (hero.isGoingUp){
            hero.y -= hero.height;
            hero.isGoingUp = false;
        }

        if (hero.isGoingdown){
            hero.y += hero.height;
            hero.isGoingdown = false;
        }

        if (hero.isGoingLeft){
            hero.x -= hero.width;
            hero.isGoingLeft = false;
        }

        if (hero.isGoingRight){
            hero.x += hero.width;
            hero.isGoingRight = false;
        }

        if (hero.y < 360)
            hero.y = 360;

        if (hero.y >= 1368)
            hero.y = 1368;

        if (hero.x < 0)
            hero.x = 0;

        if (hero.x >= screenX - hero.width)
            hero.x = screenX - hero.width;
    }

    private void draw(){
        if (getHolder().getSurface().isValid()){

            Canvas canvas = getHolder().lockCanvas();
            canvas.drawBitmap(background1.background, background1.x, background1.y, paint);

            canvas.drawBitmap(hero.getg1hero(), hero.x, hero.y, paint);
            canvas.drawBitmap(monster.getMonsterView(), monster.x, monster.y, paint);

            getHolder().unlockCanvasAndPost(canvas);

        }



    }

    private void sleep(){
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

    public void resume(){
        isPlaying = true;
        thread = new Thread(this);
        thread.start();
    }

    public void pause(){
        try {
            isPlaying = false;
            thread.join();
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if (event.getAction() == MotionEvent.ACTION_DOWN){
            if (event.getX() > 0 && event.getX() < screenX &&
                    event.getY() > 0 && event.getY() < screenY / 3){
                hero.isGoingUp = true;
            }
            if (event.getX() > 0 && event.getX() < screenX / 2 &&
                    event.getY() > screenY /3 && event.getY() < screenY * 2 /3){
                hero.isGoingLeft = true;
            }
            if (event.getX() > screenX / 2 && event.getX() < screenX &&
                    event.getY() > screenY /3 && event.getY() < screenY * 2 /3){
                hero.isGoingRight = true;
            }
            if (event.getX() > 0 && event.getX() < screenX &&
                    event.getY() > screenY * 2 / 3 && event.getY() < screenY){
                hero.isGoingdown = true;
            }
        }

        return true;

    }
}
