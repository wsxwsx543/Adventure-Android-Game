package com.example.phase2.stage3.model;

import com.example.phase2.datamanagement.FileSystem;
import com.example.phase2.appcore.scoreboard.ScoreBoard;
import com.example.phase2.appcore.game.Player;
import com.example.phase2.appcore.game.Property;
import com.example.phase2.appcore.user.UserManager;
import android.content.Context;


public class BattleModel {
    /** The current player and current monster. */
    private Player player;
    private Monster monster;
    /** The boolean value decide whether is the round that player could move. */
    private boolean moveStatus = false;
    /** The current round number of the battle game. */
    private int roundNum;
    /** Monster's property. */
    private Property monsterProperty;
    /** The filesystem. */
    private FileSystem fileSystem;

    public BattleModel(Context context){
        Property monster_property = new Property(10, 10, 0, 0);
        monster = new Monster(300, monster_property);
        player = UserManager.getInstance().getCurUser().getCurPlayer();
        fileSystem = new FileSystem(context);
    }

    public void battle(int playerMove){
        Round round = new Round(player, monster);
        nextRound();
        round.battle(playerMove, monsterProperty);
        int decreaseM = round.getDamage1();
        int decreaseP = round.getDamage2();
        if (player.getLivesRemain() > decreaseP) {
            player.loseLives(decreaseP);
        } else player.loseLives(player.getLivesRemain());
        if (monster.getLivesRemain() > decreaseM) {
            monster.loseLives(decreaseM);
        } else monster.loseLives(monster.getLivesRemain());
    }

    public String getMonsterMove(){
        Round round = new Round(player, monster);
        monsterProperty = round.getMonsterProperty();
        return round.getMonsterString();
    }

    public void saveData(){
        fileSystem.save(UserManager.getInstance().getUsers(), "Users.ser");
        fileSystem.save(ScoreBoard.getInstance().getUserPlayers(), "ScoreBoard.ser");
    }


    private void nextRound(){
        roundNum ++;
    }

    public int getRoundNum(){
        return roundNum;
    }

    public int getPlayerLives(){
        return player.getLivesRemain();
    }

    public int gerMonsterLives(){
        return monster.getLivesRemain();
    }

    public int getPlayerAttack(){
        return player.getProperty().getAttack();
    }

    public int getPlayerDefence(){
        return player.getProperty().getDefence();
    }

    public int getPlayerFlexibility(){
        return player.getProperty().getFlexibility();
    }

    public int getPlayerLuckiness(){
        return player.getProperty().getLuckiness();
    }

    public void setMoveTrue(){
        moveStatus = true;
    }

    public void setMoveFalse(){
        moveStatus = false;
    }

    public void setCurStage(int stageNum){
        player.setCurStage(stageNum);
    }

    public FileSystem getFileSystem(){
        return fileSystem;
    }

    public boolean getMoveStatus(){
        return moveStatus;
    }


    public int checkLife(){
        if (monster.getLivesRemain() > 0 && player.getLivesRemain() > 0) {
            return 0;
        } else if (player.getLivesRemain() <= 0) {
            return 1;
        } else {
            return 2;
        }
    }

}
