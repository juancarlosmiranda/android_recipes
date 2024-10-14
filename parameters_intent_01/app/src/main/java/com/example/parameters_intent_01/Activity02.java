package com.example.parameters_intent_01;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class Activity02 extends AppCompatActivity {
    public final static String EXTRA_RETURN_MESSAGE = "EXTRA_RETURN_MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_02);
        // -----------------------
        // unpacking intent data
        Bundle externalPar = getIntent().getExtras();
        String myStringP = externalPar.getString("myTextValue");
        int myIntegerP = externalPar.getInt("myIntegerValue");
        // -----------------------
        Log.d("ACTIVITY_02", "myString = " + myStringP);
        // -----------------------
        TextView resultsTextView = findViewById(R.id.resultsTextView);
        resultsTextView.setText(myStringP);
        // -----------------------
    }
}