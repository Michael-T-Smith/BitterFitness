package com.example.bitterfitness.data;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewHolder extends RecyclerView.ViewHolder {
    TextView workoutText;
    ImageView image;

    public RecyclerViewHolder(@NonNull View itemView) {
        super(itemView);
        this.workoutText = workoutText;
        this.image = image;
    }
}
