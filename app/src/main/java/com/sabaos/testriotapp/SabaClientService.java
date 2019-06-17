package com.sabaos.testriotapp;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.NotificationCompat;

import com.sabaos.messaging.ClientService;


public class SabaClientService extends ClientService {

    @Override
    public void handleMessage(String data){

        // the data parameter is the message. You can manipulate it as you wish.
        final String data1 = data;
        //this code in used to display received message as a notification.
        //since we're in the background, we need to use Looper to access UI
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                NotificationManager notificationManager;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    CharSequence name = "My_channel1";
                    String description = "Saba_channel1";
                    int importance = NotificationManager.IMPORTANCE_DEFAULT;
                    NotificationChannel channel = new NotificationChannel("41", name, importance);
                    channel.setDescription(description);
                    // Register the channel with the system; you can't change the importance
                    // or other notification behaviors after this
                    notificationManager = getSystemService(NotificationManager.class);
                    notificationManager.createNotificationChannel(channel);
                    NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), "41")
                            .setSmallIcon(com.sabaos.messaging.R.mipmap.ic_launcher)
                            .setContentTitle("testRiotApp")
                            .setContentText(data1)
                            .setAutoCancel(false);
                    notificationManager.notify(1, builder.build());
                } else {

                    NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), "123")
                            .setSmallIcon(com.sabaos.messaging.R.mipmap.ic_launcher)
                            .setContentTitle("testRiotApp")
                            .setContentText(data1)
                            .setAutoCancel(false);
                    notificationManager = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
                    notificationManager.notify(1, builder.build());
                }
            }
        });
    }
}
