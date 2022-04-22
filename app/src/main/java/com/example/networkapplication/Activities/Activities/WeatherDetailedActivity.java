package com.example.networkapplication.Activities.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.networkapplication.Activities.Entity.Result;
import com.example.networkapplication.R;

public class WeatherDetailedActivity extends AppCompatActivity {

    private Result result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_detailed);

        TextView day_textview = findViewById(R.id.day_textview);;
        TextView degree_textview = findViewById(R.id.degree_textview);;
        TextView date_textview = findViewById(R.id.date_textview);;
        Button save_button = findViewById(R.id.save_button);

        Bundle bundle = getIntent().getExtras();

        if(bundle != null)
        {
            result = bundle.getParcelable("result");
            date_textview.setText(result.getDate());
            day_textview.setText(result.getDay());
            degree_textview.setText(result.getDegree());
        }

        // Show result data at screen

        // When pressed save button save to db

        save_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveData();
            }
        });

    }

    private void saveData()
    {

    }

}