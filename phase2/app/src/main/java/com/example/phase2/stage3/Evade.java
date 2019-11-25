package com.example.phase2.stage3;

import com.example.phase2.AppCoreClasses.Property;

public class Evade implements Strategy {
    @Override
    public Property doMove(Property property) {
        property.addPropertyToSelf(-15,0,5,10);
        return property;
    }
}