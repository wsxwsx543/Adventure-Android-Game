package com.example.phase2.UserManagementActivities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.example.phase2.AppCoreClasses.UserManager;
import com.example.phase2.Initializable;
import com.example.phase2.R;
import com.example.phase2.stage3.PopUpActivity;

/** Jump to this activity if the player lose the game. */
public class LoseActivity extends SuperActivity implements Initializable {
    Handler myhandler = new Handler(){
        @Override
        public void handleMessage(Message msg){
            super.handleMessage(msg);
            switch (msg.what){
                case 1:
                    startActivity(new Intent(LoseActivity.this, PopUpActivity.class));
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
        myhandler.sendEmptyMessageDelayed(1, 3000);
    }

    @Override
    public void init() {
        super.init();
        setContentView(R.layout.activity_lose);
        UserManager.getInstance().getCurUser().getCurPlayer().setWin(false);
    }
}
