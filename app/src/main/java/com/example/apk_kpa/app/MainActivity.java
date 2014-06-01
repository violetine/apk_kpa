package com.example.apk_kpa.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;


public class MainActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startActivity(new Intent(this, Login.class));
    }



    public void loged_in (View view2) {

        startActivity(new Intent(this, LogedIn.class));
    }


//    public void test(View view){
//        startActivity(new Intent(this, Admin_loged.class));
//    }
}
