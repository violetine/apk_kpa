package com.example.apk_kpa.app;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;


public class MainActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        startActivity(new Intent(this, Login.class));
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
    }



    public void loged_in (View view2) {

        startActivity(new Intent(this, LogedIn.class));
    }

    public void logIn (View view3){
        startActivity(new Intent(this, Login.class));
    }


//    public void test(View view){
//        startActivity(new Intent(this, Admin_loged.class));
//    }
}
