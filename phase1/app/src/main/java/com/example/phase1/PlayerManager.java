package com.example.phase1;

import java.io.Serializable;
import java.util.HashMap;

public class PlayerManager implements Serializable {
    private HashMap<String, Player> players;

    public PlayerManager(){
        players = new HashMap<>();
    }

    public void addPlayer(Player player){
        players.put(player.getName(), player);
    }

    //Remove the player with specific name in players
    public void removePlayer(String name){
        players.remove(name);
    }

    // Return the player creates highest attack.
    public Player findHighestAttackCreate(){
        int highest = 0;
        Player ret_player = new Player("NoPlayer", new Property(0, 0, 0,
                0), 0);
        for(Player player: players.values()){
            if(player.getAttackCreate() > highest){
                highest = player.getAttackCreate();
                ret_player = player;
            }
        }
        return ret_player;
    }
}
