package com.example.networkapplication.Activities.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.networkapplication.R;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class WeatherListActivity extends AppCompatActivity {

    private String cityName = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_list);

        // Get City Name Information From Bundle

            Bundle bundle = getIntent().getExtras();
            if (bundle != null) {
                cityName = bundle.getString("city_name");
            }

        // Get Data From Network
            getWeatherListFromNetwork();
        // Show Data At Recycler View

    }

    private void getWeatherListFromNetwork()
    {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Request request = new Request.Builder()
                .url("https://api.collectapi.com/weather/getWeather?data.lang=tr&data.city=" + cityName)
                .method("GET", null)
                .addHeader("authorization", "apikey 2XzqtUByNqxTJfcfAbsS8r:3zFoFx3xowkgMZlpSfpv2N")
                .addHeader("content-type", "application/json")
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                Log.d("Fail: " , "Network Fail");
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if(response.isSuccessful())
                {
                    final String responseBody = response.body().string();
                    Log.e("Succes: " , responseBody);
                }
            }
        });
    }

}