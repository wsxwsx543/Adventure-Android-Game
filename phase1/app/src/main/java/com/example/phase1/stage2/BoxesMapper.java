package com.example.phase1.stage2;

import java.util.ArrayList;

public class BoxesMapper {
    private Box[][] boxes;

    public BoxesMapper(int board_size, int unit_size){
        boxes = new Box[board_size][board_size];
        for (int y = 0; y < board_size; y++){
            for (int x = 0; x < board_size; x++) {
                double decider = Math.random();
                Unit unit;
                if (decider < 0.6) {
                    unit = new EmptyUnit();
                } else if (decider < 0.7) {
                    unit = new Treasure();
                } else {
                    unit = new Trap();
                }
                boxes[x][y] = new Box(unit, x * unit_size, y * unit_size);
            }
        }
        for (int y = 0; y < board_size; y++){
            for (int x = 0; x < board_size; x++){
                if (x + 1 <= board_size -  1){
                    boxes[x][y].addNeighbourBox(boxes[x + 1][y]);
                }
                if (x + 1 <= board_size -  1 && y - 1 >= 0){
                    boxes[x][y].addNeighbourBox(boxes[x + 1][y - 1]);
                }
                if (y - 1 >= 0){
                    boxes[x][y].addNeighbourBox(boxes[x][y - 1]);
                }
                if (x - 1 >= 0 && y - 1 >= 0){
                    boxes[x][y].addNeighbourBox(boxes[x - 1][y - 1]);
                }
                if (x - 1 >= 0){
                    boxes[x][y].addNeighbourBox(boxes[x - 1][y]);
                }
                if (x - 1 >= 0 && y + 1 <= board_size - 1){
                    boxes[x][y].addNeighbourBox(boxes[x - 1][y + 1]);
                }
                if (y + 1 <= board_size - 1){
                    boxes[x][y].addNeighbourBox(boxes[x][y + 1]);
                }
                if (x + 1 <= board_size - 1 && y + 1 <= board_size - 1){
                    boxes[x][y].addNeighbourBox(boxes[x + 1][y + 1]);
                }

            }
        }

    }
    public Box[][] getBoxes(){
        return boxes;
    }

}
