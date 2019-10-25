package com.example.phase1;

public class User {


    // username of the User
    private String username;
    // password of the User
    private String password;
    // The limit of how many players this User can initialize
    private int playerNumLimit;
    // List of all players the User initialized;
    private Player[] players;


    public User(int playNumLimit) {
        this.playerNumLimit = playNumLimit;
        players = new Player[this.playerNumLimit];

    }
    public User(int playerNumLimit, String username, String password){
        this(playerNumLimit);
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
