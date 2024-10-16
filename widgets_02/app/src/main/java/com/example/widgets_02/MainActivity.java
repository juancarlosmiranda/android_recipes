package com.example.widgets_02;

import android.os.Bundle;
import android.util.Log; // Log data

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

// widgets
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public static final String LOG_TAG = "MAIN_ACTIVITY";
    private Spinner spinner;
    private Switch switchButton;
    private TextView resultText;

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
        // ------------------------------------
        // Widget definitions
        spinner = findViewById(R.id.spinner);
        switchButton = findViewById(R.id.switch_button);
        resultText = findViewById(R.id.result_text);
        // ------------------------------------
        // -----------------------------------
        // Switch ON / OFF
        switchButton.setOnCheckedChangeListener((buttonView, isChecked) -> updateResult());

        // Spinner Listener
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                updateResult();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // No action
                Log.i(LOG_TAG, LOG_TAG + " -> onNothingSelected");
            }
        });
        // ------------------------------------
    }
    // ------------------------------------
    private void updateResult() {
        // Used to update results on the screen.
        String selectedItem = spinner.getSelectedItem().toString();
        boolean isSwitchOn = switchButton.isChecked();

        String result = "Selected: " + selectedItem + "\nSwitch is " + (isSwitchOn ? "ON" : "OFF");
        resultText.setText(result);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(LOG_TAG, LOG_TAG + " -> onResume()");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(LOG_TAG, LOG_TAG + "-> onStart()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(LOG_TAG, LOG_TAG + " -> onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(LOG_TAG, LOG_TAG + " -> onStop()");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(LOG_TAG, LOG_TAG + " -> onRestart()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(LOG_TAG, LOG_TAG + " -> onDestroy()");
    }
}