package com.example.phase1;

import java.io.Serializable;
import java.util.HashMap;

public class User implements Serializable {

    // username of the User
    private String username;
    // password of the User
    private String password;
    // Current player for this user.
    private Player curPlayer;
    // max number of players for a user.
    private int playersNumberLimit;
    // Players this user have
    private HashMap<String, Player> players;

    public User(String username, String password, int playersNumberLimit){
        setUsername(username);
        setPassword(password);
        setPlayersNumberLimit(playersNumberLimit);
        players = new HashMap<>();
        curPlayer = null;
    }

    public void setPlayersNumberLimit(int playersNumberLimit) {
        this.playersNumberLimit = playersNumberLimit;
    }
    public int getPlayersNumberLimit() {
        return playersNumberLimit;
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

    public void setCurPlayer(Player curPlayer){
        this.curPlayer = curPlayer;
    }
    public Player getCurPlayer(){
        return this.curPlayer;
    }

    public HashMap<String, Player> getPlayers() {
        return players;
    }
    public void setPlayers(HashMap<String, Player> players) {
        this.players = players;
    }

    public void addPlayer(Player player) throws TooMuchPlayersException, SamePlayerNameException, EmptyPlayerNameException{
        if(players.size() >= playersNumberLimit) {
            throw new TooMuchPlayersException();
        }
        else if(players.containsKey(player.getName())){
            throw new SamePlayerNameException();
        }
        else if(player.getName().equals("")){
            throw new EmptyPlayerNameException();
        }
        else players.put(player.getName(), player);
    }

    // Return the player creates highest attack.
    public Player findHighestAttackCreate(){
        int highest = 0;
        Player ret_player = new Player("NoPlayer", new Property(0, 0, 0,
                0));
        for(Player player: players.values()){
            if(player.getAttackCreate() > highest){
                highest = player.getAttackCreate();
                ret_player = player;
            }
        }
        return ret_player;
    }
}

class TooMuchPlayersException extends Exception{
    public TooMuchPlayersException(){
        super();
    }
}
class SamePlayerNameException extends Exception{
    public SamePlayerNameException(){
        super();
    }
}
class EmptyPlayerNameException extends Exception{
    EmptyPlayerNameException(){
        super();
    }
}