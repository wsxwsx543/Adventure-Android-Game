package com.example.phase2.stage3;

import com.example.phase2.appcore.Property;

public class AttackStrategy implements Strategy {
    @Override
    public Property doMove(Property property) {
        property.addPropertyToSelf(10,0,0,0);
        return property;
    }
}