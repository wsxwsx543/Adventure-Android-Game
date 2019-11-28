package com.example.phase2.usersystem.presenters;

import android.content.Context;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.phase2.appcore.user.User;
import com.example.phase2.usersystem.models.SelectPlayerModel;
import com.example.phase2.usersystem.views.iview.SpinnerStringView;
import com.example.phase2.usersystem.views.iview.TextStringView;
import com.example.phase2.usersystem.views.iview.ToastStringView;

public class SelectPlayerPresenter {
    private SelectPlayerModel selectPlayerModel;
    private TextStringView textStringView;
    private SpinnerStringView spinnerStringView;
    private ToastStringView toastStringView;

    public SelectPlayerPresenter(SelectPlayerModel selectPlayerModel, TextStringView textStringView, SpinnerStringView spinnerStringView, ToastStringView toastStringView) {
        this.selectPlayerModel = selectPlayerModel;
        this.textStringView = textStringView;
        this.spinnerStringView = spinnerStringView;
        this.toastStringView = toastStringView;
    }

    public void showText(User user, String playerName, String stats, TextView textView) {
        textStringView.setText(textView, selectPlayerModel.playerStats(user, playerName, stats));
    }

    public void showPlayersSpinner(Context context, Spinner spinner, User user) {
        spinnerStringView.setSpinner(spinner, selectPlayerModel.playersAdapter(context, user));
    }

    public boolean showPlayerAvailableToast(User user, String playerName) {
        String result = selectPlayerModel.checkPlayerAvailable(user, playerName);
        toastStringView.setResult(result);
        return result.equals("Start game!");
    }
}
