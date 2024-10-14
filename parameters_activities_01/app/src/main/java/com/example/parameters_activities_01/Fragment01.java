package com.example.parameters_activities_01;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.example.parameters_activities_01.databinding.Fragment01Binding;

public class Fragment01 extends Fragment {

    private Fragment01Binding binding;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = Fragment01Binding.inflate(inflater, container, false);
        binding.editTextText.setText("");

        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // setting listeners
        binding.btnSend.setOnClickListener(v -> btnClick());

    }

    @Override
    public void onDestroyView() {
        Log.d("BTN_CLICK", " onDestroyView() BYE BYE!");
        super.onDestroyView();
        binding = null;
    }

    public void btnClick(){
        Log.d("BTN_CLICK", "Updating values");
        // getting data from layout
        String aNumberStr = binding.editTextNumber.getText().toString();
        int aNumber = Integer.parseInt(aNumberStr);
        String aName = binding.editTextText.getText().toString();

        Log.d("BTN_CLICK", "aNumber = " + aNumberStr);
        Log.d("BTN_CLICK", "aName = " + aName);

        // preparing the bundle to send to another fragment
        Bundle bundle = new Bundle();
        bundle.putInt("aNumberB",aNumber);
        bundle.putString("aNameB",aName);

        // call to navigation controller
        NavController navController = NavHostFragment.findNavController(Fragment01.this);
        navController.navigate(R.id.action_Fragment01_to_Fragment02,bundle);
    }

}