package com.example.internationalization_01;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.content.res.Configuration;
import java.util.Locale;


public class MainActivity extends AppCompatActivity {
    public static final String LOG_TAG = "i18";
    private Spinner spinner;
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
        // -----------------------------------------
        // Widget definitions
        spinner = findViewById(R.id.spinner);
        resultText = findViewById(R.id.result_text);
        // -----------------------------------------
        // Spinner Listener
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                updateResult(); // Used to update results on the screen.
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // No action
                Log.i(LOG_TAG, LOG_TAG + " -> onNothingSelected");
            }
        });
    }

    // ------------------------------------
    private void updateResult() {
        // Used to update results on the screen.
        String selectedItem = spinner.getSelectedItem().toString();
        String result = "Selected: " + selectedItem;
        resultText.setText(result);
        Log.i(LOG_TAG,"spinnerSelected = "+spinner.getSelectedItemId());
        // ---------------------------------
        if (spinner.getSelectedItemId()==1){
            Log.i(LOG_TAG,"opcion 1");
            setLanguage("es");
        }
        if (spinner.getSelectedItemId()==2){
            Log.i(LOG_TAG,"opcion 2");
            setLanguage("en");
        }
        spinner.setSelection(0); // set option to Zero
        // ---------------------------------
        Log.i(LOG_TAG,"UPDATE RESULT HERE");
        // ---------------------------------
    }

    // ------------------------------------
    public void setLanguage(String language){
        Locale locale = new Locale(language);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.setLocale(locale);
        getResources().updateConfiguration(config, getResources().getDisplayMetrics());
        recreate();
    }
}