package com.example.notification_02;

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
        // Managing user clics
        // ----------------------------------------
        Button button01 = findViewById(R.id.button_01);
        button01.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                launchNotification();
            }
        });
        // --------------------------------------------
    }
    private void launchNotification(){
        Log.i(LOG_TAG, "launchNotification()");
        // --------------------------------------------
        // Notification
        // ----------------------------------------
        Intent mainIntent = new Intent(getApplicationContext(), NotiActivity.class);

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, mainIntent, PendingIntent.FLAG_IMMUTABLE);
        Log.i(LOG_TAG, "Creating builder NotificationCompat.Builder()");
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle(getString(R.string.app_name))
                .setContentText(getString(R.string.notification_content))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent);

        Log.i(LOG_TAG, "Creating NotificationManager()");
        NotificationManager notificationManager = getSystemService(NotificationManager.class);
        // ----------------------------------
        Log.i(LOG_TAG, "Build.VERSION.SDK_INT = "+ Build.VERSION.SDK_INT);
        Log.i(LOG_TAG, "Build.VERSION_CODES.TIRAMISU = "+ Build.VERSION_CODES.TIRAMISU);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU && ActivityCompat.checkSelfPermission(MainActivity.this, android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            Log.i(LOG_TAG, "Ask user for permissions");
            activityResultLauncher.launch(Manifest.permission.POST_NOTIFICATIONS);
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                Log.i("LOG_TAG", "Build.VERSION.SDK_INT >= Build.VERSION_CODES.O");
                CharSequence name = getString(R.string.app_name);
                String description = getString(R.string.notification_description);
                int importance = NotificationManager.IMPORTANCE_HIGH;

                Log.i(LOG_TAG, "NotificationChannel()");
                NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID, name, importance);

                Log.i(LOG_TAG, "Launching notifications");
                notificationChannel.setDescription(description);
                notificationManager.createNotificationChannel(notificationChannel);
                notificationManager.notify(10, notificationBuilder.build());
            }
        }
        // ----------------------------------
    }
}