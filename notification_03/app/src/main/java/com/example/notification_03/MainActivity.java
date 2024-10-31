package com.example.notification_03;

import android.Manifest;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


public class MainActivity extends AppCompatActivity {
    public static final String LOG_TAG = "dd-notification";
    public static final String CHANNEL_ID = "test";
    public static final int NOTIFICATION_ID = 1;
    private static final String ACTION_BUTTON_CLICK_01 = "ACTION_NOTIFICATION_01";
    private static final String ACTION_BUTTON_CLICK_02 = "ACTION_NOTIFICATION_02";

    private final ActivityResultLauncher<String> activityResultLauncher = registerForActivityResult(new ActivityResultContracts.RequestPermission(), new ActivityResultCallback<Boolean>() {
        @Override
        public void onActivityResult(Boolean o) {
            if (o) {
                Toast.makeText(MainActivity.this, getString(R.string.toast_granted), Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(MainActivity.this, getString(R.string.toast_not_granted), Toast.LENGTH_SHORT).show();
            }
        }
    });

    NotificationChannel notificationChannel;
    NotificationCompat.Builder notificationBuilder;
    NotificationManager notificationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        // ----------------------------------------
        // Setting up a channel for notifications
        // ----------------------------------------
        createNotificationChannel();

        // ----------------------------------------
        // Managing user clicks
        // ----------------------------------------
        Button button01 = findViewById(R.id.button_01);
        button01.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                launchNotification();
            }
        });
        // --------------------------------------------
    }

    private void createNotificationChannel() {
        Log.i(LOG_TAG, "createNotificationChannel()");
        // -------------------------------------------
        //
        // -------------------------------------------
        // createNotificationChannel()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU && ActivityCompat.checkSelfPermission(MainActivity.this, android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            Log.i(LOG_TAG, "Ask user for permissions");
            activityResultLauncher.launch(Manifest.permission.POST_NOTIFICATIONS);
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                Log.i("LOG_TAG", "Build.VERSION.SDK_INT >= Build.VERSION_CODES.O");
                CharSequence name = getString(R.string.channel_name);
                String description = getString(R.string.channel_description);
                int importance = NotificationManager.IMPORTANCE_HIGH;

                Log.i(LOG_TAG, "NotificationChannel()");
                notificationChannel = new NotificationChannel(CHANNEL_ID, name, importance);
                notificationChannel.setDescription(description);
            }
        }
    }

    private void launchNotification() {
        Log.i(LOG_TAG, "launchNotification()");
        // -------------------------------------------
        //
        // -------------------------------------------
        Intent action01Intent = new Intent(this, MyBroadcastReceiver.class);
        action01Intent.setAction(ACTION_BUTTON_CLICK_01);
        PendingIntent action01PendingIntent = PendingIntent.getBroadcast(this, 0, action01Intent, PendingIntent.FLAG_IMMUTABLE);

        Intent action02Intent = new Intent(this, MyBroadcastReceiver.class);
        action02Intent.setAction(ACTION_BUTTON_CLICK_02);
        PendingIntent action02PendingIntent = PendingIntent.getBroadcast(this, 0, action02Intent, PendingIntent.FLAG_IMMUTABLE);

        Log.i(LOG_TAG, "Creating builder NotificationCompat.Builder()");
        notificationBuilder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle(getString(R.string.notification_title))
                .setContentText(getString(R.string.notification_content))
                .setStyle(new NotificationCompat.BigTextStyle().bigText("Much longer text that cannot fit one line..."))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .addAction(R.drawable.ic_action_01_foreground, getString(R.string.action_01), action01PendingIntent)
                .addAction(R.drawable.ic_action_02_foreground,  getString(R.string.action_02), action02PendingIntent);
        // ---------------------------
        // Creating NotificationManager()
        // --------------------------
        Log.i(LOG_TAG, "Creating NotificationManager()");
        notificationManager = getSystemService(NotificationManager.class);
        // ---------------------------
        // Launching notification
        // --------------------------
        notificationManager.createNotificationChannel(notificationChannel);
        notificationManager.notify(NOTIFICATION_ID, notificationBuilder.build());
        // ----------------------------------
        Log.i(LOG_TAG, "Build.VERSION.SDK_INT = " + Build.VERSION.SDK_INT);
        Log.i(LOG_TAG, "Build.VERSION_CODES.TIRAMISU = " + Build.VERSION_CODES.TIRAMISU);
        // ----------------------------------
    }
}