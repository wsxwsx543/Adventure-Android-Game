package com.example.phase2.usersystem.presenters;

import android.content.Context;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.phase2.appcore.user.User;
import com.example.phase2.usersystem.models.SelectPlayerModel;
import com.example.phase2.usersystem.views.iview.ISpinnerStringView;
import com.example.phase2.usersystem.views.iview.ITextStringView;
import com.example.phase2.usersystem.views.iview.IToastStringView;

public class SelectPlayerPresenter {
    private SelectPlayerModel selectPlayerModel;
    private ITextStringView ITextStringView;
    private ISpinnerStringView ISpinnerStringView;
    private IToastStringView IToastStringView;

    public SelectPlayerPresenter(SelectPlayerModel selectPlayerModel, ITextStringView ITextStringView, ISpinnerStringView ISpinnerStringView, IToastStringView IToastStringView) {
        this.selectPlayerModel = selectPlayerModel;
        this.ITextStringView = ITextStringView;
        this.ISpinnerStringView = ISpinnerStringView;
        this.IToastStringView = IToastStringView;
    }

    public void showText(User user, String playerName, String stats, TextView textView) {
        ITextStringView.setText(textView, selectPlayerModel.playerStats(user, playerName, stats));
    }

    public void showPlayersSpinner(Context context, Spinner spinner, User user) {
        ISpinnerStringView.setSpinner(spinner, selectPlayerModel.playersAdapter(context, user));
    }

    public boolean showPlayerAvailableToast(User user, String playerName) {
        String result = selectPlayerModel.checkPlayerAvailable(user, playerName);
        IToastStringView.setResult(result);
        return result.equals("Start game!");
    }
}
