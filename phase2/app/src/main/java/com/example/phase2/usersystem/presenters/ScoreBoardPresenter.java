package com.example.phase2.usersystem.presenters;

import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.phase2.appcore.scoreboard.SortStrategy;
import com.example.phase2.stage3.Strategy;
import com.example.phase2.usersystem.models.ScoreBoardModel;
import com.example.phase2.usersystem.views.TextStringView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ScoreBoardPresenter {
    private ScoreBoardModel scoreBoardModel;
    private TextStringView textStringView;

    public ScoreBoardPresenter(ScoreBoardModel scoreBoardModel, TextStringView textStringView) {
        this.scoreBoardModel = scoreBoardModel;
        this.textStringView = textStringView;
    }

    public void showFirst(TextView textView, SortStrategy sortStrategy) {
        ArrayList<Map.Entry<String, Integer>> result = scoreBoardModel.sortResult(sortStrategy);
        System.out.println(result.size());
        if (result.size() >= 1) {
            textStringView.setText(textView, result.get(0).getKey() + ": " + result.get(0).getValue());
        }
    }

    public void showSecond(TextView textView, SortStrategy sortStrategy) {
        ArrayList<Map.Entry<String, Integer>> result = scoreBoardModel.sortResult(sortStrategy);
        if (result.size() >= 2) {
            textStringView.setText(textView, result.get(1).getKey() + ": " + result.get(1).getValue());
        }
    }

    public void showThird(TextView textView, SortStrategy sortStrategy) {
        ArrayList<Map.Entry<String, Integer>> result = scoreBoardModel.sortResult(sortStrategy);
        if (result.size() >= 3) {
            textStringView.setText(textView, result.get(2).getKey() + ": " + result.get(2).getValue());
        }
    }

    public void showFourth(TextView textView, SortStrategy sortStrategy) {
        ArrayList<Map.Entry<String, Integer>> result = scoreBoardModel.sortResult(sortStrategy);
        if (result.size() >= 4) {
            textStringView.setText(textView, result.get(3).getKey() + ": " + result.get(3).getValue());
        }
    }

    public void showFifth(TextView textView, SortStrategy sortStrategy) {
        ArrayList<Map.Entry<String, Integer>> result = scoreBoardModel.sortResult(sortStrategy);
        if (result.size() >= 5) {
            textStringView.setText(textView, result.get(4).getKey() + ": " + result.get(4).getValue());
        }
    }
}
