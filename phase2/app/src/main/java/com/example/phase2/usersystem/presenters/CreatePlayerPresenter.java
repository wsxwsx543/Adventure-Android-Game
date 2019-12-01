package com.example.phase2.usersystem.presenters;

import android.widget.TextView;

import com.example.phase2.datamanagement.FileSystem;
import com.example.phase2.usersystem.models.CreatePlayerModel;
import com.example.phase2.usersystem.views.iview.ITextStringView;
import com.example.phase2.usersystem.views.iview.IToastStringView;

public class CreatePlayerPresenter {
    private CreatePlayerModel createPlayerModel;
    private IToastStringView IToastStringView;
    private ITextStringView ITextStringView;

    public CreatePlayerPresenter(CreatePlayerModel createPlayerModel, IToastStringView IToastStringView, ITextStringView ITextStringView) {
        this.createPlayerModel = createPlayerModel;
        this.IToastStringView = IToastStringView;
        this.ITextStringView = ITextStringView;
    }

    public boolean showResult(FileSystem fileSystem, String playerName, String career, String weapon) {
        String result = createPlayerModel.createPlayer(fileSystem, playerName, career, weapon);
        IToastStringView.setResult(result);
        return result.equals("Successfully create player.");
    }

    public void setCareerProperty(TextView textView, String career) {
        String property = createPlayerModel.generateCareerProperty(career).toString();
        ITextStringView.setText(textView, property);
    }

    public void setWeaponProperty(TextView textView, String weapon) {
        String property = createPlayerModel.generateWeaponProperty(weapon).toString();
        ITextStringView.setText(textView, property);
    }
}
