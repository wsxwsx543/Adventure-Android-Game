package com.example.phase2.usermanagementactivities.presenters;

import com.example.phase2.datamanagement.FileSystem;
import com.example.phase2.usermanagementactivities.models.RegisterModel;
import com.example.phase2.usermanagementactivities.views.SetStringView;

import java.util.Observable;
import java.util.Observer;

public class RegisterPresenter {
    private RegisterModel registerModel;
    private SetStringView setStringView;

    public RegisterPresenter(RegisterModel registerModel, SetStringView setStringView){
        this.registerModel = registerModel;
        this.setStringView = setStringView;
    }

    public boolean showResult(FileSystem fileSystem, String username, String password1, String password2){
        String result = registerModel.addNewUser(fileSystem, username, password1, password2);
        setStringView.setResult(result);
        return result.equals("Register Successful.");
    }
}
