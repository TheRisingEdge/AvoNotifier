package com.example.constantin.avonotifier.impl;

import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;
import com.example.constantin.avonotifier.logic.IAppMessages;

public class ToastMessages implements IAppMessages {
    Context context;
    int gravity;

    public ToastMessages(Context context) {
        this(context, Gravity.BOTTOM);
    }

    public ToastMessages(Context context, int gravity) {
        this.context = context;
        this.gravity = gravity;
    }

    @Override
    public void show(String message) {
        Toast toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
        toast.setGravity(gravity, 0, 0);
        toast.show();
    }
}
