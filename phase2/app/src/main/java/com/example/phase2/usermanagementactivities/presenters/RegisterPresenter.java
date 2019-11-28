package com.example.phase2.usermanagementactivities.presenters;

import com.example.phase2.datamanagement.FileSystem;
import com.example.phase2.usermanagementactivities.models.RegisterModel;
import com.example.phase2.usermanagementactivities.views.ToastStringView;

public class RegisterPresenter {
    private RegisterModel registerModel;
    private ToastStringView toastStringView;

    public RegisterPresenter(RegisterModel registerModel, ToastStringView toastStringView){
        this.registerModel = registerModel;
        this.toastStringView = toastStringView;
    }

    public boolean showResult(FileSystem fileSystem, String username, String password1, String password2){
        String result = registerModel.addNewUser(fileSystem, username, password1, password2);
        toastStringView.setResult(result);
        return result.equals("Register Successful.");
    }
}
