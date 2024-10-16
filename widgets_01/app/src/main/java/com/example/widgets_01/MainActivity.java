package com.example.widgets_01;

import android.os.Bundle;
import android.util.Log; // Log data

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

// widgets
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioGroup;
import android.widget.RadioButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public static final String LOG_TAG="MAIN_ACTIVITY";
    private CheckBox checkBox;
    private Button submitButton;
    private RadioGroup radioGroup;

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
        checkBox = findViewById(R.id.checkBox);
        radioGroup = findViewById(R.id.radioGroup);
        submitButton = findViewById(R.id.submitButton);
        // ------------------------------------
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // checkBox
                if (checkBox.isChecked()) {
                    Toast.makeText(MainActivity.this, "CHECKED IS TRUE!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "IT IS NOT CHECKED IS FALSE!", Toast.LENGTH_SHORT).show();
                }
                // radioButton
                int selectedRadioOption = radioGroup.getCheckedRadioButtonId();
                if (selectedRadioOption != -1){
                    //get selected radioButton
                    RadioButton selectedRadioButton = findViewById(selectedRadioOption);
                    String selectedText = selectedRadioButton.getText().toString();
                    Toast.makeText(MainActivity.this, "RADIO=" + selectedText, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "You should select an option!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        // ------------------------------------
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(LOG_TAG,LOG_TAG + " -> onResume()");
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