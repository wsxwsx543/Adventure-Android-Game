package com.example.phase1;

import java.util.ArrayList;
import java.util.List;

public class PlayerManager{
    List<Player> players;

    public PlayerManager(){
        players = new ArrayList<>();
    }

    public void addPlayer(Player player){
        players.add(player);
    }

    public void removePlayer(String name){
        for(int i = 0; i < players.size(); i++){
            if(players.get(i).name.equals(name)) {
                players.remove(i);
                return;
            }
        }
    }

    public Player findHighestAttackCreate(){
        int highest = 0;
        Player player = new Player("NoPlayer", new Property(0, 0, 0,
                0), 0);
        for(int i = 0; i < players.size(); i++){
            if(players.get(i).attackCreate > highest){
                highest = players.get(i).attackCreate;
                player = players.get(i);
            }
        }
        return player;
    }
}
