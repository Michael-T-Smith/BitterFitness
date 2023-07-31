package com.example.bitterfitness.data.model;

import android.widget.ImageView;
import android.widget.TextView;

public class WorkoutCategoryCard {
    private TextView mWorkoutName;
    private ImageView mWorkoutImage;

    public WorkoutCategoryCard(TextView mWorkoutName) {
        this.mWorkoutName = mWorkoutName;
    }

    public TextView getmWorkoutName() {
        return mWorkoutName;
    }
}
