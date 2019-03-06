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
 * Activity where final result is shown.
 */
public class ResultActivity extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result);
        TextView textView=(TextView)findViewById(R.id.tvresult);
        Button returnButton = (Button)findViewById(R.id.returnButton);
        Bundle bundle = getIntent().getExtras();
        int score= bundle.getInt("score");
        Log.d("Result", "" + score);
        textView.setText("Točno je odgovoreno na " + score + " od 5 pitanja");

        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }

}

