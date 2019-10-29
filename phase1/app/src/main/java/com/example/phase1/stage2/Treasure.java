package com.example.phase1.stage2;

import com.example.phase1.R;

public class Treasure extends Box {
    public Treasure(int x, int y){
        super(x, y);
    }
    public void update(){
        if (expanded){
            imageID = R.drawable.baoxiang2;
        }
    }
}
