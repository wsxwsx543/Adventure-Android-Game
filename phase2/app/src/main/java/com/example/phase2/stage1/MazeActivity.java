package com.example.phase2.stage1;

import android.graphics.Point;
import android.os.Bundle;

import com.example.phase2.usersystem.views.app.SuperActivity;


public class MazeActivity extends SuperActivity {
    /**
     * The game view we are gonna present
     */
    private MazeView myg1View;

    /**
     * Every time intent to this activity, we jump to the corresponding
     * game view
     *
     * @param savedInstanceState save the instance state.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.init();


        Point point = new Point();
        getWindowManager().getDefaultDisplay().getSize(point);

        myg1View = new MazeView(this, point.x, point.y);

        setContentView(myg1View);
    }

    /**
     * pause the game view
     */
    @Override
    protected void onPause() {
        super.onPause();
        myg1View.pause();
    }

    /**
     * resume the game view
     */
    @Override
    protected void onResume() {
        super.onResume();
        myg1View.resume();
    }


}
