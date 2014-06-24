package com.example.apk_kpa.app;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class Mano_duomenys extends Fragment {

    public String name;
    public String email;

    public Mano_duomenys(){}

    public Mano_duomenys(String name,String email){
        this.name = name;
        this.email = email;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Log.e("Aš čia, Email !!! arba ne :( ","" + email);

        View myInflatedView = inflater.inflate(R.layout.mano_duomenys, container,false);

        // Uzdedamas slapyvardis
        TextView a = (TextView) myInflatedView.findViewById(R.id.nickLbl);
        a.setText(name);

        // Uzdedamas el.pastsa
        TextView n = (TextView) myInflatedView.findViewById(R.id.pastasLbl);
        n.setText(email);



        return myInflatedView;

    }
}
