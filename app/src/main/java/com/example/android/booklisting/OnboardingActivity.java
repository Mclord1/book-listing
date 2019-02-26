package com.example.android.booklisting;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class OnboardingActivity extends AppCompatActivity {

    private ViewPager mSliderViewPager;
    private SliderAdapter sliderAdapter;
    private TextView prevButton, nextButton, beginButton;
    private ImageView indicator_0, indicator_1, indicator_2;
    private int mCurrentPage = 0;
    private static final String LOG_TAG = OnboardingActivity.class.getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onbard);

        // Reference the views
        mSliderViewPager = (ViewPager) findViewById(R.id.viewPager);
        prevButton = findViewById(R.id.prevButton);
        nextButton = findViewById(R.id.nextButton);

        indicator_0 = findViewById(R.id.indicator_0);
        indicator_1 = findViewById(R.id.indicator_1);
        indicator_2 = findViewById(R.id.indicator_2);

        // Initialize Views
        prevButton.setVisibility(View.GONE);
        indicator_0.setBackgroundResource(R.drawable.indicator_selected);
        sliderAdapter = new SliderAdapter(this);
        mSliderViewPager.setAdapter(sliderAdapter);
        mSliderViewPager.addOnPageChangeListener(viewListener);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSliderViewPager.setCurrentItem(mCurrentPage + 1);
            }
        });

        prevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSliderViewPager.setCurrentItem(mCurrentPage - 1);
            }
        });

    }


    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int i, float v, int i1) {

        }

        @Override
        public void onPageSelected(int i) {


            // Change the current position of the indicators
            updateIndicators(i);
            mCurrentPage = i;

//            Change the visibility of the buttons
            nextButton.setText(R.string.NextButton);
            nextButton.setId(R.id.nextButton);
            if (i == 0) {
                prevButton.setVisibility(View.GONE);
            } else {
                prevButton.setVisibility(View.VISIBLE);
            }

            if (i == 2) {
                nextButton.setText(R.string.BeginButton);
                nextButton.setId(R.id.beginButton);

                beginButton = findViewById(R.id.beginButton);

                if (beginButton != null) {
                    beginButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            finishOnboarding();
                        }
                    });
                }
            }


//            Log.i(LOG_TAG, "Got here, position: " + i);

        }

        @Override
        public void onPageScrollStateChanged(int i) {

        }
    };

    private void updateIndicators(int position) {

        switch (position) {
            case 0:
                indicator_0.setBackgroundResource(R.drawable.indicator_selected);
                indicator_1.setBackgroundResource(R.drawable.indicator_unselected);
                indicator_2.setBackgroundResource(R.drawable.indicator_unselected);
                break;
            case 1:
                indicator_1.setBackgroundResource(R.drawable.indicator_selected);
                indicator_0.setBackgroundResource(R.drawable.indicator_unselected);
                indicator_2.setBackgroundResource(R.drawable.indicator_unselected);
                break;
            case 2:
                indicator_2.setBackgroundResource(R.drawable.indicator_selected);
                indicator_0.setBackgroundResource(R.drawable.indicator_unselected);
                indicator_1.setBackgroundResource(R.drawable.indicator_unselected);
                break;
        }

    }

    public void finishOnboarding() {
        SharedPreferences preferences = getSharedPreferences("preferences", MODE_PRIVATE);

        // Set onboarding to true
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("firstStart", false);
        editor.apply();

        // Launch the main activity
        Intent main_activity = new Intent(this, MainActivity.class);
        startActivity(main_activity);

        // Close the onboarding activity
        finish();
    }

}
