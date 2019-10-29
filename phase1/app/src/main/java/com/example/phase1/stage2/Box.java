package com.example.phase1.stage2;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.phase1.R;

import java.util.ArrayList;

public abstract  class Box {

    // The location of the Box
    private int x, y;

    protected int imageID;

    public int numOfNeighbourTraps;

    public boolean expanded;

    public ArrayList<Box> getNeighbours() {
        return neighbours;
    }

    // A reference to the neighbouring boxes
    private ArrayList<Box> neighbours;

    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    private void setLocation(int x, int y){
        this.x = x;
        this.y = y;
    }
    public Box(int x, int y){
        this.x = x;
        this.y = y;
        this.imageID = R.drawable.gray;
        this.expanded = false;
        neighbours = new ArrayList<>();
    }

    public void addNeighbourBox(Box box){
        this.neighbours.add(box);
    }

    int returnNumOfTrap(){
        int sum = 0;
        for (int i = 0; i < neighbours.size(); i++){
            Box thisBox = neighbours.get(i);
            if (thisBox instanceof Trap){
                sum += 1;
            }
        }
        return sum;
    }

    int getTrapsIndicatorImg(int num){
        int newImageId = 0;
        switch (num){
            case 0:
                newImageId = R.drawable.open0;
                break;
            case 1:
                newImageId = R.drawable.open1;
                break;
            case 2:
                newImageId = R.drawable.open2;
                break;
            case 3:
                newImageId = R.drawable.open3;
                break;
            case 4:
                newImageId = R.drawable.open4;
                break;
            case 5:
                newImageId = R.drawable.open5;
                break;
            case 6:
                newImageId = R.drawable.open6;
                break;
            case 7:
                newImageId = R.drawable.open7;
                break;
            case 8:
                newImageId = R.drawable.open8;
                break;
        }
        return newImageId;
    }
    public abstract void update();
    public void expand(ArrayList<Box> checked){
        this.expanded = true;
        if (this.numOfNeighbourTraps == 0){
            for (int i = 0; i < this.neighbours.size(); i++){
                Box thisBox = this.neighbours.get(i);
                if (!checked.contains(thisBox)){
                    checked.add(thisBox);
                    thisBox.expand(checked);
                }
            }
        }
    }
}
