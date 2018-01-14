package com.rontikeky.mycampus.otpblucampus.Activity;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.rontikeky.mycampus.otpblucampus.Config.PrefHandler;
import com.rontikeky.mycampus.otpblucampus.Fragment.EventFragment;
import com.rontikeky.mycampus.otpblucampus.R;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    FragmentManager fragmentManager;
    Fragment fragment;

    TextView tvEmailHeader, tvNameHeader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Inisialisasi
        Toolbar toolbar =   (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle("BLuCampus EO");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        PrefHandler.init(this);

        //DRAWER TOGGLE
        DrawerLayout drawerLayout   =   (DrawerLayout)findViewById(R.id.drawer_layout);
        //final ActionBarDrawerToggle toggle  =   new ActionBarDrawerToggle(this,drawerLayout,R.string.navigatio_drawer_open,R.string.navigation_drawer_close);
        //drawerLayout.setDrawerListener(toggle);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigatio_drawer_open, R.string.navigation_drawer_close)
        {

            public void onDrawerClosed(View view)
            {
                supportInvalidateOptionsMenu();
                //drawerOpened = false;
            }

            public void onDrawerOpened(View drawerView)
            {
                supportInvalidateOptionsMenu();
                //drawerOpened = true;
            }
        };
        toggle.setDrawerIndicatorEnabled(true);
        drawerLayout.setDrawerListener(toggle);
        toggle.syncState();

        //Drawer
        NavigationView navigationView   =   (NavigationView)findViewById(R.id.nav_view);
        View    view    =   navigationView.getHeaderView(0);
        Menu    menu    =   navigationView.getMenu();
//        MenuItem    target  =   menu.findItem(R.id.)

        tvEmailHeader   =   (TextView)view.findViewById(R.id.emailHeader);
        tvNameHeader    =   (TextView)view.findViewById(R.id.nameHeader);

        tvEmailHeader.setText(PrefHandler.getEmailKey());
        tvNameHeader.setText(PrefHandler.getNameKey());

        navigationView.setCheckedItem(R.id.nav_registered_event);

        navigationView.setNavigationItemSelectedListener(this);

        //Set Default Fragment yang ditampilkan
        fragmentManager =   getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.frame_container,new EventFragment()).commit();
    }


    //ONCLICK ITEM NAVIGATION DRAWER
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        NavigationView  navigationView  = (NavigationView)findViewById(R.id.nav_view);
        Menu    menu    =   navigationView.getMenu();
        int id  =   item.getItemId();

        fragment    =   null;

        if (id  ==  R.id.nav_registered_event){
            fragment    =   new EventFragment();
        }else if(id ==  R.id.nav_profile){
            fragment    =   new EventFragment();
        }else if(id ==  R.id.nav_logout){
            PrefHandler.setLogout();
            Toast.makeText(MainActivity.this, "Anda berhasil logout!",Toast.LENGTH_SHORT).show();
            finish();
        }

        if (fragment    !=  null){
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.frame_container, fragment).commit();
        }

        DrawerLayout    drawerLayout    =   (DrawerLayout)findViewById(R.id.drawer_layout);
        drawerLayout.closeDrawer(Gravity.START);
        return true;
    }
}
