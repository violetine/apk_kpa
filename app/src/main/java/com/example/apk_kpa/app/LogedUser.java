package com.example.apk_kpa.app;


import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Dofke on 2014-06-23.
 */
public class LogedUser extends Fragment {

    public String name;

    public LogedUser(){}

    public LogedUser(String name){
        this.name = name;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

//        String nickName = getArguments().getString("edttext");
        // -- inflate the layout for this fragment

        View myInflatedView = inflater.inflate(R.layout.loged_in, container,false);

        // Set the Text to try this out
        TextView t = (TextView) myInflatedView.findViewById(R.id.MyName);
        t.setText(name);

        return myInflatedView;

    }



}
