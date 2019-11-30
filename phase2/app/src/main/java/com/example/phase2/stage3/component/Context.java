package com.example.phase2.stage3.component;

import com.example.phase2.appcore.game.Property;
import com.example.phase2.stage3.component.Strategy;

public class Context {
    private Strategy strategy;

    public Context(Strategy strategy){
        this.strategy = strategy;
    }

    public Property executeStrategy(Property property){
        return strategy.doMove(property);
    }
}
