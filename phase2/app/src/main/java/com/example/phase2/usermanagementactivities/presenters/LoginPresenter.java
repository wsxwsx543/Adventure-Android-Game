package com.example.phase2.usermanagementactivities.presenters;

import com.example.phase2.datamanagement.FileSystem;
import com.example.phase2.usermanagementactivities.models.LoginModel;
import com.example.phase2.usermanagementactivities.views.SetStringView;

public class LoginPresenter {
    private LoginModel loginModel;
    private SetStringView setStringView;

    public LoginPresenter(LoginModel loginModel, SetStringView loginView){
        this.loginModel = loginModel;
        this.setStringView = loginView;
    }

    public boolean showResult(FileSystem fileSystem, String username, String password){
        loginModel.loadScoreBoard(fileSystem);
        loginModel.loadUsers(fileSystem);
        if(loginModel.checkPasswordCorrect(username, password)) {
            setStringView.setResult("Login Successfully");
            return true;
        } else {
            setStringView.setResult("Invalid username or password.");
            return false;
        }
    }

    public void register(FileSystem fileSystem){
        loginModel.loadScoreBoard(fileSystem);
        loginModel.loadUsers(fileSystem);
        setStringView.setResult("Register now!");
    }
}
