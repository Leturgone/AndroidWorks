package com.example.yasnecovfi6;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;


public class SecondActivity extends AppCompatActivity {
    public Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        toolbar = findViewById(R.id.toolbar_second);
        setSupportActionBar(toolbar);
        Fragment homeFragment = new VaranFragment();
        ActionBar actionBar = getSupportActionBar();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_second,homeFragment).commit();

        if(actionBar != null) {
            actionBar.setTitle("Главная");
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        BottomNavigationView bottomNavigationView  = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment tempFragment = null;
                //Реализация нажатий на кнопки
                if (item.getItemId() == R.id.home){
                    //Обработка раздела Домой
                    tempFragment = homeFragment;
                    actionBar.setTitle("Главная");
                }
                else if (item.getItemId() == R.id.options){
                    //Обработка раздела Домой
                    tempFragment = new Varan2Fragment();
                    actionBar.setTitle("Настройки");
                }
                else if (item.getItemId() == R.id.notifications){
                    //Обработка раздела Домой
                    tempFragment = new Varan3Fragment();
                    actionBar.setTitle("Уведомления");
                }
                if(tempFragment != null){
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_second,tempFragment).commit();
                }
                return false;
            }
        });
    }
}
