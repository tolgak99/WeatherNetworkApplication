package com.example.networkapplication.Activities.Activities;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.networkapplication.R;

public class MainActivity extends Activity {

    private EditText cityNameText;
    private Button showDataButton;
    private Button showSavedDataButton;

    private String cityName;

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
                saveCityNameToLocalData(cityNameText.getText().toString());
                navigateWeatherListActivity();
            }
        });
        showSavedDataButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navigateSavedWeatherActivity();
            }
        });

        cityName = getCityNameFromLocalData();

        if(cityName.length() > 0) {
            cityNameText.setText(cityName);
            navigateWeatherListActivity();
        }

    }

    private void navigateSavedWeatherActivity()
    {
        Intent savedWeatherActivityIntent = new Intent(MainActivity.this,SavedWeatherActivity.class);
        startActivity(savedWeatherActivityIntent);
    }

    private void saveCityNameToLocalData(String cityName)
    {
        //Validation
            this.cityName = cityName;
        // Save Data
        String CONST_DATA = "CITY_NAME";
        SharedPreferences preferences = this.getSharedPreferences(CONST_DATA, getApplicationContext().MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(CONST_DATA,String.valueOf(cityName));
        editor.apply();
    }

    private String getCityNameFromLocalData()
    {
        String result;
        String CONST_DATA = "CITY_NAME";
        SharedPreferences preferences = this.getSharedPreferences(CONST_DATA, getApplicationContext().MODE_PRIVATE);
        result = preferences.getString(CONST_DATA, "");
        return result;
    }

    private void navigateWeatherListActivity()
    {
        Intent weatherListActivityIntent = new Intent(MainActivity.this,WeatherListActivity.class);
        weatherListActivityIntent.putExtra("city_name",cityName);
        startActivity(weatherListActivityIntent);
    }
}