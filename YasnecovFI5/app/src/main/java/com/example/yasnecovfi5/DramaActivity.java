package com.example.yasnecovfi5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class DramaActivity extends AppCompatActivity {

    String[] dramaList = {"Список Шиндлера","Ла ла лэнд", "500 дней лета","Городость и предубеждение"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drama);

        ListView dramaListView = (ListView) findViewById(R.id.drama_list);

        ArrayAdapter<String> dramaAdapter = new ArrayAdapter<>(this,android.R.layout.simple_expandable_list_item_1, dramaList);

        dramaListView.setAdapter(dramaAdapter);

        dramaListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //переход на активность:
                if (position == 1){
                    startActivity(new Intent(DramaActivity.this, ScrollActivity.class));
                }
            }
        });
    }
}