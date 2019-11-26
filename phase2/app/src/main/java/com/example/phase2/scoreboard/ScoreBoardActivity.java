package com.example.phase2.scoreboard;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.phase2.Initializable;
import com.example.phase2.R;
import com.example.phase2.usermanagementactivities.ChooseOrCreatePlayerActivity;
import com.example.phase2.usermanagementactivities.SuperActivity;

import java.util.List;
import java.util.Map;

public class ScoreBoardActivity extends SuperActivity implements Initializable, View.OnClickListener{
    private TextView first;
    private TextView second;
    private TextView third;
    private TextView fourth;
    private TextView fifth;

    List<Map.Entry<String, Integer>> rankList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    private void drawScoreBoard(){
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
        final Button lifeButton = findViewById(R.id.life);
        final Button propertyButton = findViewById(R.id.property);
        backButton.setOnClickListener(this);
        propertyButton.setOnClickListener(this);
        lifeButton.setOnClickListener(this);
        drawScoreBoard();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back:
                startActivity(new Intent(ScoreBoardActivity.this, ChooseOrCreatePlayerActivity.class));
                break;
            case R.id.life:
                rankList = new PlayerLivesStrategy().sort(ScoreBoard.getInstance().getUserPlayers());
                drawScoreBoard();
                break;
            case R.id.property:
                rankList = new PlayerPropertyStrategy().sort(ScoreBoard.getInstance().getUserPlayers());
                drawScoreBoard();;
                break;
        }
    }
}
