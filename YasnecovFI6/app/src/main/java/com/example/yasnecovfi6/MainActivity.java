package com.example.yasnecovfi6;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    public ActionBarDrawerToggle toggle;
    public DrawerLayout drawer;
    public Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Работа с тулбаром
        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(
                MainActivity.this, drawer, R.string.drawer_open, R.string.drawer_close);

        if (drawer != null){
            drawer.addDrawerListener(toggle);

        }

        //синхронизирует текущее состояние
        // drawerLayout (открыт или закрыт) с позицией связанной
        // с ним кнопки на панели действий (ActionBar).
        toggle.syncState();

        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null) {
            actionBar.setTitle("Постеры фильма");
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener(){

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment tempFragment = null;
                if (item.getItemId() == R.id.poster1){
                    //Обработка выбора раздела Постер 1
                    tempFragment = new Poster1Fragment();
                    actionBar.setSubtitle("Постер 1");
                }
                else if (item.getItemId() == R.id.poster2){
                    //Обработка выбора раздела Постер 2
                    tempFragment = new Poster2Fragment();
                    actionBar.setSubtitle("Постер 2");
                }
                else if (item.getItemId() == R.id.poster3){
                    //Обработка выбора раздела Постер 3
                    tempFragment = new Poster3Fragment();
                    actionBar.setSubtitle("Постер 3");
                }
                else if (item.getItemId() == R.id.on_next_avtivity){
                    //Обработка выбора раздела onNextAxtivity
                    Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                    startActivity(intent);
                }
                if (tempFragment != null) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment, tempFragment).commit();
                }
                return false;
            }
        });


    }

    // Обработка нажатия на иконку меню в ActionBar для открытия и закрытия Drawer
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(toggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}