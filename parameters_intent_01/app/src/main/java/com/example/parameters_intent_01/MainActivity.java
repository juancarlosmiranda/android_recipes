package com.example.parameters_intent_01;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.parameters_intent_01.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    public static final int TEXT_REQUEST = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // EdgeToEdge.enable(this);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(R.layout.activity_main);
        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Log.d("BTN_CLICK", "Updating values");
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
                // -----------------------
                // calling a new activity
                //startActivity(intent);
                startActivityForResult(intent, TEXT_REQUEST);
                // -----------------------
            }
        });
    }

    public void onActivityResult(int requestCode, int resultCode,  Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == TEXT_REQUEST) {
            if (resultCode == RESULT_OK) {
                String reply = data.getStringExtra(Activity02.EXTRA_RETURN_MESSAGE);
                // process data
                Log.i("MAIN_ACTIVITY", "Processing results ->");
            }
        }
    }

}