package com.example.phase1.stage2;

import android.content.res.Resources;

import com.example.phase1.Player;
import com.example.phase1.User;
import com.example.phase1.UserManager;

import java.util.ArrayList;

public class BoxesMapper {
    private Box[][] boxes;

    private int boardWidth;
    private int boardLength;
    private int unit_size;

    private double emptyBoxRate;
    private double treasureRate;
    private double trapRate;

    private int luckiness = UserManager.getInstance().getCurUser().getCurPlayer().getProperty().getLuckiness();

    public BoxesMapper(int boardWidth, int boardLength, int unit_size, int startX, int start_Y, Resources res){
        this.boardLength = boardLength;
        this.boardWidth = boardWidth;
        this.unit_size = unit_size;
        boxes = new Box[this.boardLength][this.boardWidth];
        for (int y = 0; y < this.boardLength; y++){
            for (int x = 0; x < this.boardWidth; x++) {

                setUpTheOdd();

                double decider = Math.random();
                int cur_x = startX + x * this.unit_size;
                int cur_y = start_Y + y * this.unit_size;
                Box thisBox;
                if (decider < emptyBoxRate) {
                    thisBox = new EmptyUnit(cur_x, cur_y, this.unit_size, res);
                } else if (decider < emptyBoxRate + treasureRate) {
                    thisBox = new Treasure(cur_x, cur_y, this.unit_size, res);
                } else {
                    thisBox = new Trap(cur_x, cur_y, this.unit_size, res);
                }
                boxes[y][x] = thisBox;
            }
        }
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
        for (int y = 0; y < this.boardLength; y++){
            for (int x = 0; x < this.boardWidth; x++){
                boxes[y][x].numOfNeighbourTraps = returnNumOfTrap(boxes[y][x]);
            }
        }
    }

    private void setUpTheOdd(){
        if (luckiness >= 20){
            trapRate = 0.1;
            treasureRate = 0.4;
            emptyBoxRate = 0.5;
        }
        else if (luckiness >= 10){
            trapRate = 0.15;
            treasureRate = 0.25;
            emptyBoxRate = 0.6;
        }
        else{
            trapRate = 0.2;
            treasureRate = 0.15;
            emptyBoxRate = 0.65;
        }
    }

    private int returnNumOfTrap(Box box){
        ArrayList<Box> neighbours = box.getNeighbours();
        int sum = 0;
        for (int i = 0; i < neighbours.size(); i++){
            if (neighbours.get(i) instanceof Trap){
                sum += 1;
            }
        }
        return sum;
    }

    public Box[][] getBoxes(){
        return boxes;
    }
}
