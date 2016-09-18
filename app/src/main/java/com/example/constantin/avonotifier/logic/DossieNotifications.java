package com.example.constantin.avonotifier.logic;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.widget.RemoteViews;

import com.example.constantin.avonotifier.R;
import com.example.constantin.avonotifier.activities.DetailsActivity;
import com.example.constantin.avonotifier.activities.DetailsFragment;
import com.example.constantin.avonotifier.activities.TracksActivity;

import java.util.List;

public class DossieNotifications {
    Context context;
    Class<DetailsActivity> destActivity;
    AppDateFormatter meetingFormatter;

    public DossieNotifications(Context context,
                               Class<DetailsActivity> destActivity,
                               AppDateFormatter meetingFormatter) {
        this.context = context;
        this.destActivity = destActivity;
        this.meetingFormatter = meetingFormatter;
    }

    public void showUpdated(Meeting meeting) {
        String dossieId = meeting.getDossieId();
        String message = String.format("%s -> %s", dossieId, meetingFormatter.getDay(meeting.getTime()));

        Intent intent = new Intent(context, destActivity);
        intent.putExtra(DetailsFragment.DOSSIE_ID, dossieId);
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);

        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
        stackBuilder.addParentStack(destActivity);
        stackBuilder.addNextIntent(intent);

        PendingIntent pendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context)
            // .setSmallIcon(R.drawable.ic_media_play)
            .setContentTitle(context.getString(R.string.meetingNotificationTitle))
            .setContentText(message)
            .setTicker("Dosar Modificat")
            .setContentIntent(pendingIntent)
            .setAutoCancel(true);

        NotificationManager mNotificationManager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(0, mBuilder.build());
    }

    public void showUpcoming(List<Meeting> meetings) {
        RemoteViews contentView = new RemoteViews(context.getPackageName(), R.layout.upcoming_meetings);
        for (Meeting m: meetings) {
            RemoteViews mEntry = new RemoteViews(context.getPackageName(), R.layout.notification_entry);
            mEntry.setTextViewText(R.id.dossieId, m.getDossieId());
            mEntry.setTextViewText(R.id.dossieMeetingDate, meetingFormatter.getDay(m.getTime()));
            contentView.addView(R.id.entries, mEntry);
        }

        Class<TracksActivity> destActivity = TracksActivity.class;
        Intent intent = new Intent(context, destActivity);
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);

        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
        stackBuilder.addParentStack(destActivity);
        stackBuilder.addNextIntent(intent);
        PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context)
            // .setSmallIcon(R.drawable.ic_play_dark)
            .setContentTitle("Agenda")
            .setContentText("7 sedinte")
            // .setCustomBigContentView(contentView)
            .setContentIntent(resultPendingIntent)
            .setAutoCancel(false)
            .setOngoing(true);

        NotificationManager mNotificationManager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(2, mBuilder.build());
    }
}
