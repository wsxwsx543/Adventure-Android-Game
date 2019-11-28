package com.example.phase2.usersystem.models;

import com.example.phase2.appcore.scoreboard.ScoreBoard;
import com.example.phase2.appcore.scoreboard.SortStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ScoreBoardModel {
    public ScoreBoardModel() {
    }

    public ArrayList<Map.Entry<String, Integer>> sortResult(SortStrategy sortStrategy) {
        return sortStrategy.sort(ScoreBoard.getInstance().getUserPlayers());
    }
}
