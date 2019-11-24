package com.example.phase2.stage2;

import android.content.res.Resources;
import android.graphics.Canvas;

import com.example.phase2.AppCoreClasses.UserManager;

import java.util.ArrayList;

class BoxesManager {
    private Box[][] boxes;

    private int boardWidth;
    private int boardLength;
    private int unitSize;

    private double emptyBoxRate;
    private double treasureRate;
    private double trapRate;

    private int luckiness = UserManager.getInstance().getCurUser().getCurPlayer().getProperty().getLuckiness();

    BoxesManager(int boardWidth, int boardLength, int unitSize, int startX, int start_Y, Resources res){
        this.boardLength = boardLength;
        this.boardWidth = boardWidth;
        this.unitSize = unitSize;
        boxes = new Box[this.boardLength][this.boardWidth];
        BoxFactory boxFactory = new BoxFactory();
        setUpTheOdd();

        // Sets the type for every box by the chances specified above
        for (int y = 0; y < this.boardLength; y++){
            for (int x = 0; x < this.boardWidth; x++) {

                double decider = Math.random();
                int curX = startX + x * this.unitSize;
                int curY = start_Y + y * this.unitSize;
                Box thisBox;
                if (decider < emptyBoxRate) {
                    thisBox = boxFactory.createBox("EmptyUnit", curX, curY, this.unitSize, res);
                } else if (decider < emptyBoxRate + treasureRate) {
                    thisBox = boxFactory.createBox("Treasure", curX, curY, this.unitSize, res);
                } else {
                    thisBox = boxFactory.createBox("Trap", curX, curY, this.unitSize, res);
                }
                boxes[y][x] = thisBox;
            }
        }

        // Assign neighbours to all the boxes
        for (int y = 0; y < this.boardLength; y++){
            for (int x = 0; x < this.boardWidth; x++){
                if (x + 1 <= this.boardWidth -  1){
                    boxes[y][x].addNeighbourBox(boxes[y][x + 1]);
                }
                if (x + 1 <= this.boardWidth -  1 && y - 1 >= 0){
                    boxes[y][x].addNeighbourBox(boxes[y - 1][x + 1]);
                }
                if (y - 1 >= 0){
                    boxes[y][x].addNeighbourBox(boxes[y - 1][x]);
                }
                if (x - 1 >= 0 && y - 1 >= 0){
                    boxes[y][x].addNeighbourBox(boxes[y - 1][x - 1]);
                }
                if (x - 1 >= 0){
                    boxes[y][x].addNeighbourBox(boxes[y][x - 1]);
                }
                if (x - 1 >= 0 && y + 1 <= this.boardLength - 1){
                    boxes[y][x].addNeighbourBox(boxes[y + 1][x - 1]);
                }
                if (y + 1 <= this.boardLength - 1){
                    boxes[y][x].addNeighbourBox(boxes[y + 1][x]);
                }
                if (x + 1 <= this.boardWidth - 1 && y + 1 <= this.boardLength - 1){
                    boxes[y][x].addNeighbourBox(boxes[y + 1][x + 1]);
                }

            }
        }

        // Record how many neighbours each box has
        for (int y = 0; y < this.boardLength; y++){
            for (int x = 0; x < this.boardWidth; x++){
                boxes[y][x].numOfNeighbourTraps = boxes[y][x].returnNumOfTrap();
            }
        }
    }

    // Set up the odds of the type of each boxes according to the player's luckiness
    private void setUpTheOdd(){
        if (luckiness >= 8){
            trapRate = 0.1;
            treasureRate = 0.1;
            emptyBoxRate = 0.8;
        }
        else if (luckiness >= 4){
            trapRate = 0.12;
            treasureRate = 0.07;
            emptyBoxRate = 0.81;
        }
        else{
            trapRate = 0.15;
            treasureRate = 0.05;
            emptyBoxRate = 0.8;
        }
    }

    Box[][] getBoxes(){
        return boxes;
    }

    // loot the boxes
    void loot() {
        for (int y = 0; y < this.boardLength; y++) {
            for (int x = 0; x < this.boardWidth; x++) {
                if (this.boxes[y][x] instanceof Treasure) {
                    Treasure thisTreasure = (Treasure)boxes[y][x];
                    thisTreasure.loot();
                }
            }
        }

    }

    // Draw each boxes
    void draw(Canvas canvas) {
        for (int y = 0; y < this.boardLength; y++) {
            for (int x = 0; x < this.boardWidth; x++) {
                Box thisBox = boxes[y][x];
                canvas.drawBitmap(thisBox.bitmapToDraw, thisBox.getX(), thisBox.getY(), null);
            }
        }
    }

    // if all empty units and treasure are expanded return true
    boolean checkAllExpanded(){
        for (int y = 0; y < this.boardLength; y++) {
            for (int x = 0; x < this.boardWidth; x++) {
                Box thisBox = boxes[y][x];
                if (!thisBox.expanded){
                    if (thisBox instanceof EmptyUnit || thisBox instanceof Treasure){
                        return false;
                    }
                }

            }

        }
        return true;
    }

    // if any of the trap is triggered return true
    boolean checkTrapTriggered(){
        for (int y = 0; y < this.boardLength; y++) {
            for (int x = 0; x < this.boardWidth; x++) {
                if (boxes[y][x] instanceof Trap && boxes[y][x].expanded) {
                    return true;
                }
            }
        }
        return false;
    }

    // Expand the selected box
    void expand(int x, int y){
        boxes[y][x].expand(new ArrayList<>());
    }
}
