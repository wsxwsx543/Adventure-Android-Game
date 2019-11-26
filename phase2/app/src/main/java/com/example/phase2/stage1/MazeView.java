package com.example.phase2.stage1;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.view.MotionEvent;
import android.view.SurfaceView;

import com.example.phase2.DataManagement.FileSystem;
import com.example.phase2.AppCoreClasses.User;
import com.example.phase2.AppCoreClasses.UserManager;
import com.example.phase2.stage2.TreasureHuntActivity;

import java.util.ArrayList;
import java.util.List;

public class MazeView extends SurfaceView implements Runnable{
    /**
     * The main Thread
     */
    private Thread thread;
    /**
     * If current game1 is playing
     */
    private boolean isPlaying;
    /**
     * the length and width of the screen
     */
    private int screenX, screenY;
    private Paint paint;
    /**
     * The player
     */
    private Hero hero;

    private String hasKey = "No";
    /**
     * The three monsters in the screen
     */
    private List<MazeObjects> myMonsters;

    /**
     * The treasure
     */
    private List<MazeObjects> myTreasures;

    /**
     * The Door
     */
    private List<MazeObjects> myDoors;


    /**
     * The background
     */
    private Background background1;

    /**
     * The current user we've selected
     */
    User curUser;
    /**
     * Where we store the data in case of data loss
     */
    private FileSystem fileSystem;
    /**
     * The paint of 4 properties and 1 life
     */
    private Paint lifePaint = new Paint();
    private Paint attackPaint = new Paint();
    private Paint defencePaint = new Paint();
    private Paint flexibilityPaint = new Paint();
    private Paint luckinessPaint = new Paint();
    private Paint keyPaint = new Paint();
    private Paint giftPaint = new Paint();

    /**
     * Four properties and life
     */
    private int attack;
    private int defence;
    private int flexibility;
    private int luckiness;
    private int life;
    private int giftLife;
    private int giftAttack;
    private int giftDefence;
    private int giftFlexibility;
    private int giftLuckiness;

    /**
     * @param context
     * @param screenX the width of the screen
     * @param screenY the length of the screen
     */
    public MazeView(Context context, int screenX, int screenY){
        super(context);

        this.screenX = screenX;
        this.screenY = screenY;

        this.myMonsters = new ArrayList<>();
        this.myTreasures = new ArrayList<>();
        this.myDoors = new ArrayList<>();

        curUser = UserManager.getInstance().getCurUser();

        attack = curUser.getCurPlayer().getProperty().getAttack();
        defence = curUser.getCurPlayer().getProperty().getDefence();
        flexibility = curUser.getCurPlayer().getProperty().getFlexibility();
        luckiness = curUser.getCurPlayer().getProperty().getLuckiness();
        life = curUser.getCurPlayer().getLivesRemain();
        giftLife = 0;
        giftAttack = 0;
        giftDefence = 0;
        giftFlexibility = 0;
        giftLuckiness = 0;

        this.fileSystem = new FileSystem(context);


        background1 = new Background(screenX, screenY, getResources());
        hero = new Hero(getResources());

        MazeObjects m1 = MazeObjectsFactory.getMazeObject("Monster", 900, 360, getResources());
        MazeObjects m2 = MazeObjectsFactory.getMazeObject("Monster", 540, 270, getResources());
        MazeObjects m3 = MazeObjectsFactory.getMazeObject("Monster", 360, 990, getResources());
        MazeObjects m4 = MazeObjectsFactory.getMazeObject("Monster", 90, 180, getResources());
        MazeObjects m5 = MazeObjectsFactory.getMazeObject("Monster", 270, 450, getResources());
        myMonsters.add(m1);
        myMonsters.add(m2);
        myMonsters.add(m3);
        myMonsters.add(m4);
        myMonsters.add(m5);


        MazeObjects t1 = MazeObjectsFactory.getMazeObject("Treasure", 720, 630, getResources());
        ((Treasure) t1).setGift("Key");
        MazeObjects t2 = MazeObjectsFactory.getMazeObject("Treasure", 90, 720, getResources());
        ((Treasure) t2).setGift("Life");
        MazeObjects t3 = MazeObjectsFactory.getMazeObject("Treasure", 540, 360, getResources());
        ((Treasure) t3).setGift("attack");
        MazeObjects t4 = MazeObjectsFactory.getMazeObject("Treasure", 180, 990, getResources());
        ((Treasure) t4).setGift("defence");
        MazeObjects t5 = MazeObjectsFactory.getMazeObject("Treasure", 630, 630, getResources());
        ((Treasure) t5).setGift("flexibility");
        MazeObjects t6 = MazeObjectsFactory.getMazeObject("Treasure", 270, 450, getResources());
        ((Treasure) t6).setGift("luckiness");

        myTreasures.add(t1);
        myTreasures.add(t2);
        myTreasures.add(t3);
        myTreasures.add(t4);
        myTreasures.add(t5);
        myTreasures.add(t6);


        MazeObjects d1 = MazeObjectsFactory.getMazeObject("Door", 990, 1350, getResources());
        myDoors.add(d1);


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

        keyPaint.setColor(Color.WHITE);
        keyPaint.setTextSize(70);
        keyPaint.setTypeface(Typeface.DEFAULT_BOLD);
        keyPaint.setAntiAlias(true);

        giftPaint.setColor(Color.WHITE);
        giftPaint.setTextSize(70);
        giftPaint.setTypeface(Typeface.DEFAULT_BOLD);
        giftPaint.setAntiAlias(true);

        curUser.getCurPlayer().setCurStage(1);
        saveUser();


    }

