package com.example.bitterfitness.data.model;

import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bitterfitness.R;

import java.util.ArrayList;

public class WorkoutCardAdapter extends  RecyclerView.Adapter<WorkoutCardAdapter.WorkoutCardViewHolder>{
    ArrayList<String> mWorkoutNames = new ArrayList<>();
    public WorkoutCardAdapter(ArrayList<String> mWorkoutNames) {
        this.mWorkoutNames = mWorkoutNames;
    }


    @NonNull
    @Override
    public WorkoutCardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new WorkoutCardViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_home, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull WorkoutCardViewHolder holder, int position) {
        holder.mWorkoutText.setText(mWorkoutNames.get(position));
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class WorkoutCardViewHolder extends RecyclerView.ViewHolder{
        private TextView mWorkoutText;

        public WorkoutCardViewHolder(@NonNull View itemView) {
            super(itemView);
            mWorkoutText = itemView.findViewById(R.id.text_WorkoutCategories);
        }
    }
}
