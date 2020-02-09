package com.example.b312967.quizapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.HashMap;

/**
 * Menu activity where user can scroll through categories.
 */
public class MainActivity extends AppCompatActivity {

    public static String[] categories = {"Povijest", "Sport", "Zemljopis", "Znanost", "Umjetnost", "Muzika", "Film"};
    public static HashMap<Integer, Category> listViewData = new HashMap<Integer, Category>();

    public static void fillData() {
        for (int i = 0; i < categories.length; i++) {
            Category category = new Category(i, categories[i]);
            listViewData.put(i, category);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView categoryListView = (ListView) findViewById(R.id.myListView);
        fillData();
        categoryListView.setAdapter(new Adapter(getApplicationContext()));

        categoryListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(view.getContext(), QuizActivity.class);
                intent.putExtra("listElement", String.valueOf(i));
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
