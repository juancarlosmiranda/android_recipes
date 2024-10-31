package com.example.notification_03;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class MyBroadcastReceiver extends BroadcastReceiver {
    private static final String TAG = "MyBroadcastReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "MyBroadcastReceiver() ");
        StringBuilder sb = new StringBuilder();
        sb.append("Action: " + intent.getAction() + "\n");
        sb.append("URI: " + intent.toUri(Intent.URI_INTENT_SCHEME).toString() + "\n");
        String log = sb.toString();
        Log.d(TAG, log);
        if (intent.getAction().equals("ACTION_NOTIFICATION_01")) {
            // Realizar la acción deseada
            Toast.makeText(context, "Doing something with ACTION_NOTIFICATION_01", Toast.LENGTH_SHORT).show();
        }
        if (intent.getAction().equals("ACTION_NOTIFICATION_02")) {
            // Realizar la acción deseada
            Toast.makeText(context, "Doing something with ACTION_NOTIFICATION_02", Toast.LENGTH_SHORT).show();
        }

    }
}
