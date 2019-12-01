package com.example.phase2.stage3.model;

import com.example.phase2.appcore.game.Property;

/**
 * Execute the strategy.
 */
public class Context {
    private Strategy strategy;

    public Context(Strategy strategy) {
        this.strategy = strategy;
    }

    Property executeStrategy(Property property) {
        return strategy.doMove(property);
    }
}
