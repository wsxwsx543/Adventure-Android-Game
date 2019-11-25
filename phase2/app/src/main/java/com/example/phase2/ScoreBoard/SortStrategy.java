package com.example.phase2.ScoreBoard;

import com.example.phase2.AppCoreClasses.Player;
import com.example.phase2.AppCoreClasses.User;

import java.util.HashMap;
import java.util.ArrayList;

public interface SortStrategy {
    ArrayList sort(HashMap<User, ArrayList<Player>> hashMap);
}
