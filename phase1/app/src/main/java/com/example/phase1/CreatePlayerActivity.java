package com.example.phase1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class CreatePlayerActivity extends AppCompatActivity implements View.OnClickListener {
    FileSystem fileSystem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_player);

        fileSystem = new FileSystem(this.getApplicationContext());

        Button createButton = findViewById(R.id.create);
        Button backButton = findViewById(R.id.back);

        createButton.setOnClickListener(this);
        backButton.setOnClickListener(this);

        TextView propertyTextView = findViewById(R.id.property);

        Spinner careerSpinner = (Spinner) findViewById(R.id.careers);
        careerSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String[] careers = getResources().getStringArray(R.array.careers);
                if(careers[position].equals("Attacker")) {
                    propertyTextView.setText("Attack: 10, Defence: 5, Flexibility: 5, Luckiness: 5");
                }
                if(careers[position].equals("Defender")){
                    propertyTextView.setText("Attack: 5, Defence: 10, Flexibility: 5, Luckiness: 5");
                }
                if(careers[position].equals("Runner")){
                    propertyTextView.setText("Attack: 5, Defence: 5, Flexibility: 10, Luckiness: 5");
                }
                if(careers[position].equals("Lucky Dog")){
                    propertyTextView.setText("Attack: 5, Defence: 5, Flexibility: 5, Luckiness: 10");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                propertyTextView.setText("");
            }
        });
    }

    public boolean createPlayer(){
        EditText playerNameEditText = findViewById(R.id.playerName);
        String playerName = playerNameEditText.getText().toString();

        User curUser = UserManager.getInstance().getCurUser();
        Property property = null;

        Spinner careersSpinner = findViewById(R.id.careers);
        switch (careersSpinner.getSelectedItem().toString()){
            case "Attacker":
                property = new Property(10, 5, 0, 5);
                break;
            case "Defender":
                property = new Property(5, 10, 0, 3);
                break;
            case "Runner":
                property = new Property(5, 5, 5, 3);
                break;
            case "Lucky Dog":
                property = new Property(5, 5, 0, 10);
                break;
        }
        try{
            curUser.addPlayer(new Player(playerName, property));
            return true;
        } catch (SamePlayerNameException e) {
            Toast.makeText(this, "Player name should not be same.", Toast.LENGTH_LONG).show();
            e.printStackTrace();
            return false;
        } catch (TooMuchPlayersException e) {
            Toast.makeText(this, "You have too many players.", Toast.LENGTH_LONG).show();
            e.printStackTrace();
            return false;
        } catch (EmptyPlayerNameException e) {
            Toast.makeText(this, "Player name cannot be empty.", Toast.LENGTH_LONG).show();
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.back:
                startActivity(new Intent(CreatePlayerActivity.this, ChooseOrCreatePlayerActivity.class));
                break;

            case R.id.create:
                if(createPlayer()) {
                    Toast.makeText(this, "Successfully create player.", Toast.LENGTH_LONG).show();
                    fileSystem.save(UserManager.getInstance().getUsers(), "Users.ser");
                    startActivity(new Intent(CreatePlayerActivity.this, ChooseOrCreatePlayerActivity.class));
                }
                break;
        }
    }
}
