package com.example.android.booklisting;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Check if user has seen the onbarding screen using shared prefernce
        SharedPreferences preferences = getSharedPreferences("preferences", MODE_PRIVATE);
        boolean firstStart = preferences.getBoolean("firstStart", true);

        Log.i(LOG_TAG, "Got here " + firstStart);
        if (firstStart) {
            // Start the onboarding activity
            Intent onboarding_activity = new Intent(this, OnboardingActivity.class);
            startActivity(onboarding_activity);
        }

        setContentView(R.layout.activity_main);
//        Log.i(LOG_TAG, "Got here");


    }
}
