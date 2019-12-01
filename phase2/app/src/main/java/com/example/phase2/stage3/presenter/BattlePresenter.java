package com.example.phase2.stage3.presenter;

import com.example.phase2.datamanagement.FileSystem;
import com.example.phase2.stage3.model.Monster;
import com.example.phase2.stage3.model.BattleModel;
import com.example.phase2.stage3.view.BattleView;
import com.example.phase2.appcore.game.Player;


public class BattlePresenter {
    private BattleView battleView;
    private BattleModel battleModel;
    private Player player;
    private Monster monster;
    private FileSystem fileSystem;


    public BattlePresenter(BattleView view, Monster monster, Player player, FileSystem fileSystem){
        battleView = view;
        battleModel = new BattleModel(player, monster);
        this.monster = monster;
        this.player = player;
        this.fileSystem = fileSystem;
    }

    public void battle(){
        int playerMove = battleView.getPlayerMove();
        battleModel.battle(playerMove);
    }

    public boolean moveOrNot(){
        return battleModel.getMoveStatus();
    }

    public void update(){
        battleView.update(battleModel.getRoundNum(), battleModel.getPlayerLives(), battleModel.gerMonsterLives());
        battleModel.setMoveFalse();
    }

    public void updateMoveStatus(){
        battleModel.setMoveTrue();
    }

    public int checkResult(){
        return battleModel.checkLife();
    }

    public void check(){
        battleView.check();
    }

    public void updateMonsterMove(){
        String monsterMove = battleModel.getMonsterMove();
        battleView.updateMonsterMove(monsterMove);

    }

    public int getPLayerAttack(){
        return battleModel.getPlayerAttack();
    }

    public int getPLayerDefence(){
        return battleModel.getPlayerDefence();
    }

    public int getPLayerFlexibility(){
        return battleModel.getPlayerFlexibility();
    }

    public int getPLayerLuckiness(){
        return battleModel.getPlayerLuckiness();
    }

    public int getPlayerLives(){
        return battleModel.getPlayerLives();
    }

    public int getMonsterLives(){
        return battleModel.gerMonsterLives();
    }


    public void savePlayerMove(int playerMove){
        battleModel.setPlayerMove(playerMove);
    }


    public void saveData(){
        battleModel.saveData(this.fileSystem);
    }

    public void setStage(int stageNum){
        player.setCurStage(stageNum);
    }


}
