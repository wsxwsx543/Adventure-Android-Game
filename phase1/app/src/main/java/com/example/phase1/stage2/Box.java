package com.example.phase1.stage2;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.phase1.R;

import java.util.ArrayList;

public abstract  class Box {

    // The location of the Box
    private int x, y;

    int unit_size;

    protected int imageID;

    public int numOfNeighbourTraps;

    public boolean expanded;

    Resources res;

    // A reference to the neighbouring boxes
    private ArrayList<Box> neighbours;

    private Bitmap grayTile;
    public Bitmap bitmapToDraw;

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
    public Box(int x, int y, int unit_size, Resources res){
        this.res = res;
        this.x = x;
        this.y = y;
        this.unit_size = unit_size;
        this.grayTile = BitmapFactory.decodeResource(res, R.drawable.gray);
        this.grayTile = Bitmap.createScaledBitmap(this.grayTile, unit_size, unit_size, true);
        this.bitmapToDraw = this.grayTile;
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

    Bitmap getTrapsIndicatorImg(int num){
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
        Bitmap bitmap = BitmapFactory.decodeResource(res, newImageId);
        bitmap = Bitmap.createScaledBitmap(bitmap, unit_size, unit_size, true);
        return bitmap;
    }

    public ArrayList<Box> getNeighbours() {
        return neighbours;
    }

    public abstract void update();

    public void expand(ArrayList<Box> checked){
        if (!expanded) {
            this.expanded = true;
            if (this.numOfNeighbourTraps == 0 && (!(this instanceof Trap))) {
                for (int i = 0; i < this.neighbours.size(); i++) {
                    Box thisBox = this.neighbours.get(i);
                    if (!checked.contains(thisBox)) {
                        checked.add(thisBox);
                        thisBox.expand(checked);
                    }
                }
            }
        }
    }
    public boolean getExpand(){
        return expanded;
    }

}
