package com.example.theirfather;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {
    // TODO: Rename and change types of parameters
    private Button latestAudioBtn;
    private Button browseAudioBtn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        latestAudioBtn = view.findViewById(R.id.latestText);
        browseAudioBtn = view.findViewById(R.id.browseText);

        // setOnClickListener to the latestAudioButton
        latestAudioBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController navigation = Navigation.findNavController(getActivity(), R.id.homeFragment);
                navigation.navigate(R.id.action_homeFragment2_to_latestAudioFragment);
            }
        });

        // setOnClickListener to the browseAudio
        browseAudioBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController navigation = Navigation.findNavController(getActivity(), R.id.homeFragment);
                navigation.navigate(R.id.action_homeFragment2_to_browseAudioFragment);
            }
        });
    }
}