    public int getScreenX(){return screenX;}
    public int getScreenY(){return screenY;}

    /**
     * in where we keep running the methods
     */
    @Override
    public void run() {
        while (isPlaying){
            update();
            draw();
            sleep();
            

            for (MazeObjects monster : this.myMonsters) {
                action(monster);
            }


        }

    }

    /**
     * method to save the User data
     */
    public void saveUser() {
        fileSystem.save(UserManager.getInstance().getUsers(), "Users.ser");
    }


    /**
     * Action to move monsters
     */
    public void action(MazeObjects monster){
        double d = Math.random();
        if (d < 0.25){
            monster.setX(monster.getX()+monster.getWidth());
        } else if(0.25 <= d && d < 0.5){
            monster.setX(monster.getX()-monster.getWidth());
        } else if(0.5 <= d && d < 0.75){
            monster.setY(monster.getY()+monster.getHeight());
        } else{
            monster.setY(monster.getY()-monster.getHeight());
        }
        if (monster.getY() < 360)
            monster.setY(360);

        if (monster.getY() >= 1440)
            monster.setY(1440);

        if (monster.getX() < 0)
            monster.setX(0);

        if (monster.getX() >= screenX - monster.getWidth())
            monster.setX(screenX - monster.getWidth());
    }

    /**
     * update the x,y of monster, hte situation where player hit monsters and when to
     * jump to the next activity
     */
    private void update(){
        if (hero.getIsGoingUp()){
            hero.setY(hero.getY()-hero.getHeight());
            hero.setIsGoingUp(false);
        }

        if (hero.getIsGoingDown()){
            hero.setY(hero.getY()+hero.getHeight());
            hero.setIsGoingDown(false);
        }

        if (hero.getIsGoingLeft()){
            hero.setX(hero.getX()-hero.getWidth());
            hero.setIsGoingLeft(false);
        }

        if (hero.getIsGoingRight()){
            hero.setX(hero.getX()+hero.getWidth());
            hero.setIsGoingRight(false);
        }

        if (hero.getY() < 360)
            hero.setY(360);

        if (hero.getY() >= 1440)
            hero.setY(1440);

        if (hero.getX() < 0)
            hero.setX(0);

        if (hero.getX() >= screenX - hero.getWidth())
            hero.setX(screenX - hero.getWidth());

        for (MazeObjects monster : this.myMonsters) {
            if (hero.getX() == monster.getX() && hero.getY() == monster.getY()) {
                life--;
                if (life == 0) {
                    Intent restartg1Intent = new Intent(getContext(), MazeActivity.class);
                    getContext().startActivity(restartg1Intent);
                }
            }
        }

        for (MazeObjects treasure : this.myTreasures){
            if (hero.getX() == treasure.getX() && hero.getY() == treasure.getY()){
                if (((Treasure) treasure).getGift().equals("Life")){
                    life += 5;
                    giftLife += 5;
                    ((Treasure) treasure).setGift("Empty");
                    myTreasures.remove(treasure);
                    break;
                } else if(((Treasure) treasure).getGift().equals("attack")){
                    attack += 5;
                    giftAttack += 5;
                    ((Treasure) treasure).setGift("Empty");
                    myTreasures.remove(treasure);
                    break;
                } else if(((Treasure) treasure).getGift().equals("defence")){
                    defence += 5;
                    giftDefence += 5;
                    ((Treasure) treasure).setGift("Empty");
                    myTreasures.remove(treasure);
                    break;
                } else if(((Treasure) treasure).getGift().equals("flexibility")){
                    flexibility += 5;
                    giftFlexibility += 5;
                    ((Treasure) treasure).setGift("Empty");
                    myTreasures.remove(treasure);
                    break;
                } else if(((Treasure) treasure).getGift().equals("luckiness")){
                    luckiness += 5;
                    giftLuckiness += 5;
                    ((Treasure) treasure).setGift("Empty");
                    myTreasures.remove(treasure);
                    break;
                }
                else if (((Treasure) treasure).getGift().equals("Key")){
                    hero.setKey();
                    hasKey = "Yes";
                    ((Treasure) treasure).setGift("Empty");
                    myTreasures.remove(treasure);
                    break;
                }
            }
        }

        if (hero.getX() == myDoors.get(0).getX() && hero.getY() == myDoors.get(0).getY()
                && hero.getKey()){
            curUser.getCurPlayer().getProperty().setAttack(attack);
            curUser.getCurPlayer().getProperty().setDefence(defence);
            curUser.getCurPlayer().getProperty().setFlexibility(flexibility);
            curUser.getCurPlayer().getProperty().setLuckiness(luckiness);
            curUser.getCurPlayer().setLivesRemain(life);
            curUser.getCurPlayer().setCurStage(2);
            saveUser();

            Intent tog2Intent = new Intent(getContext(), TreasureHuntActivity.class);
            getContext().startActivity(tog2Intent);
        }
    }

