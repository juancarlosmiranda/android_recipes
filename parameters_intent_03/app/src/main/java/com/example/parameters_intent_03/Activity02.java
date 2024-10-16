package com.example.parameters_intent_03;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class Activity02 extends AppCompatActivity {
    public static final String LOG_TAG = "ACTIVITY_02";
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
        Log.i(LOG_TAG, LOG_TAG+"->"+"onCreate(Bundle savedInstanceState)");
        Log.i(LOG_TAG, LOG_TAG+"->"+"myString = " + myStringP);
        // -----------------------
        TextView resultsTextView = findViewById(R.id.resultsTextView);
        resultsTextView.setText(myStringP);
        resultsTextView.getText();
        // -----------------------
        Button buttonReturn = findViewById(R.id.buttonReturn);
        buttonReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Log.i(LOG_TAG, LOG_TAG+"->"+"onClick(View view)");
                Log.i(LOG_TAG, LOG_TAG+"->"+"Returning values");
                Intent returnIntent = new Intent();
                returnIntent.putExtra("MY_RESULT", "Mi valor de retorno");
                setResult(AppCompatActivity.RESULT_OK, returnIntent);
                finish();
                // -----------------------
            }
        });



        // -----------------------
    }
}