package com.example.networkapplication.Activities.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.networkapplication.Activities.Adapter.WeatherResultAdapter;
import com.example.networkapplication.Activities.Entity.Result;
import com.example.networkapplication.Activities.Entity.WeatherResult;
import com.example.networkapplication.R;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class WeatherListActivity extends AppCompatActivity {

    private String cityName = "";
    private RecyclerView weather_list_recyclerview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_list);

        weather_list_recyclerview = findViewById(R.id.weather_list_recycleview);

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
                    Log.e("Success: " , responseBody);
                    WeatherResult weatherResult = new Gson().fromJson(responseBody,WeatherResult.class);

                    WeatherListActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            setAdapterRecyclerView(weatherResult.getResult());
                        }
                    });
                }
            }
        });
    }

    private void setAdapterRecyclerView(List<Result> resultList)
    {

        WeatherResultAdapter adapter = new WeatherResultAdapter(resultList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        weather_list_recyclerview.setLayoutManager(mLayoutManager);
        weather_list_recyclerview.setAdapter(adapter);


        //weather_list_recyclerview
        //adapter
    }

}