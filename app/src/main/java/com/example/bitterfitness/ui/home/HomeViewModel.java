package com.example.bitterfitness.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

public class HomeViewModel extends ViewModel {

    private ArrayList<String> mWorkoutCategories;
    private final MutableLiveData<String> mWorkoutCategoryTitle;

    public HomeViewModel() {
        mWorkoutCategoryTitle = new MutableLiveData<>();
        mWorkoutCategories = new ArrayList<>();
        mWorkoutCategoryTitle.setValue("Workout Categories");

    }

    public LiveData<String> getWorkoutTitleText() {
        return mWorkoutCategoryTitle;
    }
}