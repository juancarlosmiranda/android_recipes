package com.example.parameters_activities_01;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.parameters_activities_01.databinding.Fragment02Binding;

public class Fragment02 extends Fragment {

    private Fragment02Binding binding;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = Fragment02Binding.inflate(inflater, container, false);

        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // getting arguments
        Integer aIntArg = getArguments().getInt("aNumberB");
        String aIntStrArg = Integer.toString(aIntArg);
        String  aStrArg = getArguments().getString("aNameB");

        Log.d("FRAGMENT_02","RECEIVING ARGUMENTS " + aIntArg);
        Log.d("FRAGMENT_02","RECEIVING ARGUMENTS " + aStrArg);

        // loading data into wiev's widgets
        binding.editTextNumber.setText(aIntStrArg);
        binding.editTextText.setText(aStrArg);

        binding.btnReturn.setOnClickListener(v ->
                NavHostFragment.findNavController(Fragment02.this)
                        .navigate(R.id.action_Fragment02_to_Fragment01)
        );
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}