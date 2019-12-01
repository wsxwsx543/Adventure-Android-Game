package com.example.phase2.stage3.model;


import com.example.phase2.appcore.game.Property;

public interface Move {
    /**
     * Get the move
     *
     * @param id An number which represent a move.
     * @return What the move is.
     */
    String getString(int id);

    Property doMove(int id);
}
