package com.example.phase1.stage2;

import com.example.phase1.R;

public class EmptyUnit extends Box {
    public EmptyUnit(int x, int y){
        super(x, y);
    }
    public void update(){
        if (expanded){
            imageID = getTrapsIndicatorImg(numOfNeighbourTraps);
        }
    }

}