package com.example.phase2.stage3;

import com.example.phase2.AppCoreClasses.Property;

class Context {
    private Strategy strategy;

    Context(Strategy strategy){
        this.strategy = strategy;
    }

    Property executeStrategy(Property property){
        return strategy.doMove(property);
    }
}
