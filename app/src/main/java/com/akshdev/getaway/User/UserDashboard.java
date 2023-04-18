package com.akshdev.getaway.User;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.akshdev.getaway.R;
import com.akshdev.getaway.User.UserResponse.MissingPlace;
import com.akshdev.getaway.User.UserResponse.NotifyDisparity;
import com.google.android.material.navigation.NavigationView;

public class UserDashboard extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    static final float END_SCALE = 0.7f;

    LinearLayoutCompat contentView;
    AppCompatButton dashMenu, dashAdd;
    DrawerLayout drawerLayout;
    public static NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dash_user_activity);

        //DashBoard Hooks
        contentView = findViewById(R.id.content);
        dashMenu = findViewById(R.id.dash_menu);
        dashAdd = findViewById(R.id.dash_add);

        //Navigation Drawer Hooks
        drawerLayout = findViewById(R.id.drawer_Layout);
        navigationView = findViewById(R.id.navigation_view);

        //Navigation Drawer
        navigationDrawer();


    }

    public void onBackPressed() {
        if (drawerLayout.isDrawerVisible(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    public void navigationDrawer() {

        navigationView.bringToFront();

        navigationView.setNavigationItemSelectedListener(this);

        navigationView.setCheckedItem(R.id.dash_nav_home);

        dashMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //if drawer is open the close
                if (drawerLayout.isDrawerVisible(GravityCompat.START)) {
                    drawerLayout.closeDrawer(GravityCompat.START);
                } else {
                    drawerLayout.openDrawer(GravityCompat.START);
                }
            }
        });
        animateNavigationDrawer();
    }

    private void animateNavigationDrawer() {

//        drawerLayout.setScrimColor(getColor(R.color.light_charcoal));

        drawerLayout.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                // Scale the View based on current slide offset
                final float diffScaledOffset = slideOffset * (1 - END_SCALE);
                final float offsetScale = 1 - diffScaledOffset;
                contentView.setScaleX(offsetScale);
                contentView.setScaleY(offsetScale);
                // Translate the View, accounting for the scaled width
                final float xOffset = drawerView.getWidth() * slideOffset;
                final float xOffsetDiff = contentView.getWidth() * diffScaledOffset / 2;
                final float xTranslation = xOffset - xOffsetDiff;
                contentView.setTranslationX(xTranslation);
            }
        });

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        if(id==R.id.dash_nav_missing_place){
            startActivity(new Intent(getApplicationContext(), MissingPlace.class));
        }
        if(id==R.id.dash_nav_notify_disparity){
            startActivity(new Intent(getApplicationContext(), NotifyDisparity.class));
        }

        return true;
    }
}