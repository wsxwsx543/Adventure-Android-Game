package com.example.phase2.ScoreBoard;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.phase2.Initializable;
import com.example.phase2.R;
import com.example.phase2.UserManagementActivities.ChooseOrCreatePlayerActivity;
import com.example.phase2.UserManagementActivities.SuperActivity;

import org.w3c.dom.Text;

import java.util.List;
import java.util.Map;

public class ScoreBoardActivity extends SuperActivity implements Initializable, View.OnClickListener{
    private TextView first;
    private TextView second;
    private TextView third;
    private TextView fourth;
    private TextView fifth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    private void drawScoreBoard(){
        ScoreBoard scoreBoard = ScoreBoard.getInstance();
        List<Map.Entry<String, Integer>> rankList = scoreBoard.sort(new PlayerLivesStrategy());
        if(rankList == null)
            return;
        if(rankList.size() > 0)
            first.setText(rankList.get(0).getKey() + ": " + rankList.get(0).getValue());
        if(rankList.size() > 1)
            second.setText(rankList.get(1).getKey() + ":" + rankList.get(1).getValue());
        if(rankList.size() > 2)
            third.setText(rankList.get(2).getKey() + ":" + rankList.get(2).getValue());
        if(rankList.size() > 3)
            fourth.setText(rankList.get(3).getKey() + ":" + rankList.get(3).getValue());
        if(rankList.size() > 4)
            fifth.setText(rankList.get(4).getKey() + ":" + rankList.get(4).getValue());
    }

    public void init(){
        super.init();
        setContentView(R.layout.activity_score_board);
        final Button backButton = findViewById(R.id.back);
        first = findViewById(R.id.first);
        second = findViewById(R.id.second);
        third = findViewById(R.id.third);
        fourth = findViewById(R.id.fourth);
        fifth = findViewById(R.id.fifth);
        backButton.setOnClickListener(this);
        drawScoreBoard();
    }

    @Override
    public void onClick(View v) {
        startActivity(new Intent(ScoreBoardActivity.this, ChooseOrCreatePlayerActivity.class));
    }
}
