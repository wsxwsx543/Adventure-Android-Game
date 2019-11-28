package com.example.phase2.appcore.scoreboard;

import com.example.phase2.appcore.Player;
import com.example.phase2.appcore.User;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.Map;

public interface SortStrategy {
    ArrayList<HashMap.Entry<String, Integer>> sort(HashMap<User, ArrayList<Player>> hashMap);
}
