package com.example.fundamentals_02;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Menu;

//import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.example.fundamentals_02.databinding.ActivityMainBinding;

// for activity calling
import android.content.Intent;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.appBarMain.toolbar);

        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_about) {
            Log.d(LOG_TAG, "Calling About");
            Toast.makeText(this, "Calling About", Toast.LENGTH_SHORT).show();
            //Intent intent = new Intent(this, AboutActivity.class);
            //startActivity(intent);
            return true;
        }

        if (id == R.id.action_settings) {
            Log.d(LOG_TAG, "Calling Settings");
            Toast.makeText(this, "Calling Settings", Toast.LENGTH_SHORT).show();
            //Intent intent = new Intent(this, SettingsActivity.class);
            //startActivity(intent);
            return true;
        }

        if (id == R.id.action_menu_01) {
            Log.d(LOG_TAG, "Calling Menu 01");
            Toast.makeText(this, "Calling Menu 01", Toast.LENGTH_SHORT).show();
            //Intent intent = new Intent(this, Menu01Activity.class);
            //startActivity(intent);
            return true;
        }

        if (id == R.id.action_menu_02) {
            Log.d(LOG_TAG, "Calling Menu 02");
            Toast.makeText(this, "Calling Menu 02", Toast.LENGTH_SHORT).show();
            //Intent intent = new Intent(this, Menu02Activity.class);
            //startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}