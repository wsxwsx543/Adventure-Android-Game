package com.example.phase2.usersystem.presenters;

import com.example.phase2.datamanagement.FileSystem;
import com.example.phase2.usersystem.models.LoginModel;
import com.example.phase2.usersystem.views.iview.ToastStringView;

public class LoginPresenter {
    private LoginModel loginModel;
    private ToastStringView toastStringView;

    public LoginPresenter(LoginModel loginModel, ToastStringView loginView) {
        this.loginModel = loginModel;
        this.toastStringView = loginView;
    }

    public boolean showResult(FileSystem fileSystem, String username, String password) {
        loginModel.loadScoreBoard(fileSystem);
        loginModel.loadUsers(fileSystem);
        if (loginModel.checkPasswordCorrect(username, password)) {
            toastStringView.setResult("Login Successfully");
            return true;
        } else {
            toastStringView.setResult("Invalid username or password.");
            return false;
        }
    }

    public void register(FileSystem fileSystem) {
        loginModel.loadScoreBoard(fileSystem);
        loginModel.loadUsers(fileSystem);
        toastStringView.setResult("Register now!");
    }
}
