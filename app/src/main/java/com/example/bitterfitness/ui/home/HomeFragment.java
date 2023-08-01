package com.example.bitterfitness.ui.home;


import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import com.example.bitterfitness.R;
import com.example.bitterfitness.databinding.FragmentHomeBinding;

import java.util.ArrayList;
import java.util.Map;


public class HomeFragment extends Fragment {
    ArrayList<String> mWorkoutCategories;
    TextView[] mCateTextViews;
    ImageView[] mCateImageViews;
    private FragmentHomeBinding binding;
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context context;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        context = getContext();
        pref = getActivity().getSharedPreferences("BitterFitness", Context.MODE_PRIVATE);

        Map<String, String> userEmail =  (Map<String, String>) pref.getAll();

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        final TextView categoryTitle = binding.textWorkoutCategories;
        homeViewModel.getWorkoutTitleText().observe(getViewLifecycleOwner(), categoryTitle::setText);

        initImageViews();
        initTextViews();
        return root;
    }

    private void initImageViews() {
        mCateImageViews = new ImageView[10];
        mCateImageViews[0] = binding.imageCate1;
        mCateImageViews[1] = binding.imageCate2;
        mCateImageViews[2] = binding.imageCate3;
        mCateImageViews[3] = binding.imageCate4;
        mCateImageViews[4] = binding.imageCate5;
        mCateImageViews[5] = binding.imageCate6;
        mCateImageViews[6] = binding.imageCate7;
        mCateImageViews[7] = binding.imageCate8;
        mCateImageViews[8] = binding.imageCate9;
        mCateImageViews[9] = binding.imageCate10;
        InitCateImageItems();
    }
    private void InitCateImageItems() {
        mCateImageViews[0].setImageResource(R.drawable.push_pull);
        mCateImageViews[1].setImageResource(R.drawable.upper_lower);
        mCateImageViews[2].setImageResource(R.drawable.hiit);
        mCateImageViews[3].setImageResource(R.drawable.dumbbells);
        mCateImageViews[4].setImageResource(R.drawable.no_equipment);
        mCateImageViews[5].setImageResource(R.drawable.resistance_band);
        mCateImageViews[6].setImageResource(R.drawable.cardio);
        mCateImageViews[7].setImageResource(R.drawable.full_body);
        mCateImageViews[8].setImageResource(R.drawable.day_split_3);
        mCateImageViews[9].setImageResource(R.drawable.day_split_5);
        for (int i = 0; i < mCateImageViews.length; i++) {
            mCateImageViews[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getContext(), "Image Clicked", Toast.LENGTH_LONG).show();
                }
            });
        }
    }


    private void initTextViews() {
        mCateTextViews = new TextView[10];
        mCateTextViews[0] = binding.textCate1;
        mCateTextViews[1] = binding.textCate2;
        mCateTextViews[2] = binding.textCate3;
        mCateTextViews[3] = binding.textCate4;
        mCateTextViews[4] = binding.textCate5;
        mCateTextViews[5] = binding.textCate6;
        mCateTextViews[6] = binding.textCate7;
        mCateTextViews[7] = binding.textCate8;
        mCateTextViews[8] = binding.textCate9;
        mCateTextViews[9] = binding.textCate10;
        initCateTextItems();
    }

    private void initCateTextItems() {
        mWorkoutCategories = new ArrayList<>();
        mWorkoutCategories.add("Push-Pull");
        mWorkoutCategories.add("Upper-Lower");
        mWorkoutCategories.add("H.I.I.T");
        mWorkoutCategories.add("Dumbbells");
        mWorkoutCategories.add("No Equipment");
        mWorkoutCategories.add("Band");
        mWorkoutCategories.add("Cardio");
        mWorkoutCategories.add("Full body");
        mWorkoutCategories.add("3-Day Split");
        mWorkoutCategories.add("5-Day Split");

        for (int i = 0; i < 10; i++) {
            if(i < mWorkoutCategories.size()){
                mCateTextViews[i].setText(mWorkoutCategories.get(i));
                mCateTextViews[i].setTextColor(getResources().getColor(R.color.cateColor));
            } else {
                mCateTextViews[i].setText("Loading Error..");
                mCateTextViews[i].setTextColor(Color.RED);
            }
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


}