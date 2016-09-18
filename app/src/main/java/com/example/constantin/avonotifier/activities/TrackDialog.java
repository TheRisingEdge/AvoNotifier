package com.example.constantin.avonotifier.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;

import com.example.constantin.avonotifier.Globals;
import com.example.constantin.avonotifier.R;
import com.example.constantin.avonotifier.logic.Track;

public class TrackDialog extends DialogFragment implements DialogInterface.OnClickListener {
    EditText idText;

    public TrackDialog() { }

    public static TrackDialog newInstance() {
        TrackDialog dialog = new TrackDialog();
        Bundle args = new Bundle();
        dialog.setArguments(args);
        return dialog;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Activity activity = getActivity();

        LayoutInflater inflater = activity.getLayoutInflater();
        View view = inflater.inflate(R.layout.track_dialog, null);
        idText = (EditText)view.findViewById(R.id.dossieId);

        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setView(view)
            .setTitle(R.string.new_dossie)
            .setPositiveButton(R.string.ok, this)
            .setNegativeButton(R.string.cancel, null);

        Dialog dialog = builder.create();
        Window window = dialog.getWindow();
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);

        return dialog;
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        String id = idText.getText().toString();
        Track trackingData = new Track(id);
        Globals.dossieTracker.Track(trackingData);
    }
}
