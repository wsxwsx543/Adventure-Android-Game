package com.example.phase1;

import java.io.Serializable;
import java.util.HashMap;

public class User implements Serializable {


    // username of the User
    private String username;
    // password of the User
    private String password;
    // The limit of how many players this User can initialize
//    private int playerNumLimit;
//    // List of all players the User initialized;
    private PlayerManager playerManager;

    // Key of this hash map is player's name and value is the player.
    private HashMap<String, Player> playerHashMap;

    public User(String username, String password){
        playerManager = new PlayerManager();
        setUsername(username);
        setPassword(password);
    }

    public void addPlayer(Player player){
        playerManager.addPlayer(player);
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
