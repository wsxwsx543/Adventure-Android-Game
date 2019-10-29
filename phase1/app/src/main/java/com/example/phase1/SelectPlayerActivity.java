package com.example.phase1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Iterator;
import java.util.Set;

public class SelectPlayerActivity extends AppCompatActivity implements View.OnClickListener{
    User curUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_player);

        curUser = UserManager.getInstance().getCurUser();

        Button startButton = findViewById(R.id.start);
        Button backButton = findViewById(R.id.back);

        startButton.setOnClickListener(this);
        backButton.setOnClickListener(this);

        initSpinner();
    }

    public void initSpinner(){
        Spinner players = (Spinner) findViewById(R.id.players);
        Set<String> playerNamesSet = curUser.getPlayers().keySet();
        Iterator<String> playerNamesIterator = playerNamesSet.iterator();
        String[] playerNames = new String[playerNamesSet.size()];
        int curIndex = 0;
        while(playerNamesIterator.hasNext()){
            playerNames[curIndex++] = playerNamesIterator.next();
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, playerNames);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        players.setAdapter(adapter);
        players.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            TextView stageTextView = findViewById(R.id.curStage);
            TextView propertyTextView = findViewById(R.id.property);

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                stageTextView.setText("Current at Stage: " + String.valueOf(curUser.getPlayers().get(playerNames[position]).getCurStage()));
                propertyTextView.setText(curUser.getPlayers().get(playerNames[position]).getProperty().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                stageTextView.setText("");
                propertyTextView.setText("");
            }
        });
    }

    @Override
    public void onClick(View v) {
        Spinner playerNames = findViewById(R.id.players);
        String curPlayerName = null;
        if(playerNames.getSelectedItem() != null)
            curPlayerName = playerNames.getSelectedItem().toString();

        switch (v.getId()){
            case R.id.back:
                startActivity(new Intent(SelectPlayerActivity.this, ChooseOrCreatePlayerActivity.class));
                break;
            case R.id.start:
                if(curUser.getPlayers().containsKey(curPlayerName))
                    curUser.setCurPlayer(curUser.getPlayers().get(curPlayerName));
                else{
                    Toast.makeText(this, "Please create a new player first.", Toast.LENGTH_LONG).show();
                }
                break;
        }
    }
}
