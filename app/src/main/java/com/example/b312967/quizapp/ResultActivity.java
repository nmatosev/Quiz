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
        //get text view
        TextView t=(TextView)findViewById(R.id.tvresult);
        Button returnButton = (Button)findViewById(R.id.returnButton);
        //get score
        Bundle b = getIntent().getExtras();
        int score= b.getInt("score");
        Log.d("rezultat", "" + score);
        //display score
        //t.setText("Točno je odgovoreno na "+score+ " od 5 pitanja");
        switch (score) {
            case 0:
                t.setText("Bijedniče, svaki odgovor ti je kriv!");
                break;
            case 1:
                t.setText("Prejadnih 1 od 5 točnih!");
                break;
            case 2:
                t.setText("Bijedan si! Samo 2 od 5!");
                break;
            case 3:
                t.setText("Slučajno si naboja 3 od 5!");
                break;
            case 4:t.setText("Točno je odgovoreno na 4 od 5 pitanja!");
                break;
            case 5:t.setText("Točno je odgovoreno na svih 5 pitanja. P.S. Svejedno si bijednik :)");
                break;
        }
        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }

}

