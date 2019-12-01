package com.example.phase2.usersystem.presenters;

import com.example.phase2.datamanagement.FileSystem;
import com.example.phase2.usersystem.models.LoginModel;
import com.example.phase2.usersystem.views.iview.IToastStringView;

public class LoginPresenter {
    private LoginModel loginModel;
    private IToastStringView IToastStringView;

    public LoginPresenter(LoginModel loginModel, IToastStringView loginView) {
        this.loginModel = loginModel;
        this.IToastStringView = loginView;
    }

    public boolean showResult(FileSystem fileSystem, String username, String password) {
        loginModel.loadScoreBoard(fileSystem);
        loginModel.loadUsers(fileSystem);
        if (loginModel.checkPasswordCorrect(username, password)) {
            IToastStringView.setResult("Login Successfully");
            return true;
        } else {
            IToastStringView.setResult("Invalid username or password.");
            return false;
        }
    }

    public void register(FileSystem fileSystem) {
        loginModel.loadScoreBoard(fileSystem);
        loginModel.loadUsers(fileSystem);
        IToastStringView.setResult("Register now!");
    }
}
