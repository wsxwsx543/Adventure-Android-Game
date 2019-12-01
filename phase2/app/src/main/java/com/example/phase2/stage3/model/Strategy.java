package com.example.phase2.stage3.model;

import com.example.phase2.appcore.game.Property;

public interface Strategy {
    public Property doMove(Property property);
}