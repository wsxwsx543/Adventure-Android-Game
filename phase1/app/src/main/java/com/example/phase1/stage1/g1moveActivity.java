package com.example.phase1.stage1;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Point;
import android.os.Bundle;

import com.example.phase1.R;

public class g1moveActivity extends AppCompatActivity {
    private g1View myg1View;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Point point = new Point();
        getWindowManager().getDefaultDisplay().getSize(point);

        myg1View = new g1View(this, point.x, point.y);

        setContentView(myg1View);
    }

    @Override
    protected void onPause() {
        super.onPause();
        myg1View.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        myg1View.resume();
    }

    @Override
    protected void onStop() {
        super.onStop();
        myg1View.saveUser();
    }
    protected void onDestroy(){
        super.onDestroy();
        myg1View.saveUser();
    }


}
