package com.example.phase2.stage1;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.view.MotionEvent;
import android.view.SurfaceView;

import com.example.phase2.datamanagement.FileSystem;
import com.example.phase2.appcore.User;
import com.example.phase2.appcore.UserManager;
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
    private Paint paint = new Paint();
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
    private Paint textPaint = new Paint();

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

        createMonsterItems();
        createTreasureItems();
        createDoorItems();

        setTextPaint();

        curUser = UserManager.getInstance().getCurUser();

        fileSystem = new FileSystem(context);
        background1 = new Background(screenX, screenY, getResources());
        hero = new Hero(getResources());

        attack = curUser.getCurPlayer().getProperty().getAttack();
        defence = curUser.getCurPlayer().getProperty().getDefence();
        flexibility = curUser.getCurPlayer().getProperty().getFlexibility();
        luckiness = curUser.getCurPlayer().getProperty().getLuckiness();
        life = curUser.getCurPlayer().getLivesRemain();

        curUser.getCurPlayer().setCurStage(1);
        saveUser();


    }

    public int getScreenX(){return screenX;}
    public int getScreenY(){return screenY;}

    public void setTextPaint(){
        textPaint.setColor(Color.WHITE);
        textPaint.setTextSize(70);
        textPaint.setTypeface(Typeface.DEFAULT_BOLD);
        textPaint.setAntiAlias(true);
    }

    public void createMonsterItems(){
        MazeObjects m1 = MazeObjectsFactory.getMazeObject("Monster", 900, 360, getResources(), "Strong");
        MazeObjects m2 = MazeObjectsFactory.getMazeObject("Monster", 540, 270, getResources(), "Weak");
        MazeObjects m3 = MazeObjectsFactory.getMazeObject("Monster", 360, 990, getResources(), "Weak");
        MazeObjects m4 = MazeObjectsFactory.getMazeObject("Monster", 90, 180, getResources(), "Weak");
        MazeObjects m5 = MazeObjectsFactory.getMazeObject("Monster", 270, 450, getResources(), "Weak");
        myMonsters.add(m1);
        myMonsters.add(m2);
        myMonsters.add(m3);
        myMonsters.add(m4);
        myMonsters.add(m5);
    }

    public void createTreasureItems(){
        MazeObjects t1 = MazeObjectsFactory.getMazeObject("Treasure", 720, 630, getResources(), "Key");
        MazeObjects t2 = MazeObjectsFactory.getMazeObject("Treasure", 90, 720, getResources(), "Life");
        MazeObjects t3 = MazeObjectsFactory.getMazeObject("Treasure", 540, 360, getResources(), "Attack");
        MazeObjects t4 = MazeObjectsFactory.getMazeObject("Treasure", 180, 990, getResources(), "Defence");
        MazeObjects t5 = MazeObjectsFactory.getMazeObject("Treasure", 630, 630, getResources(), "Flexibility");
        MazeObjects t6 = MazeObjectsFactory.getMazeObject("Treasure", 270, 450, getResources(), "Luckiness");

        myTreasures.add(t1);
        myTreasures.add(t2);
        myTreasures.add(t3);
        myTreasures.add(t4);
        myTreasures.add(t5);
        myTreasures.add(t6);
    }

    public void createDoorItems(){
        MazeObjects d1 = MazeObjectsFactory.getMazeObject("Door", 990, 1350, getResources(), "True");
        MazeObjects d2 = MazeObjectsFactory.getMazeObject("Door", 90, 1350, getResources(), "False");
        myDoors.add(d1);
        myDoors.add(d2);
    }

    /**
     * in where we keep running the methods
     */
    @Override
    public void run() {
        while (isPlaying){
            update();
            draw();
            sleep();
            for (MazeObjects monster : this.myMonsters) { action(monster);}
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
        updateHero();
        updateMonster();
        updateTreasure();
        updateDoor();
    }

    public void updateHero(){
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
    }

    public void updateMonster(){
        for (MazeObjects monster : this.myMonsters) {
            if (monster.getType().equals("Strong")){
                if (hero.getX() == monster.getX() && hero.getY() == monster.getY()) {
                    life -= 5;
                    if (life <= 0) {
                        Intent restartg1Intent = new Intent(getContext(), MazeActivity.class);
                        getContext().startActivity(restartg1Intent);
                    }
                }
            }
            else if (monster.getType().equals("Weak")){
                if (hero.getX() == monster.getX() && hero.getY() == monster.getY()) {
                    life --;
                    if (life <= 0) {
                        Intent restartg1Intent = new Intent(getContext(), MazeActivity.class);
                        getContext().startActivity(restartg1Intent);
                    }
                }
            }
        }
    }

    public void updateTreasure(){
        for (MazeObjects treasure : this.myTreasures){
            if (hero.getX() == treasure.getX() && hero.getY() == treasure.getY()){
                if (treasure.getType().equals("Life")){
                    life += 5;
                    giftLife += 5;
                    treasure.setType("Empty");
                    myTreasures.remove(treasure);
                    break;
                } else if(treasure.getType().equals("Attack")){
                    attack += 5;
                    giftAttack += 5;
                    treasure.setType("Empty");
                    myTreasures.remove(treasure);
                    break;
                } else if(treasure.getType().equals("Defence")){
                    defence += 5;
                    giftDefence += 5;
                    treasure.setType("Empty");
                    myTreasures.remove(treasure);
                    break;
                } else if(treasure.getType().equals("Flexibility")){
                    flexibility += 5;
                    giftFlexibility += 5;
                    treasure.setType("Empty");
                    myTreasures.remove(treasure);
                    break;
                } else if(treasure.getType().equals("Luckiness")){
                    luckiness += 5;
                    giftLuckiness += 5;
                    treasure.setType("Empty");
                    myTreasures.remove(treasure);
                    break;
                }
                else if (treasure.getType().equals("Key")){
                    hero.setKey();
                    hasKey = "Yes";
                    treasure.setType("Empty");
                    myTreasures.remove(treasure);
                    break;
                }
            }
        }
    }

    public void updateDoor(){
        for (MazeObjects door : myDoors){
            if (door.getType().equals("True")){
                if (hero.getX() == door.getX() && hero.getY() == door.getY() && hero.getKey()){
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
            else if (door.getType().equals("False")){
                if (hero.getX() == door.getX() && hero.getY() == door.getY() && hero.getKey()){
                    life -= 5;
                    door.setType("Used");
                    myDoors.remove(door);
                    break;
                }
            }
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

            canvas.drawText("Life: " + life, 20, 60, textPaint);
            canvas.drawText("Key: " + hasKey, 500, 60, textPaint);
            canvas.drawText("Attack: " + attack, 20, 180, textPaint);
            canvas.drawText("Defence: " + defence, 500, 180, textPaint);
            canvas.drawText("Flexibility: " + flexibility, 20, 320, textPaint);
            canvas.drawText("Luckiness: " + luckiness, 500, 320, textPaint);

            canvas.drawText("Life from the treasure: " + giftLife, 100, 1600, textPaint);
            canvas.drawText("Attack from the treasure: " + giftAttack, 100, 1680, textPaint);
            canvas.drawText("Defence from the treasure: " + giftDefence, 100, 1760, textPaint);
            canvas.drawText("Flexibility from the treasure: " + giftFlexibility, 100, 1840, textPaint);
            canvas.drawText("Luckiness from the treasure: " + giftLuckiness, 100, 1920, textPaint);

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