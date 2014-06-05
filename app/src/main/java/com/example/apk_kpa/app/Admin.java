package com.example.apk_kpa.app;

import android.app.Activity;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;

/**
 * Created by Dofke on 2014-06-04.
 */
public class Admin extends Activity implements View.OnClickListener {

    private View myView;
    private Vibrator vibr;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loged_in_admin);
        vibr = (Vibrator) this.getSystemService(VIBRATOR_SERVICE);

        myView = this.findViewById(R.id.kurtiApklausaBtn);
        myView.setOnClickListener(this);
    }
    @Override
    public void onClick(View v){
        vibr.vibrate(50);
    }


}
