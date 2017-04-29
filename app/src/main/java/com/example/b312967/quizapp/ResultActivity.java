package com.example.b312967.quizapp;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
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
        //get rating bar object
        RatingBar bar=(RatingBar)findViewById(R.id.ratingBar);
        //get text view
        TextView t=(TextView)findViewById(R.id.tvresult);
        //get score
        Bundle b = getIntent().getExtras();
        int score= b.getInt("score");
        Log.d("rezultat", "" + score);
        //display score
        bar.setRating(score);
        switch (score) {
            case 0:
                t.setText("nula");
                break;
            case 1:
                t.setText("1/5");
                break;
            case 2:
                t.setText("2/5");
                break;
            case 3:
                t.setText("3/5");
                break;
            case 4:t.setText("4/5");
                break;
            case 5:t.setText("5/5");
                break;
        }
    }

}

