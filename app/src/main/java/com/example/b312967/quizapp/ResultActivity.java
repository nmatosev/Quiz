package com.example.b312967.quizapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

/**
 * Created by b312967 on 23.12.2015..
 */
public class ResultActivity extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result);
        TextView t=(TextView)findViewById(R.id.tvresult);
        Button returnButton = (Button)findViewById(R.id.returnButton);
        Bundle b = getIntent().getExtras();
        int score= b.getInt("score");
        Log.d("Result", "" + score);
        t.setText("Točno je odgovoreno na " + score + " od 5 pitanja");

        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }

}

