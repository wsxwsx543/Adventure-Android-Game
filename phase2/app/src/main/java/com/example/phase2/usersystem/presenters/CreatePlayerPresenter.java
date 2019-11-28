package com.example.phase2.usersystem.presenters;

import android.widget.TextView;

import com.example.phase2.datamanagement.FileSystem;
import com.example.phase2.usersystem.models.CreatePlayerModel;
import com.example.phase2.usersystem.views.TextStringView;
import com.example.phase2.usersystem.views.ToastStringView;

public class CreatePlayerPresenter {
    private CreatePlayerModel createPlayerModel;
    private ToastStringView toastStringView;
    private TextStringView textStringView;

    public CreatePlayerPresenter(CreatePlayerModel createPlayerModel, ToastStringView toastStringView, TextStringView textStringView){
        this.createPlayerModel = createPlayerModel;
        this.toastStringView = toastStringView;
        this.textStringView = textStringView;
    }

    public boolean showResult(FileSystem fileSystem, String playerName, String career, String weapon){
        String result = createPlayerModel.createPlayer(fileSystem, playerName, career, weapon);
        toastStringView.setResult(result);
        return result.equals("Successfully create player.");
    }

    public void setCareerProperty(TextView textView, String career){
        String property = createPlayerModel.generateCareerProperty(career).toString();
        textStringView.setText(textView, property);
    }

    public void setWeaponProperty(TextView textView, String weapon){
        String property = createPlayerModel.generateWeaponProperty(weapon).toString();
        textStringView.setText(textView, property);
    }
}
