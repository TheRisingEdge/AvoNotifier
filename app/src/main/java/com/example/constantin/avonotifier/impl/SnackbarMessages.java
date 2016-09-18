package com.example.constantin.avonotifier.impl;

import android.support.design.widget.Snackbar;
import android.view.View;

import com.example.constantin.avonotifier.logic.IAppMessages;

public class SnackbarMessages implements IAppMessages {
    View viewParent;

    public SnackbarMessages(View viewParent) {
        this.viewParent = viewParent;
    }

    @Override
    public void show(String message) {
        Snackbar snackbar = Snackbar.make(viewParent, message, Snackbar.LENGTH_SHORT);
        snackbar.show();
    }
}
