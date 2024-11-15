package com.example.android_room_01;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.room.Room;

// ----------------------
// DATABASE
import java.time.LocalDate;

import database.AppDatabase;
import database.Author;
import database.AuthorDao;

public class MainActivity extends AppCompatActivity {
    public static String LOG_TAG = "DD-MAIN_ACTIVITY";

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
        // -----------------------------
        // Context of the app under test.
        Context appContext = getApplicationContext();
        // -----------------------------
        AppDatabase db = Room.databaseBuilder(appContext, AppDatabase.class, "database-name").build();
        AuthorDao authorDao = db.authorDao(); // to improve
        authorDao.deleteAll();
        // -----------------------------
        Author authorReg01 = new Author("Alice", "Miranda", LocalDate.of(1981,01,01));
        Author authorReg02 = new Author("Bob", "Miranda", LocalDate.of(1981,02,02));
        // ------------------------------
        Log.i(LOG_TAG, "INSERTING DATA WITH DAO->");
        authorDao.insertAll(authorReg01, authorReg02);
        // ------------------------------
    }
}