package com.example.phase1.stage2;

import java.util.ArrayList;

public class Box {

    // The location of the Box
    private int x, y;
    // The Unit this box contained (A trap or a treasure)
    public Unit unit;
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
    public Box(Unit unit, int x, int y){
        this.unit = unit;
        this.x = x;
        this.y = y;
        neighbours = new ArrayList<>();
    }

    public void addNeighbourBox(Box box){
        this.neighbours.add(box);
    }
}
