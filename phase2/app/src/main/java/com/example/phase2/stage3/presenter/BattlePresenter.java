package com.example.phase2.stage3.presenter;

import android.content.Context;

import com.example.phase2.stage3.model.BattleModel;
import com.example.phase2.stage3.view.BattleView;


public class BattlePresenter {
    private BattleView battleView;
    private BattleModel battleModel;


    public BattlePresenter(Context context, BattleView view){
        battleView = view;
        battleModel = new BattleModel(context);
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

    public int getPLayerDefence(){ return battleModel.getPlayerDefence(); }

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

    public void saveData(){
        battleModel.saveData();
    }

    public void setStage(int stageNum){ battleModel.setCurStage(stageNum); }


}
