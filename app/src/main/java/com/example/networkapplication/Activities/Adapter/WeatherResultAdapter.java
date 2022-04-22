package com.example.networkapplication.Activities.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.networkapplication.Activities.Activities.WeatherDetailedActivity;
import com.example.networkapplication.Activities.Entity.Result;
import com.example.networkapplication.R;

import java.util.ArrayList;
import java.util.List;

public class WeatherResultAdapter extends RecyclerView.Adapter<WeatherResultAdapter.ViewHolder> {
    private List<Result> weatherResultItemList;
    private Result result;

    public WeatherResultAdapter(List<Result> weatherResultItemList) {
        this.weatherResultItemList = weatherResultItemList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.weather_list_item, viewGroup, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        result = weatherResultItemList.get(position);

        holder.date_textview.setText(result.getDate());
        holder.status_textview.setText(result.getDescription());
        holder.degree_value_textview.setText(result.getDegree());
        holder.degree_textview.setText("Derece");

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateDetailActivity(v.getContext());
            }
        });
    }

    @Override
    public int getItemCount() {
        return weatherResultItemList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView date_textview;
        private TextView status_textview;
        private TextView degree_value_textview;
        private TextView degree_textview;

        public ViewHolder(View v) {
            super(v);
            date_textview = v.findViewById(R.id.date_textview);
            status_textview = v.findViewById(R.id.status_textview);
            degree_value_textview = v.findViewById(R.id.degree_value_textview);
            degree_textview = v.findViewById(R.id.degree_textview);
        }
    }

    private void navigateDetailActivity(Context context)
    {
        Intent intent = new Intent(context,WeatherDetailedActivity.class);
        intent.putExtra("result",result);
        context.startActivity(intent);
    }
}
