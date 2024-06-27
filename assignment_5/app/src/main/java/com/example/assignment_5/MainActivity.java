package com.example.assignment_5;

import android.os.Bundle;
import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.assignment_5.fragment.HomeFragment;
import com.example.assignment_5.fragment.PostFragment;
import com.example.assignment_5.fragment.ProfileFragment;
import com.example.assignment_5.fragment.SearchFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;


public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    HomeFragment homeFragment = new HomeFragment();
    SearchFragment searchFragment = new SearchFragment();
    PostFragment postFragment = new PostFragment();
    ProfileFragment profileFragment = new ProfileFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView,homeFragment).commit();
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                if (menuItem.getItemId() == R.id.homeFragment) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView,homeFragment).commit();
                    return true;
                } else if(menuItem.getItemId() == R.id.searchFragment) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView, searchFragment).commit();
                    return true;
                } else if(menuItem.getItemId() == R.id.postFragment){
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView,postFragment).commit();
                    return true;
                } else if(menuItem.getItemId() == R.id.profileFragment) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView,profileFragment).commit();
                    return true;
                }
                return false;
            }
        });
    }
}