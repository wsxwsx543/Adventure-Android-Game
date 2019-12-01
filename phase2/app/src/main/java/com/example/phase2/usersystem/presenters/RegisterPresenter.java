package com.example.phase2.usersystem.presenters;

import com.example.phase2.datamanagement.FileSystem;
import com.example.phase2.usersystem.models.RegisterModel;
import com.example.phase2.usersystem.views.iview.IToastStringView;

public class RegisterPresenter {
    private RegisterModel registerModel;
    private IToastStringView IToastStringView;

    public RegisterPresenter(RegisterModel registerModel, IToastStringView IToastStringView) {
        this.registerModel = registerModel;
        this.IToastStringView = IToastStringView;
    }

    public boolean showResult(FileSystem fileSystem, String username, String password1, String password2) {
        String result = registerModel.addNewUser(fileSystem, username, password1, password2);
        IToastStringView.setResult(result);
        return result.equals("Register Successful.");
    }
}
