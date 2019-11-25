package com.example.phase2.stage3;

import com.example.phase2.AppCoreClasses.Property;

public class Defence implements Strategy {
    @Override
    public Property doMove(Property property) {
        property.addPropertyToSelf(-100,30,-10,-10);
        return property;
    }
}