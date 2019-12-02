package com.example.phase2.stage2.view;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.os.Handler;

import com.example.phase2.appcore.game.Property;
import com.example.phase2.usersystem.views.app.SuperActivity;

public interface DisplayView {
    void draw();
    double getCurX();
    double getCurY();
    void setCurX(double curX);
    void setCurY(double curY);
    Handler getHandler();
    Resources getResources();
    Context getContext();
}
