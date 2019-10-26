package com.example.phase1;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PlayerManager implements Serializable {
    private List<Player> players;

    public PlayerManager(){
        players = new ArrayList<>();
    }

    public void addPlayer(Player player){
        players.add(player);
    }

    //Remove the player with specific name in players
    public void removePlayer(String name){
        for(Player player: players){
            if(player.getName().equals(name)) {
                players.remove(player);
                return;
            }
        }
    }

    // Return the player creates highest attack.
    public Player findHighestAttackCreate(){
        int highest = 0;
        Player ret_player = new Player("NoPlayer", new Property(0, 0, 0,
                0), 0);
        for(Player player: players){
            if(player.getAttackCreate() > highest){
                highest = player.getAttackCreate();
                ret_player = player;
            }
        }
        return ret_player;
    }
}
