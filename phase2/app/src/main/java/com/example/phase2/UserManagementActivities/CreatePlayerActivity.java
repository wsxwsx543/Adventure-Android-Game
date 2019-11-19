package com.example.phase2.UserManagementActivities;

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

import com.example.phase2.Exceptions.EmptyPlayerNameException;
import com.example.phase2.Exceptions.SamePlayerNameException;
import com.example.phase2.DataManagement.FileSystem;
import com.example.phase2.Initializable;
import com.example.phase2.AppCoreClasses.GameApp;
import com.example.phase2.AppCoreClasses.Player;
import com.example.phase2.AppCoreClasses.Property;
import com.example.phase2.R;
import com.example.phase2.AppCoreClasses.User;
import com.example.phase2.AppCoreClasses.UserManager;
import com.example.phase2.AppCoreClasses.Weapon;

public class CreatePlayerActivity extends SuperActivity implements View.OnClickListener, Initializable {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();

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
                if(careers[position].equals("Computer Science")) {
                    propertyTextView.setText("Attack: 20, Defence: 10, Flexibility: 0, Luckiness: 3");
                }
                if(careers[position].equals("Life Science")){
                    propertyTextView.setText("Attack: 15, Defence: 15, Flexibility: 0, Luckiness: 3");
                }
                if(careers[position].equals("Rotman Commerce")){
                    propertyTextView.setText("Attack: 15, Defence: 10, Flexibility: 5, Luckiness: 3");
                }
                if(careers[position].equals("Engineer")){
                    propertyTextView.setText("Attack: 15, Defence: 10, Flexibility: 0, Luckiness: 7");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                propertyTextView.setText("");
            }
        });

        TextView weaponPropertyTextView = findViewById(R.id.weaponProperty);
        Spinner weaponSpinner = (Spinner) findViewById(R.id.weapons);
        weaponSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String[] weapons = getResources().getStringArray(R.array.weapons);
                if(weapons[position].equals("Pencil"))
                    weaponPropertyTextView.setText("Attack: 5, Defence: 0, Flexibility: 0, Luckiness: 0");
                if(weapons[position].equals("Eraser"))
                    weaponPropertyTextView.setText("Attack: 0, Defence: 5, Flexibility: 0, Luckiness: 0");
                if(weapons[position].equals("Calculator"))
                    weaponPropertyTextView.setText("Attack: 0, Defence: 0, Flexibility: 3, Luckiness: 0");
                if(weapons[position].equals("CheatSheet"))
                    weaponPropertyTextView.setText("Attack: 0, Defence: 0, Flexibility: 0, Luckiness: 3");
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                weaponPropertyTextView.setText("");
            }
        });
    }

    /**
     * Create a new player. Return true if the new player created successfully and false otherwise.
     * @return a boolean value represents whether the player is created.
     */
    public boolean createPlayer(){
        Player player;

        EditText playerNameEditText = findViewById(R.id.playerName);
        String playerName = playerNameEditText.getText().toString();

        User curUser = UserManager.getInstance().getCurUser();
        Property property = null;

        Spinner careersSpinner = findViewById(R.id.careers);
        switch (careersSpinner.getSelectedItem().toString()){
            case "Computer Science":
                property = new Property(20, 10, 0, 3);
                break;
            case "Life Science":
                property = new Property(15, 15, 0, 3);
                break;
            case "Rotman Commerce":
                property = new Property(15, 10, 5, 3);
                break;
            case "Engineer":
                property = new Property(15, 10, 0, 7);
                break;
        }

        player = new Player(playerName, property);
        Weapon weapon;
        Spinner weaponsSpinner = findViewById(R.id.weapons);
        switch (weaponsSpinner.getSelectedItem().toString()) {
            case "Pencil":
                weapon = new Weapon("Pencil", 10, 0, 0, 0);
                break;
            case "Eraser":
                weapon = new Weapon("Eraser", 0, 5, 0, 0);
                break;
            case "Calculator":
                weapon = new Weapon("Calculator", 0, 0, 3, 0);
                break;
            case "CheatSheet":
                weapon = new Weapon("CheatSheet", 0, 0, 0, 3);
                break;
            default:
                weapon = new Weapon("", 0, 0, 0, 0);
        }
        player.addWeapon(weapon);

        try{
            curUser.addPlayer(player);
            return true;
        } catch (SamePlayerNameException e) {
            Toast.makeText(this, "Player name should not be same.", Toast.LENGTH_LONG).show();
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

    @Override
    public void init() {
        super.init();
        setContentView(R.layout.activity_create_player);
    }
}
