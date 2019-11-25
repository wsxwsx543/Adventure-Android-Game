package com.example.phase2.stage3;

import com.example.phase2.AppCoreClasses.Player;
import com.example.phase2.AppCoreClasses.Property;

public class PlayerMove implements Move {


    /** A player move. */
    PlayerMove() {
    }

    /**
     * use playerDoMove method to get property of player after each move.
     *
     * @param moveName the name of player's move.
     * @param player   the player.
     * @return the property of the player after move.
     */
    Property playerDoMove(String moveName, Player player) {

        Property p = player.getProperty();
        Property PP = new Property(p.getAttack(), p.getDefence(), p.getFlexibility(), p.getLuckiness());
        switch (moveName) {
            case "Attack":
                Context context = new Context(new Attack());
                return context.executeStrategy(PP);
            case "Defence":
                context = new Context(new Defence());
                return context.executeStrategy(PP);
            case "Evade":
                context = new Context(new Evade());
                return context.executeStrategy(PP);
            default:
                return null;
        }
    }

    /**
     * Get the name of move of the player.
     * @param id An number.
     * @return The name of the move in a string type.
     */
    @Override
    public String getString(int id) {
        if (id == 0) {
            return "Attack.";
        } else if (id == 1) {
            return "Defence.";
        } else if (id == 2) {
            return "Evade";
        } else {
            return null;
        }
    }
}