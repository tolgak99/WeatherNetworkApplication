package com.example.networkapplication.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.networkapplication.R;

public class MainActivity extends Activity {

    private EditText cityNameText;
    private Button showDataButton;
    private Button showSavedDataButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cityNameText = findViewById(R.id.city_name_text);
        showDataButton = findViewById(R.id.show_weather_button);
        showSavedDataButton = findViewById(R.id.show_saved_weather_button);

        showDataButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navigateWeatherListActivity();
            }
        });

        showSavedDataButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navigateSavedWeatherActivity();
            }
        });

    }

    private void navigateSavedWeatherActivity()
    {
        Intent savedWeatherActivityIntent = new Intent(MainActivity.this,SavedWeatherActivity.class);
        startActivity(savedWeatherActivityIntent);
    }

    private void navigateWeatherListActivity()
    {
        Intent weatherListActivityIntent = new Intent(MainActivity.this,WeatherListActivity.class);
        //weatherListActivityIntent.putExtra();
        startActivity(weatherListActivityIntent);
    }
}