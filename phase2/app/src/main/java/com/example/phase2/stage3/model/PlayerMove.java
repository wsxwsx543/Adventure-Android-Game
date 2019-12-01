package com.example.phase2.stage3.model;

import com.example.phase2.appcore.game.Player;
import com.example.phase2.appcore.game.Property;

public class PlayerMove implements Move {

    private Property property;

    /**
     * A player move.
     */
    PlayerMove(Player player) {
        this.property = player.getProperty();
    }

    /**
     * use playerDoMove method to get property of player after each move.
     *
     * @param playerMove case1 represents attack, case2 represents defence, case3 represents evade.
     * @return the property of the player after move.
     */
    public Property doMove(int playerMove) {

        Property playerProperty = new Property(this.property.getAttack(), this.property.getDefence(), this.property.getFlexibility(), this.property.getLuckiness());
        switch (playerMove) {
            case 1:
                Context context = new Context(new AttackStrategy());
                return context.executeStrategy(playerProperty);
            case 2:
                context = new Context(new DefenceStrategy());
                return context.executeStrategy(playerProperty);
            case 3:
                context = new Context(new EvadeStrategy());
                return context.executeStrategy(playerProperty);
            default:
                return null;
        }
    }

    /**
     * Get the name of move of the player.
     *
     * @param id An number.
     * @return The name of the move in a string type.
     */
    @Override
    public String getString(int id) {
        if (id == 0) {
            return "AttackStrategy.";
        } else if (id == 1) {
            return "DefenceStrategy.";
        } else if (id == 2) {
            return "EvadeStrategy";
        } else {
            return null;
        }
    }
}