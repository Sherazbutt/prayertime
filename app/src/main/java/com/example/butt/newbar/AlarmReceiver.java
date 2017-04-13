package com.example.butt.newbar;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

public class AlarmReceiver extends BroadcastReceiver
{
    private static final int MY_NOTIFICATION_ID = 1;
    NotificationManager notificationManager;
    Notification myNotification;
    Context context;
    private final String myBlog = "http://android-er.blogspot.com/";
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "I'm running", Toast.LENGTH_SHORT).show();
        notificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(myBlog));
        myIntent.setFlags(intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(
                context,
                144,
                myIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        PendingIntent pendingIntent1 = PendingIntent.getActivity(
                context,
                1050,
                myIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);



        /*AlertDialog.Builder builder = new AlertDialog.Builder();
        builder.setTitle("Exit");
        builder.setMessage("are you sure");
        builder.setPositiveButton("Turn OFF Alarm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub
            }
        });

        builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub

            }
        });


        AlertDialog alert = builder.create();
        alert.show();
    }*/
        //Uri sound = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.azan);
        //mBuilder.setSound(sound);

        myNotification = new NotificationCompat.Builder(context).setContentTitle("Exercise of Notification!")
                .setContentText("Prayer Time....!")
                .setTicker("Notification!")
                .setSound(Uri.parse("android.resource://"
                        + context.getPackageName() + "/" + R.raw.azan))
                .setWhen(System.currentTimeMillis())
                .setContentIntent(pendingIntent)
                //.setDefaults(Notification.DEFAULT_SOUND)
                .setAutoCancel(true)
                .setSmallIcon(R.drawable.dua)
                .build();


        notificationManager.notify(MY_NOTIFICATION_ID, myNotification);


        myNotification = new NotificationCompat.Builder(context).setContentTitle("Exercise of Notification!")
                .setContentText("Sun Rise....!")
                .setTicker("Notification!")
                .setSound(Uri.parse("android.resource://"
                        + context.getPackageName() + "/" + R.raw.azan))
                .setWhen(System.currentTimeMillis())
                .setContentIntent(pendingIntent1)
                //.setDefaults(Notification.DEFAULT_SOUND)
                .setAutoCancel(true)
                .setSmallIcon(R.drawable.prayer)
                .build();


        notificationManager.notify(MY_NOTIFICATION_ID, myNotification);

    }
}