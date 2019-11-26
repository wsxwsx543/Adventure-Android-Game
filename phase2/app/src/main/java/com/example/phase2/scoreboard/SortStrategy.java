package com.example.phase2.scoreboard;

import com.example.phase2.appcore.Player;
import com.example.phase2.appcore.User;

import java.util.HashMap;
import java.util.ArrayList;

public interface SortStrategy {
    ArrayList sort(HashMap<User, ArrayList<Player>> hashMap);
}
