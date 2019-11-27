package com.example.phase2.usermanagementactivities.models;

import android.widget.EditText;

import com.example.phase2.R;
import com.example.phase2.appcore.User;
import com.example.phase2.appcore.UserManager;
import com.example.phase2.datamanagement.FileSystem;
import com.example.phase2.scoreboard.ScoreBoard;

import java.util.HashMap;

public class LoginModel {

    /**
     * Load users data from local file named Users.ser.
     */
    public void loadUsers(FileSystem fileSystem) {
        if (fileSystem.load("Users.ser") instanceof HashMap) {
            UserManager.getInstance().setUsers((HashMap<String, User>)
                    fileSystem.load("Users.ser"));
        } else {
            UserManager.getInstance().setUsers(new HashMap<>());
            fileSystem.save(UserManager.getInstance().getUsers(), "Users.ser");
        }
    }

    public void loadScoreBoard(FileSystem fileSystem) {
        if (fileSystem.load("ScoreBoard.ser") instanceof HashMap) {
            ScoreBoard.getInstance().setUserPlayers(fileSystem.load("ScoreBoard.ser"));
        } else {
            ScoreBoard.getInstance().setUserPlayers(new HashMap<>());
            fileSystem.save(ScoreBoard.getInstance().getUserPlayers(), "ScoreBoard.ser");
        }
    }

    /**
     * Return a boolean value states whether the password is correct or nor.
     *
     * @return a boolean value.
     */
    public boolean checkPasswordCorrect(String username, String password) {
        UserManager userManagerInstance = UserManager.getInstance();

        if (userManagerInstance.getUsers().containsKey(username)) {
            if (password.equals(userManagerInstance.getUsers().get(username).getPassword())) {
                userManagerInstance.setCurUser(userManagerInstance.getUsers().get(username));
                return true;
            }
        }
        return false;
    }
}
