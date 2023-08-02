package com.example.bitterfitness.ui.notifications;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.bitterfitness.HomeActivity;
import com.example.bitterfitness.MainActivity;
import com.example.bitterfitness.databinding.FragmentNotificationsBinding;

public class NotificationsFragment extends Fragment {

    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;
    private FragmentNotificationsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        NotificationsViewModel notificationsViewModel =
                new ViewModelProvider(this).get(NotificationsViewModel.class);
        prefs = getActivity().getSharedPreferences("BitterFitness", Context.MODE_PRIVATE);
        editor = prefs.edit();
        binding = FragmentNotificationsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final Button signoutBtn = binding.btnSignOut;
        signoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signoutClicked();
            }
        });
        return root;
    }
private void signoutClicked(){
    editor.putBoolean("activeUser", false);
    editor.apply();
    Intent intent = new Intent(getContext(), MainActivity.class);
    startActivity(intent);
    onDestroyView();
}
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}