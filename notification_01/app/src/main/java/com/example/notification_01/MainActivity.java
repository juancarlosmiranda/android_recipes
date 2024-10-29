package com.example.notification_01;
import android.Manifest;
import android.app.NotificationChannel;
import android.app.NotificationManager;
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

/*
* Adapted from https://github.com/Everyday-Programmer/Basic-Notification-Android-Java
*
*
* */

public class MainActivity extends AppCompatActivity {
    public static final String LOG_TAG = "dd-notification";

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
        // --------------------------------------------
        // Notification
        // ----------------------------------------
        Log.i(LOG_TAG, "Creating builder NotificationCompat.Builder()");
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, "test")
                .setSmallIcon(R.drawable.ic_stat_name)
                .setContentTitle(getString(R.string.app_name))
                .setContentText(getString(R.string.notification_content))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        Log.i(LOG_TAG, "Creating NotificationManager()");
        NotificationManager notificationManager = getSystemService(NotificationManager.class);
        // ----------------------------------------
        // Managing user clics
        // ----------------------------------------
        Button button01 = findViewById(R.id.button_01);
        button01.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // ----------------------------------
                Log.i(LOG_TAG, "Build.VERSION.SDK_INT = "+ Build.VERSION.SDK_INT);
                Log.i(LOG_TAG, "Build.VERSION_CODES.TIRAMISU = "+ Build.VERSION_CODES.TIRAMISU);

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU && ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                    Log.i(LOG_TAG, "Ask user for permissions");
                    activityResultLauncher.launch(Manifest.permission.POST_NOTIFICATIONS);
                } else {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        Log.i("LOG_TAG", "Build.VERSION.SDK_INT >= Build.VERSION_CODES.O");
                        CharSequence name = getString(R.string.app_name);
                        String description = getString(R.string.notification_description);
                        int importance = NotificationManager.IMPORTANCE_DEFAULT;

                        Log.i(LOG_TAG, "NotificationChannel()");
                        NotificationChannel notificationChannel = new NotificationChannel("test", name, importance);

                        Log.i(LOG_TAG, "Launching notifications");
                        notificationChannel.setDescription(description);
                        notificationManager.createNotificationChannel(notificationChannel);
                        notificationManager.notify(10, notificationBuilder.build());
                    }
                }
            }
        });
        // --------------------------------------------
    }
}