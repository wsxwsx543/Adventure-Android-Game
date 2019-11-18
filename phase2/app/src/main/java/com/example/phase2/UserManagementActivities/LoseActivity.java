package com.example.phase2.UserManagementActivities;

import com.example.phase2.Initializable;
import com.example.phase2.R;

/** Jump to this activity if the player lose the game. */
public class LoseActivity extends ResultActivity implements Initializable {
    @Override
    public void init() {
        super.init();
        setContentView(R.layout.activity_lose);
    }
}
