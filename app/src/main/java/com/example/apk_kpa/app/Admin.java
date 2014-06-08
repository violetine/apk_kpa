package com.example.apk_kpa.app;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by Dofke on 2014-06-04.
 */
public class Admin extends FragmentActivity {

    ActionBarDrawerToggle icon;
    final String[] listContent ={"Fragment One","Fragment Two","Fragment Three","Main Page"};
    final String[] fragments ={
            "com.example.apk_kpa.app.FirstFragment",
            "com.example.apk_kpa.app.SecondFragment",
            "com.example.apk_kpa.app.ThirdFragment",
            "com.example.apk_kpa.app.MainPageFragment"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kurti_apklausa);
        Intent intent = new Intent(Admin.this,FragmentOnLoad.class);
        startActivity(intent);

        ArrayAdapter<String> ad = new ArrayAdapter<String>(getActionBar().getThemedContext(), android.R.layout.simple_list_item_1, listContent);

        final DrawerLayout drawer = (DrawerLayout)findViewById(R.id.drawer_layout);
        final ListView list = (ListView) findViewById(R.id.drawer);
        list.setAdapter(ad);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                drawer.setDrawerListener( new DrawerLayout.SimpleDrawerListener(){
                    @Override
                    public void onDrawerClosed(View drawerView){
                        super.onDrawerClosed(drawerView);
                        FragmentTransaction transition = getSupportFragmentManager().beginTransaction();
                        transition.replace(R.id.main, Fragment.instantiate(Admin.this, fragments[position]));
                        transition.commit();
                    }
                });
                drawer.closeDrawer(list);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;

    }
}
