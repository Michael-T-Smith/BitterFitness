package com.example.bitterfitness;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.bitterfitness.databinding.ActivityHomeBinding;

public class HomeActivity extends AppCompatActivity {
    SharedPreferences pref;
    SQLiteManager sqLiteManager;

    private ActivityHomeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pref = getSharedPreferences("BitterFitness", Context.MODE_PRIVATE);
        SharedPreferences.Editor prefEditor = pref.edit();
        boolean activeUser = pref.getBoolean("activeUser", false);
        Log.e("Existing User?", "" + activeUser);

        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        BottomNavigationView navView = findViewById(R.id.nav_view);
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_home);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);
        sqLiteManager = loadDBFromMemory();

    }

    private SQLiteManager loadDBFromMemory() {
        SQLiteManager sqLiteManager = SQLiteManager.instanceOfDatabase(this);
        return sqLiteManager;
    }
}