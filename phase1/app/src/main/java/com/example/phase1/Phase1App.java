package com.example.phase1;

import android.app.Application;

public class Phase1App extends Application {

    private String colorTheme;

    @Override
    public void onCreate() {
        super.onCreate();
        colorTheme = "blue";
    }

    public String getColorTheme() {
        return colorTheme;
    }

    public void setColorTheme(String colorTheme) {
        this.colorTheme = colorTheme;
    }
}
