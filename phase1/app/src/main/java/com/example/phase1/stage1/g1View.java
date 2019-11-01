package com.example.phase1.stage1;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.view.MotionEvent;
import android.view.SurfaceView;

import com.example.phase1.FileSystem;
import com.example.phase1.User;
import com.example.phase1.UserManager;
import com.example.phase1.stage2.TreasureHuntActivity;

public class g1View extends SurfaceView implements Runnable{

    private Thread thread;
    private boolean isPlaying;
    private int screenX, screenY;
    private Paint paint;
    private g1hero hero;
    private g1Monster monster1;
    private g1Monster monster2;
    private g1Monster monster3;
    private g1Treasure treasure;
    private g1background background1;

    User curUser;
    private FileSystem fileSystem;

    private Paint lifePaint = new Paint();
    private Paint attackPaint = new Paint();
    private Paint defencePaint = new Paint();
    private Paint flexibilityPaint = new Paint();
    private Paint luckinessPaint = new Paint();
    private Paint weaponPaint = new Paint();

    private int attack;
    private int defence;
    private int flexibility;
    private int luckiness;
    private String weapon;

    private int life;

    public g1View(Context context, int screenX, int screenY){
        super(context);

        this.screenX = screenX;
        this.screenY = screenY;

        curUser = UserManager.getInstance().getCurUser();
//        curUser.getCurPlayer();

        attack = curUser.getCurPlayer().getProperty().getAttack();
        defence = curUser.getCurPlayer().getProperty().getDefence();
        flexibility = curUser.getCurPlayer().getProperty().getFlexibility();
        luckiness = curUser.getCurPlayer().getProperty().getLuckiness();
        life = curUser.getCurPlayer().getLivesRemain();

        this.fileSystem = new FileSystem(context);


        background1 = new g1background(screenX, screenY, getResources());
        hero = new g1hero(screenY, getResources());
        monster1 = new g1Monster(720, 360,this, screenY, getResources());
        monster2 = new g1Monster(1008, 576,this, screenY, getResources());
        monster3 = new g1Monster(288, 1368,this, screenY, getResources());
        treasure = new g1Treasure(this, screenY, getResources());

        paint = new Paint();

        lifePaint.setColor(Color.WHITE);
        lifePaint.setTextSize(70);
        lifePaint.setTypeface(Typeface.DEFAULT_BOLD);
        lifePaint.setAntiAlias(true);

        attackPaint.setColor(Color.WHITE);
        attackPaint.setTextSize(70);
        attackPaint.setTypeface(Typeface.DEFAULT_BOLD);
        attackPaint.setAntiAlias(true);

        defencePaint.setColor(Color.WHITE);
        defencePaint.setTextSize(70);
        defencePaint.setTypeface(Typeface.DEFAULT_BOLD);
        defencePaint.setAntiAlias(true);

        flexibilityPaint.setColor(Color.WHITE);
        flexibilityPaint.setTextSize(70);
        flexibilityPaint.setTypeface(Typeface.DEFAULT_BOLD);
        flexibilityPaint.setAntiAlias(true);

        luckinessPaint.setColor(Color.WHITE);
        luckinessPaint.setTextSize(70);
        luckinessPaint.setTypeface(Typeface.DEFAULT_BOLD);
        luckinessPaint.setAntiAlias(true);

        weaponPaint.setColor(Color.WHITE);
        weaponPaint.setTextSize(70);
        weaponPaint.setTypeface(Typeface.DEFAULT_BOLD);
        weaponPaint.setAntiAlias(true);

//        life = 10;

    }

    public int getScreenX(){return screenX;}
    public int getScreenY(){return screenY;}

    @Override
    public void run() {
        while (isPlaying){
            update();
            draw();
            action1();
            action2();
            action3();
            escape();
//            sleep();

        }

    }

    public void saveUser() {
        fileSystem.save(UserManager.getInstance().getUsers(), "Users.ser");
    }

    public void escape(){
        double d = Math.random();
        if (d < 0.25){
            treasure.x += treasure.width;
        } else if(0.25 <= d && d < 0.5){
            treasure.x -= treasure.width;
        } else if(0.5 <= d && d < 0.75){
            treasure.y += treasure.height;
        } else{
            treasure.y -= treasure.height;
        }
        if (treasure.y < 360)
            treasure.y = 360;

        if (treasure.y >= 1368)
            treasure.y = 1368;

        if (treasure.x < 0)
            treasure.x = 0;

        if (treasure.x >= screenX - treasure.width)
            treasure.x = screenX - treasure.width;
        sleep();
    }