    /**
     * Where to draw the bitmap background, player, treasure and monsters
     */
    private void draw(){
        if (getHolder().getSurface().isValid()){

            Canvas canvas = getHolder().lockCanvas();
            canvas.drawBitmap(background1.background, background1.x, background1.y, paint);


            canvas.drawBitmap(hero.getHero(), hero.getX(), hero.getY(), paint);

            for (MazeObjects monster : this.myMonsters) {
                canvas.drawBitmap(monster.getView(), monster.getX(), monster.getY(), paint);
            }

            for (MazeObjects treasure : this.myTreasures) {
                canvas.drawBitmap(treasure.getView(), treasure.getX(), treasure.getY(), paint);
            }

            for (MazeObjects door : this.myDoors) {
                canvas.drawBitmap(door.getView(), door.getX(), door.getY(), paint);
            }

            canvas.drawText("Life: " + life, 20, 60, lifePaint);
            canvas.drawText("Key: " + hasKey, 500, 60, keyPaint);
            canvas.drawText("Attack: " + attack, 20, 180, attackPaint);
            canvas.drawText("Defence: " + defence, 500, 180, defencePaint);
            canvas.drawText("Flexibility: " + flexibility, 20, 320, flexibilityPaint);
            canvas.drawText("Luckiness: " + luckiness, 500, 320, luckinessPaint);

            canvas.drawText("Life from the treasure: " + giftLife, 100, 1600, giftPaint);
            canvas.drawText("Attack from the treasure: " + giftAttack, 100, 1680, giftPaint);
            canvas.drawText("Defence from the treasure: " + giftDefence, 100, 1760, giftPaint);
            canvas.drawText("Flexibility from the treasure: " + giftFlexibility, 100, 1840, giftPaint);
            canvas.drawText("Luckiness from the treasure: " + giftLuckiness, 100, 1920, giftPaint);

            getHolder().unlockCanvasAndPost(canvas);

        }

    }

    /**
     * we suspend the program for 200 millis
     */
    private void sleep(){
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

    /**
     * resume the thread
     */
    public void resume(){
        isPlaying = true;
        thread = new Thread(this);
        thread.start();
    }

    /**
     * pause the thread
     */
    public void pause(){
        try {
            isPlaying = false;
            thread.join();
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    /**
     * Where we move the player
     * click the upper screen to move player upwards
     * click the lower screen to move player downwards
     * click the left screen to move player leftwards
     * click the right screen to move player rightwards
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if (event.getAction() == MotionEvent.ACTION_DOWN){
            if (event.getX() > 0 && event.getX() < screenX &&
                    event.getY() > 0 && event.getY() < screenY / 3){
                hero.setIsGoingUp(true);
            }
            if (event.getX() > 0 && event.getX() < screenX / 2 &&
                    event.getY() > screenY /3 && event.getY() < screenY * 2 /3){
                hero.setIsGoingLeft(true);
            }
            if (event.getX() > screenX / 2 && event.getX() < screenX &&
                    event.getY() > screenY /3 && event.getY() < screenY * 2 /3){
                hero.setIsGoingRight(true);
            }
            if (event.getX() > 0 && event.getX() < screenX &&
                    event.getY() > screenY * 2 / 3 && event.getY() < screenY){
                hero.setIsGoingDown(true);
            }
        }

        return true;

    }
}