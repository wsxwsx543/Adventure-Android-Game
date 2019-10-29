package com.example.phase1.stage2;

import com.example.phase1.R;

public class Trap extends Box {
    public Trap(int x, int y){
        super(x, y);
    }

    public void update(){
        if (expanded){
            imageID = R.drawable.trap;
        }
    }
}
