package com.example.apk_kpa.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;


public class MainActivity extends Activity {

    //SectionsPagerAdapter mSectionsPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
    }

    //metodas register klases iskvietimui
    public void register (View view1) {
        startActivity(new Intent(this, Register.class));
    }

    //apsirasant metoda reik pirma sukurt klase, kad nemestu error, klases pavadinimas turi buti is didziosios raides (blyn!)
//    public void test(View view){
//        startActivity(new Intent(this, Admin_loged.class));
//    }
}
