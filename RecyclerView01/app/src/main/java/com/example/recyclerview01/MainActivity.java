package com.example.recyclerview01;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private static final String LOG_TAG="DD-MAIN";

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
        Log.d(LOG_TAG, "onCreate METHOD");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(LOG_TAG, "onStart METHOD");
    }
/*
    @Override
    protected void onResume() {
        Log.d(LOG_TAG, "onResume METHOD");
    }

    @Override
    protected void onPause() {
        Log.d(LOG_TAG, "onPause METHOD");
    }

    @Override
    protected void onStop() {
        Log.d(LOG_TAG, "onStop METHOD");
    }

    @Override
    protected void onRestart() {
        Log.d(LOG_TAG, "onRestart METHOD");
    }

    @Override
    protected void onDestroy() {
        Log.d(LOG_TAG, "onDestroy METHOD");
    }
*/

}