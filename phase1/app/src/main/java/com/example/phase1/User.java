package com.example.phase1;

import java.io.Serializable;

public class User implements Serializable {


    // username of the User
    private String username;
    // password of the User
    private String password;
    // The limit of how many players this User can initialize
//    private int playerNumLimit;
//    // List of all players the User initialized;
    private PlayerManager playerManager;

    public User(String username, String password){
        playerManager = new PlayerManager();
        setUsername(username);
        setPassword(password);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
