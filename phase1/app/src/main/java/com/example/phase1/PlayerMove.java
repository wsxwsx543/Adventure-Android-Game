package com.example.phase1;

interface Move {
    void fight();
}

class PlayerMove implements Move {
    String MoveName;
    Property property;

    public PlayerMove(String MoveName, Property property){

    }
    @Override
    public void fight() {
        System.out.println("This is the Rectangle draw() method.");
    }
}


