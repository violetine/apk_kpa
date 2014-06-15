package com.example.apk_kpa.app;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Mano_duomenys extends Fragment {

    public Mano_duomenys(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.mano_duomenys, container, false);

        return rootView;
    }
}
