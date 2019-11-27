package com.example.phase2.usermanagementactivities.presenters;

import com.example.phase2.datamanagement.FileSystem;
import com.example.phase2.usermanagementactivities.models.LoginModel;
import com.example.phase2.usermanagementactivities.views.LoginView;

public class LoginPresenter {
    private LoginModel loginModel;
    private LoginView loginView;

    public LoginPresenter(LoginModel loginModel, LoginView loginView){
        this.loginModel = loginModel;
        this.loginView = loginView;
    }

    public boolean showResult(FileSystem fileSystem, String username, String password){
        loginModel.loadScoreBoard(fileSystem);
        loginModel.loadUsers(fileSystem);
        if(loginModel.checkPasswordCorrect(username, password)) {
            loginView.setLoginResult("Login Successfully");
            return true;
        } else {
            loginView.setLoginResult("Invalid username or password.");
            return false;
        }
    }
}
