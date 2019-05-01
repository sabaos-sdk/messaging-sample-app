package com.sabaos.core.init;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import com.sabaos.core.service.WebSocketService;
import com.sabaos.messaging.messaging.Settings;

public class BootCompletedReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Settings settings = new Settings(context);

        if (!settings.tokenExists()) {
            return;
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            context.startForegroundService(new Intent(context, WebSocketService.class));
        } else {
            context.startService(new Intent(context, WebSocketService.class));
        }
    }
}
