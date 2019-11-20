package com.example.phase2.ScoreBoard;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

/**
 * Here we use Singleton design pattern to build the ScoreBoard.
 * The ScoreBoard is an Observer and the Player will be Observable.
 */

public class ScoreBoard implements Observer, Serializable {
    private static ScoreBoard scoreBoard = null;
    private List<Map.Entry<String, Integer>> rankNameLives;
    private HashMap<String, Integer> nameLives;

    private ScoreBoard(){

    }

    public void setRankNameLives(List<Map.Entry<String, Integer>> rankNameLives) {
        this.rankNameLives = rankNameLives;
    }

    public List<Map.Entry<String, Integer>> getRankNameLives() {
        return rankNameLives;
    }

    public void setNameLives(HashMap<String, Integer> nameLives) {
        this.nameLives = nameLives;
    }

    public HashMap<String, Integer> getNameLives() {
        return nameLives;
    }

    public static ScoreBoard getInstance(){
        if(scoreBoard == null)
            scoreBoard = new ScoreBoard();
        return scoreBoard;
    }

    public static void setScoreBoard(ScoreBoard scoreBoard) {
        ScoreBoard.scoreBoard = scoreBoard;
    }

    private void sort(){
        scoreBoard.rankNameLives = new PlayerLivesStrategy().sort(nameLives);
    }

    @Override
    public void update(Observable o, Object arg) {
        System.out.println(o);
        String[] strings = o.toString().split(":");
        String name = strings[0];
        System.out.println(name);
        Integer lives = Integer.valueOf(strings[1]);
        System.out.println(lives);
        ScoreBoard.getInstance().getNameLives().put(name, lives);
        sort();
    }
}
