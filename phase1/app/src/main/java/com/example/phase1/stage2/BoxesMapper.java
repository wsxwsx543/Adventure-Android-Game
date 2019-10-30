package com.example.phase1.stage2;

import android.content.res.Resources;

import java.util.ArrayList;

public class BoxesMapper {
    private Box[][] boxes;

    private int boardWidth;
    private int boardLength;
    private int unit_size;

    public BoxesMapper(int boardWidth, int boardLength, int unit_size, int startX, int start_Y, Resources res){
        this.boardLength = boardLength;
        this.boardWidth = boardWidth;
        this.unit_size = unit_size;
        boxes = new Box[boardLength][boardWidth];
        for (int y = 0; y < boardLength; y++){
            for (int x = 0; x < boardWidth; x++) {
                double decider = Math.random();
                int cur_x = startX + x * unit_size;
                int cur_y = start_Y + y * unit_size;
                Box thisBox = null;
                if (decider < 0.6) {
                    thisBox = new EmptyUnit(cur_x, cur_y, unit_size, res);
                } else if (decider < 0.7) {
                    thisBox = new Treasure(cur_x, cur_y, unit_size, res);
                } else {
                    thisBox = new Trap(cur_x, cur_y, unit_size, res);
                }
                boxes[y][x] = thisBox;
            }
        }
        for (int y = 0; y < boardLength; y++){
            for (int x = 0; x < boardWidth; x++){
                if (x + 1 <= boardWidth -  1){
                    boxes[y][x].addNeighbourBox(boxes[y][x + 1]);
                }
                if (x + 1 <= boardWidth -  1 && y - 1 >= 0){
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
                if (x - 1 >= 0 && y + 1 <= boardLength - 1){
                    boxes[y][x].addNeighbourBox(boxes[y + 1][x - 1]);
                }
                if (y + 1 <= boardLength - 1){
                    boxes[y][x].addNeighbourBox(boxes[y + 1][x]);
                }
                if (x + 1 <= boardWidth - 1 && y + 1 <= boardLength - 1){
                    boxes[y][x].addNeighbourBox(boxes[y + 1][x + 1]);
                }

            }
        }
        for (int y = 0; y < boardLength; y++){
            for (int x = 0; x < boardWidth; x++){
                boxes[y][x].numOfNeighbourTraps = returnNumOfTrap(boxes[y][x]);
            }
        }



    }
    private <T>int returnNumOfTrap(Box box){
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
