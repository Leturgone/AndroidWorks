package com.example.yasnecovfi4v2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportFragmentManager().beginTransaction().add(
                R.id.dinamic_fragment, SecondFragment.class,null).commit();

        Button StatButton = findViewById(R.id.fbutton);
        Button DinamButton = findViewById(R.id.sbutton);
        Button ElemButton = findViewById(R.id.tbutton);

        StatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                FirstFragment fragment1 = new FirstFragment();
                FragmentTransaction fragmentTransaction =
                        fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.dinamic_fragment, fragment1, "fragment1");
                fragmentTransaction.commit();
            }
        });
        DinamButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                SecondFragment fragment1 = new SecondFragment();
                FragmentTransaction fragmentTransaction =
                        fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.dinamic_fragment, fragment1, "fragment1");
                fragmentTransaction.commit();
            }
        });
        ElemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                ThirdFragment fragment1 = new ThirdFragment();
                FragmentTransaction fragmentTransaction =
                        fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.dinamic_fragment, fragment1, "fragment1");
                fragmentTransaction.commit();
            }
        });

    }

}