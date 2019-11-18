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

public class UoftMazeView extends SurfaceView implements Runnable{
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
    /**
     * The three monsters in the screen
     */
    private Monster[] mymonsters = {
            new Monster(720, 360, getResources()),
            new Monster(1008, 576, getResources()),
            new Monster(288, 1368, getResources()),
            new Monster(144, 864,getResources()),
            new Monster(864, 144, getResources())
    };

    /**
     * The treasure
     */
    private Treasure[] myTreasures = {new Treasure(792, 648, getResources())};
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

    /**
     * Four properties and life
     */
    private int attack;
    private int defence;
    private int flexibility;
    private int luckiness;
    private int life;

    /**
     * @param context
     * @param screenX the width of the screen
     * @param screenY the length of the screen
     */
    public UoftMazeView(Context context, int screenX, int screenY){
        super(context);

        this.screenX = screenX;
        this.screenY = screenY;

        curUser = UserManager.getInstance().getCurUser();

        attack = curUser.getCurPlayer().getProperty().getAttack();
        defence = curUser.getCurPlayer().getProperty().getDefence();
        flexibility = curUser.getCurPlayer().getProperty().getFlexibility();
        luckiness = curUser.getCurPlayer().getProperty().getLuckiness();
        life = curUser.getCurPlayer().getLivesRemain();

        this.fileSystem = new FileSystem(context);


        background1 = new Background(screenX, screenY, getResources());
        hero = new Hero(getResources());

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
            

            for (Monster monster : this.mymonsters) {
                action(monster);
            }

            for (Treasure treasure : this.myTreasures) {
                escape(treasure);
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
     * Method to move the treasure
     */
    public void escape(Treasure treasure){
        double d = Math.random();
        if (d < 0.25){
//            treasure.x += treasure.width;
            treasure.setX(treasure.getX()+treasure.getWidth());
        } else if(0.25 <= d && d < 0.5){
//            treasure.x -= treasure.width;
            treasure.setX(treasure.getX()-treasure.getWidth());
        } else if(0.5 <= d && d < 0.75){
//            treasure.y += treasure.height;
            treasure.setY(treasure.getY()+treasure.getHeight());
        } else{
//            treasure.y -= treasure.height;
            treasure.setY(treasure.getY()-treasure.getHeight());
        }
        if (treasure.getY() < 360)
            treasure.setY(360);

        if (treasure.getY() >= 1368)
            treasure.setY(1368);

        if (treasure.getX() < 0)
            treasure.setX(0);

        if (treasure.getX() >= screenX - treasure.getWidth())
            treasure.setX(screenX - treasure.getWidth());
        sleep();
    }

    /**
     * Action to move monsters
     */
    public void action(Monster monster){
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

        if (monster.getY() >= 1368)
            monster.setY(1368);

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
//            hero.y -= hero.height;
            hero.setY(hero.getY()-hero.getHeight());
            hero.setIsGoingUp(false);
        }

        if (hero.getIsGoingDown()){
//            hero.y += hero.height;
            hero.setY(hero.getY()+hero.getHeight());
            hero.setIsGoingDown(false);
        }

        if (hero.getIsGoingLeft()){
//            hero.x -= hero.width;
            hero.setX(hero.getX()-hero.getWidth());
            hero.setIsGoingLeft(false);
        }

        if (hero.getIsGoingRight()){
//            hero.x += hero.width;
            hero.setX(hero.getX()+hero.getWidth());
            hero.setIsGoingRight(false);
        }

        if (hero.getY() < 360)
            hero.setY(360);

        if (hero.getY() >= 1368)
            hero.setY(1368);

        if (hero.getX() < 0)
            hero.setX(0);

        if (hero.getX() >= screenX - hero.getWidth())
            hero.setX(screenX - hero.getWidth());

        for (Monster monster : this.mymonsters) {
            if (hero.getX() == monster.getX() && hero.getY() == monster.getY()) {
                life--;
                curUser.getCurPlayer().setLivesRemain(life);
                saveUser();
                if (life == 0) {
                    Intent restartg1Intent = new Intent(getContext(), UoftMazeActivity.class);
                    getContext().startActivity(restartg1Intent);
                }
            }
        }

        if (hero.getX() == myTreasures[0].getX() && hero.getY() == myTreasures[0].getY()){
            curUser.getCurPlayer().getProperty().setAttack(attack);
            curUser.getCurPlayer().getProperty().setDefence(defence);
            curUser.getCurPlayer().getProperty().setFlexibility(flexibility);
            curUser.getCurPlayer().getProperty().setLuckiness(luckiness);
            curUser.getCurPlayer().setLivesRemain(life);
            curUser.getCurPlayer().setCurStage(2);

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

            canvas.drawBitmap(hero.getg1hero(), hero.getX(), hero.getY(), paint);

            for (Monster monster : this.mymonsters) {
                canvas.drawBitmap(monster.getMonsterView(), monster.getX(), monster.getY(), paint);
            }

            for (Treasure treasure : this.myTreasures) {
                canvas.drawBitmap(treasure.getTreasurerview(), treasure.getX(), treasure.getY(), paint);
            }

            canvas.drawText("Life: " + life, 20, 60, lifePaint);
            canvas.drawText("Attack: " + attack, 20, 180, attackPaint);
            canvas.drawText("Defence: " + defence, 500, 180, defencePaint);
            canvas.drawText("Flexibility: " + flexibility, 20, 320, flexibilityPaint);
            canvas.drawText("Luckiness: " + luckiness, 500, 320, luckinessPaint);

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