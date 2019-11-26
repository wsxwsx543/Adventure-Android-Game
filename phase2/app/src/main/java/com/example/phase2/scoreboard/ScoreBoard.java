package com.example.phase2.scoreboard;

import com.example.phase2.appcore.Player;
import com.example.phase2.appcore.User;
import com.example.phase2.appcore.UserManager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

/**
 * Here we use Singleton design pattern to build the ScoreBoard.
 * The ScoreBoard is an Observer and the Player will be Observable.
 */

public class ScoreBoard implements Observer, Serializable {
    private static ScoreBoard scoreBoard = null;
    private HashMap<User, ArrayList<Player>> userPlayers;

    private ScoreBoard(){

    }

    public static ScoreBoard getInstance(){
        if(scoreBoard == null)
            scoreBoard = new ScoreBoard();
        return scoreBoard;
    }

    public static void setScoreBoard(ScoreBoard scoreBoard) {
        ScoreBoard.scoreBoard = scoreBoard;
    }

    public ArrayList sort(SortStrategy sortStrategy){
        return sortStrategy.sort(userPlayers);
    }

    public HashMap<User, ArrayList<Player>> getUserPlayers() {
        return userPlayers;
    }

    public void setUserPlayers(HashMap<User, ArrayList<Player>> userPlayers) {
        this.userPlayers = userPlayers;
    }

    @Override
    public void update(Observable o, Object arg) {
        if(userPlayers.containsKey(UserManager.getInstance().getCurUser())){
            userPlayers.get(UserManager.getInstance().getCurUser()).add(UserManager.getInstance().getCurUser().getCurPlayer());
        }
        else{
            ArrayList<Player> players = new ArrayList<>();
            players.add(UserManager.getInstance().getCurUser().getCurPlayer());
            userPlayers.put(UserManager.getInstance().getCurUser(), players);
        }
    }
}