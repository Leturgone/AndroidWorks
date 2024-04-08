package com.example.yasnecovfi6;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
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
                MainActivity.this, drawer, R.string.drawler_open, R.string.drawler_close);

        if (drawer != null){
            drawer.addDrawerListener(toggle);
        }

        //синхронизирует текущее состояние
        // drawerLayout (открыт или закрыт) с позицией связанной
        // с ним кнопки на панели действий (ActionBar).
        toggle.syncState();

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        actionBar.setTitle("Постеры фильма");

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener(){

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.poster1){
                    //Обработка выбора раздела Постер 1
                    Toast.makeText(MainActivity.this,"Постер 1",Toast.LENGTH_LONG).show();
                }
                else if (item.getItemId() == R.id.poster2){
                    //Обработка выбора раздела Постер 1
                    Toast.makeText(MainActivity.this,"Постер 2",Toast.LENGTH_LONG).show();
                }
                else if (item.getItemId() == R.id.poster3){
                    //Обработка выбора раздела Постер 1
                    Toast.makeText(MainActivity.this,"Постер 3",Toast.LENGTH_LONG).show();
                }
                else if (item.getItemId() == R.id.on_next_avtivity){
                    //Обработка выбора раздела Постер 1
                    Toast.makeText(MainActivity.this,"on next activity",Toast.LENGTH_LONG).show();
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