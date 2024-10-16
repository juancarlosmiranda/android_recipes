package com.example.parameters_intent_03;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.parameters_intent_03.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity {
    public static final String LOG_TAG = "MAIN_ACTIVITY";
    private ActivityMainBinding binding;
    public static final int REQUEST_CODE = 1234;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // EdgeToEdge.enable(this);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(R.layout.activity_main);
        Button buttonSend = findViewById(R.id.buttonSend);
        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Log.i(LOG_TAG, LOG_TAG+"->"+"onClick(View view)");
                Log.i(LOG_TAG, LOG_TAG+"->"+"Updating values");
                Intent intent = new Intent(view.getContext(), Activity02.class);
                // -----------------------
                TextView emisorTextView1 = findViewById(R.id.emisorTextView);
                //emisorTextView1.getText(); another way
                // -----------------------
                String myString = (String) binding.emisorTextView.getText(); //"FROM EMISOR";
                int myInteger = 81;
                // -----------------------
                // packing data into intent
                intent.putExtra("myTextValue", myString);
                intent.putExtra("myIntegerValue", myInteger);
                intent.putExtra("myResultValue", "ALGO");
                // -----------------------
                // calling a new activity
                //startActivity(intent);
                startActivityForResult(intent, REQUEST_CODE);//, TEXT_REQUEST);
                // -----------------------
            }
        });
    }
    // deprecated function
    // New method is explained by Google. https://developer.android.com/training/basics/intents/result#java
    public void onActivityResult(int requestCode, int resultCode,  Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.i(LOG_TAG, LOG_TAG+"->"+"onActivityResult()");
        Log.i(LOG_TAG, LOG_TAG+"->"+"requestCode="+requestCode);
        Log.i(LOG_TAG, LOG_TAG+"->"+"resultCode="+resultCode);
        Log.i(LOG_TAG, LOG_TAG+"->"+"data="+data);

        if (requestCode == REQUEST_CODE) {
            if (resultCode == AppCompatActivity.RESULT_OK) {
                // get Intent for results
                String reply = data.getStringExtra("MY_RESULT");
                // process data
                Log.i(LOG_TAG, LOG_TAG+"->"+"onActivityResult() TEXT_REQUEST & RESULT_OK -> "+reply);
            }

            if (resultCode == AppCompatActivity.RESULT_CANCELED) {
                Log.i(LOG_TAG, LOG_TAG+"->"+"onActivityResult() RESULT_CANCELED -> ");
            }
        }
    }

}