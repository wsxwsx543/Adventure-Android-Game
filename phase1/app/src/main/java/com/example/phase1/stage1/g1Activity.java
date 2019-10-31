package com.example.phase1.stage1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;

import java.util.Timer;
import java.util.TimerTask;

import android.graphics.drawable.Drawable;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.phase1.R;


//ideas come from
//https://www.youtube.com/watch?v=rBVU9KBRRe8&list=PLteuIswuFU6aYjdjd6oKwId4tfxa4TSF9&index=2&t=0s
//how to move button move character

public class g1Activity extends AppCompatActivity implements View.OnTouchListener{
    private ImageView box;
    private ImageView mos;

    private RelativeLayout frame;
    private Drawable imageBoxRight, imageBoxLeft, imageBoxFront, imageBoxBack;
    private Drawable mosImage;
    private float boxX, boxY;
    private float mosX, mosY;

    private boolean action_up, action_down, action_right, action_left;

    private TextView gpa;
    private double actual_gpa;

    private Timer timer = new Timer();
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_g1);


        box = findViewById(R.id.box);
        mos = findViewById(R.id.mos);
        frame = findViewById(R.id.frame);

        imageBoxRight = getResources().getDrawable(R.drawable.g1_player_right);
        imageBoxLeft = getResources().getDrawable(R.drawable.g1_player_left);
        imageBoxFront = getResources().getDrawable(R.drawable.g1_player_front);
        imageBoxBack = getResources().getDrawable(R.drawable.g1_player_back);

        mosImage = getResources().getDrawable(R.drawable.g1_utoronto);

        gpa = findViewById(R.id.your_gpa);
        actual_gpa = 4.0;



        findViewById(R.id.upBtn).setOnTouchListener(this);
        findViewById(R.id.downBtn).setOnTouchListener(this);
        findViewById(R.id.leftBtn).setOnTouchListener(this);
        findViewById(R.id.rightBtn).setOnTouchListener(this);


        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        changePos();
                    }
                });
            }
        }, 0, 20);


    }

    public void changePos(){
        boxX = box.getX();
        boxY = box.getY();

        mosX = mos.getX();
        mosY = mos.getY();

        if (action_up){
            boxY -= box.getHeight();
            box.setImageDrawable(imageBoxBack);
            action_up = false;
        }

        if (action_down){
            boxY += box.getHeight();
            box.setImageDrawable(imageBoxFront);
            action_down = false;
        }

        if (action_left){
            boxX -= box.getWidth();
            box.setImageDrawable(imageBoxLeft);
            action_left = false;
        }

        if (action_right){
            boxX += box.getWidth();
            box.setImageDrawable(imageBoxRight);
            action_right = false;
        }

        if (boxY < 0) boxY = 0;
        if (boxY > frame.getHeight() - box.getHeight()) boxY = frame.getHeight() - box.getHeight();

        if (boxX < 0) {
            boxX = 0;

        }
        if (boxX > frame.getWidth() - box.getWidth()) {
            boxX = frame.getWidth() - box.getWidth();

        }

        gpa.setText("Your GPA :" + actual_gpa);


        box.setX(boxX);
        box.setY(boxY);
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (motionEvent.getAction() == MotionEvent.ACTION_DOWN){
            switch (view.getId()){
                case R.id.upBtn:
                    action_up = true;
                    break;
                case R.id.downBtn:
                    action_down = true;
                    break;
                case R.id.leftBtn:
                    action_left = true;
                    break;
                case R.id.rightBtn:
                    action_right = true;
                    break;
            }
        } else {
            action_up = false;
            action_down = false;
            action_right = false;
            action_left = false;
        }
        return true;
    }

}
