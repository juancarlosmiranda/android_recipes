package com.example.internationalization_02;
// TODO: add explanation about libraries
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.util.Log;

import java.util.Locale;

import android.content.res.Configuration;
import android.content.SharedPreferences;

public class MainActivity extends AppCompatActivity {
    private static final String PREFS_NAME = "prefs";
    private static final String PREF_LANGUAGE = "language";
    public static final String LOG_TAG = "dd-main";
    private Boolean isUserAction = false; // used to know when a user touch the spinner

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        // ------------------------------
        loadLocale();
        // ------------------------------
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        // --------------------------------------
        Spinner languageSpinner = findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.items_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        languageSpinner.setAdapter(adapter);

        // Determinar la posición del spinner según el idioma actual
        String currentLanguage = getSharedPreferences(PREFS_NAME, MODE_PRIVATE).getString(PREF_LANGUAGE, "en");

        // draw on Screen
        if (currentLanguage.equals("es")) {
            Log.i(LOG_TAG, "SETTING SPINNER ON SCREEN -> Spanish");
            languageSpinner.setSelection(0); // Español es la primera opción
        } else {
            Log.i(LOG_TAG, "SETTING SPINNER ON SCREEN -> English");
            languageSpinner.setSelection(1); // Inglés es la segunda opción
        }

        // Touched by user,
        // it solves the eternal reboot or recreate() activity
        languageSpinner.setOnTouchListener(new View.OnTouchListener() {
            // Based on https://stackoverflow.com/questions/56775164/onitemselectedlistener-spinner-recreate-loop-android
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                isUserAction = true;
                Log.i(LOG_TAG, "TOUCHED BY USER -> onTouch()");
                return false;
            }
        });

        //
        languageSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            boolean first_consumed = false;

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedLanguage = (String) parent.getItemAtPosition(position);
                // selectedLanguage.equals("English")
                Log.i(LOG_TAG, "onItemSelected -> position =" + position);
                if (position == 0) {
                    Log.i(LOG_TAG, "onItemSelected -> Spanish");
                    setLocale("es");
                } else if (position == 1) {
                    Log.i(LOG_TAG, "onItemSelected -> English");
                    setLocale("en");
                }
                // check .setOnTouchListener Method
                if (isUserAction) {
                    Log.i(LOG_TAG, "onItemSelected -> recreate()");
                    recreate();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // No hacer nada
                Log.i(LOG_TAG, LOG_TAG + " -> NOTHING SELECTED");
            }
        });
        // --------------------------------------
        Log.i(LOG_TAG, LOG_TAG + " -> AFTEEEEEEER!!!! languageSpinner.setOnItemSelectedListener");
        Log.i(LOG_TAG, LOG_TAG + " -> CLOSING");
        // --------------------------------------
    }

    private void setLocale(String lang) {
        Log.i(LOG_TAG, "setLocale(String lang) -> setting preferences = ");
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.setLocale(locale);
        getResources().updateConfiguration(config, getResources().getDisplayMetrics());

        // Guardar el idioma en SharedPreferences
        Log.i(LOG_TAG, "setLocale(String lang) -> saving preferences = ");

        SharedPreferences.Editor editor = getSharedPreferences(PREFS_NAME, MODE_PRIVATE).edit();
        editor.putString(PREF_LANGUAGE, lang);
        editor.apply();
    }

    private void loadLocale() {
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        String languagePref = prefs.getString(PREF_LANGUAGE, "en");

        Log.i(LOG_TAG, "loadLocale() -> language in SharedPreferences = " + languagePref);
        // -----------------------------
        Context context = getBaseContext();
        Configuration config = context.getResources().getConfiguration();

        String languageApp = config.locale.getLanguage();
        Log.i(LOG_TAG, "loadLocale() -> language in App config.locale.getLanguage() = " + languageApp);

        if (languageApp.equals(languagePref)) {
            Log.i(LOG_TAG, "language preference are equals NOTING TO DO = " + languageApp);
        } else {
            Log.i(LOG_TAG, "loading saved preferences = ");
            setLocale(languagePref);
        }
        // -----------------------------
    }

}