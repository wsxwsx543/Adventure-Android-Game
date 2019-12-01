package com.example.phase2.stage3.model;

import com.example.phase2.appcore.game.Property;

public class AttackStrategy implements Strategy {
    @Override
    public Property doMove(Property property) {
        property.addPropertyToSelf(20,0,0,0);
        return property;
    }
}