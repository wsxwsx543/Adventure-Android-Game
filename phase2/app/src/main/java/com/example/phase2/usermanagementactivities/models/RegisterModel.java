package com.example.phase2.usermanagementactivities.models;

import android.widget.TextView;
import android.widget.Toast;

import com.example.phase2.R;
import com.example.phase2.appcore.User;
import com.example.phase2.appcore.UserManager;
import com.example.phase2.datamanagement.FileSystem;
import com.example.phase2.exceptions.EmptyNameException;
import com.example.phase2.exceptions.EmptyPasswordException;
import com.example.phase2.exceptions.PasswordDifferentException;
import com.example.phase2.exceptions.SameNameException;
import com.example.phase2.usermanagementactivities.presenters.RegisterPresenter;

import java.util.Observable;

public class RegisterModel {

    public RegisterModel(){}

    /**
     * Return whether a account is created successfully or not.
     * @return a boolean value.
     */
    public String addNewUser(FileSystem fileSystem, String username, String password1, String password2) {
        UserManager userManager = UserManager.getInstance();

        try {
            userManager.addUser(username, password1, password2);
        } catch (EmptyNameException e) {
            return "Username cannot be empty.";
        } catch (EmptyPasswordException e) {
            return "Password cannot be empty";
        } catch (SameNameException e) {
            return "Please change a username, this username has been token.";
        } catch (PasswordDifferentException e) {
            return "Password enterd are not same.";
        }

        fileSystem.save(userManager.getUsers(), "Users.ser");
        return "Register Successful.";
    }
}
