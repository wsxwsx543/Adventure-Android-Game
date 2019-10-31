package com.example.phase1.stage3;

import com.example.phase1.*;


class PlayerMove implements Move {
    private Player player;
    private Property playerProperty;
    private String moveName;
/** moveName
a: attack
b: defence
c: escape
d:
 */
    public PlayerMove(String moveName, Player player) {
        this.moveName = moveName;
        this.player = player;
        this.playerProperty = this.player.getProperty();
    }
    @Override
    public String getMove() {
        return this.moveName;
    }
}