    public void action1(){
        double d = Math.random();
        if (d < 0.25){
            monster1.x += monster1.width;
        } else if(0.25 <= d && d < 0.5){
            monster1.x -= monster1.width;
        } else if(0.5 <= d && d < 0.75){
            monster1.y += monster1.height;
        } else{
            monster1.y -= monster1.height;
        }
        if (monster1.y < 360)
            monster1.y = 360;

        if (monster1.y >= 1368)
            monster1.y = 1368;

        if (monster1.x < 0)
            monster1.x = 0;

        if (monster1.x >= screenX - monster1.width)
            monster1.x = screenX - monster1.width;

    }

    public void action2(){
        double d = Math.random();
        if (d < 0.25){
            monster2.x += monster2.width;
        } else if(0.25 <= d && d < 0.5){
            monster2.x -= monster2.width;
        } else if(0.5 <= d && d < 0.75){
            monster2.y += monster2.height;
        } else{
            monster2.y -= monster2.height;
        }
        if (monster2.y < 360)
            monster2.y = 360;

        if (monster2.y >= 1368)
            monster2.y = 1368;

        if (monster2.x < 0)
            monster2.x = 0;

        if (monster2.x >= screenX - monster2.width)
            monster2.x = screenX - monster2.width;

    }

    public void action3(){
        double d = Math.random();
        if (d < 0.25){
            monster3.x += monster3.width;
        } else if(0.25 <= d && d < 0.5){
            monster3.x -= monster3.width;
        } else if(0.5 <= d && d < 0.75){
            monster3.y += monster3.height;
        } else{
            monster3.y -= monster3.height;
        }
        if (monster3.y < 360)
            monster3.y = 360;

        if (monster3.y >= 1368)
            monster3.y = 1368;

        if (monster3.x < 0)
            monster3.x = 0;

        if (monster3.x >= screenX - monster3.width)
            monster3.x = screenX - monster3.width;
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

        if (hero.x == monster1.x && hero.y == monster1.y){
            life --;
            if (life == 0){
                Intent restartg1Intent = new Intent(getContext(), g1moveActivity.class);
                getContext().startActivity(restartg1Intent);
            }
        }

        if (hero.x == monster2.x && hero.y == monster2.y){
            life --;
            if (life == 0){
                Intent restartg1Intent = new Intent(getContext(), g1moveActivity.class);
                getContext().startActivity(restartg1Intent);
            }
        }

        if (hero.x == monster3.x && hero.y == monster3.y){
            life --;
            if (life == 0){
                Intent restartg1Intent = new Intent(getContext(), g1moveActivity.class);
                getContext().startActivity(restartg1Intent);
            }
        }

        if (hero.x == treasure.x && hero.y == treasure.y){
            curUser.getCurPlayer().getProperty().setAttack(attack);
            curUser.getCurPlayer().getProperty().setDefence(defence);
            curUser.getCurPlayer().getProperty().setFlexibility(flexibility);
            curUser.getCurPlayer().getProperty().setLuckiness(luckiness);
            curUser.getCurPlayer().setLivesRemain(life);
            curUser.getCurPlayer().setCurStage(1);

            Intent tog2Intent = new Intent(getContext(), TreasureHuntActivity.class);
            getContext().startActivity(tog2Intent);
        }
    }

    private void draw(){
        if (getHolder().getSurface().isValid()){

            Canvas canvas = getHolder().lockCanvas();
            canvas.drawBitmap(background1.background, background1.x, background1.y, paint);

            canvas.drawBitmap(hero.getg1hero(), hero.x, hero.y, paint);
            canvas.drawBitmap(monster1.getMonsterView(), monster1.x, monster1.y, paint);
            canvas.drawBitmap(monster2.getMonsterView(), monster2.x, monster2.y, paint);
            canvas.drawBitmap(monster3.getMonsterView(), monster3.x, monster3.y, paint);
            canvas.drawBitmap(treasure.getTreasurerview(), treasure.x, treasure.y, paint);

            canvas.drawText("Life: " + life, 20, 60, lifePaint);
            canvas.drawText("Attack: " + attack, 20, 180, attackPaint);
            canvas.drawText("Defence: " + defence, 500, 180, defencePaint);
            canvas.drawText("Flexibility: " + flexibility, 20, 320, flexibilityPaint);
            canvas.drawText("Luckiness: " + luckiness, 500, 320, luckinessPaint);
            canvas.drawText("Weapon: " + "guess what", 400, 60, luckinessPaint);

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
