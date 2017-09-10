package com.example.b312967.quizapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by b312967 on 23.12.2015..
 */
public class QuizActivity extends Activity {

    List<Question> quesList;
    HashMap<String, List<Question>> questionMap = new HashMap<String, List<Question>>();
    int score=0;
    int questionCounter = 1;
    int listElementNumber = 1;

    TextView txtQuestion;
    RadioButton rda, rdb, rdc, rdd;
    Button butNext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        DataHandler dataHandler = new DataHandler();
        dataHandler.addQuestions();
        questionMap = dataHandler.getQuestionMap();
        for(Map.Entry<String, List<Question>> entry:questionMap.entrySet())
            Collections.shuffle(entry.getValue());

        txtQuestion=(TextView)findViewById(R.id.tv);
        rda=(RadioButton)findViewById(R.id.radioButton);
        rdb=(RadioButton)findViewById(R.id.radioButton2);
        rdc=(RadioButton)findViewById(R.id.radioButton3);
        rdd=(RadioButton)findViewById(R.id.radioButton4);

        butNext=(Button)findViewById(R.id.btnnext);
        Bundle extras = getIntent().getExtras();
        //String value =extras.getString("pozicija");
        final Integer pozicija=Integer.valueOf(extras.getString("pozicija"));
        //Log.d("position", pozicija + "");
        switch(pozicija) {
            case 0:
                setQuestionView("history", 0);
                break;
            case  1:
                setQuestionView("sport", 0);
                break;
            case 2:
                setQuestionView("geography", 0);
                break;
            case 3:
                setQuestionView("science", 0);
                break;
            case 4:
                setQuestionView("art", 0);
                break;
        }

        butNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioGroup grp=(RadioGroup)findViewById(R.id.radioGroup1);
                RadioButton answer=(RadioButton)findViewById(grp.getCheckedRadioButtonId());

                try {
                    if (pozicija == 0) {
                        validateAnswerAndGoToNext(answer.getText().toString(), grp, "history");
                    }
                    else if (pozicija == 1) {
                        validateAnswerAndGoToNext(answer.getText().toString(), grp, "sport");
                    }
                    else if (pozicija == 2) {
                        validateAnswerAndGoToNext(answer.getText().toString(), grp, "geography");
                    }
                    else if (pozicija == 3) {
                        validateAnswerAndGoToNext(answer.getText().toString(), grp, "science");
                    }
                    else if (pozicija == 4) {
                        validateAnswerAndGoToNext(answer.getText().toString(), grp, "art");
                    }
                }
                catch (NullPointerException e) {
                    Toast.makeText(getApplicationContext(),"Odgovor nije označen!",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void showToastCorrect(){
        Toast.makeText(getApplicationContext(), "Točan odgovor!", Toast.LENGTH_SHORT).show();
    }
    private void showToastIncorrect(){
        Toast.makeText(getApplicationContext(), "Netočan odgovor!", Toast.LENGTH_SHORT).show();

    }

    private void setQuestionView(String category, int brojElementaListe) {
        txtQuestion.setText(questionMap.get(category).get(brojElementaListe).getQUESTION());
        rda.setText(questionMap.get(category).get(brojElementaListe).getOPTA());
        rdb.setText(questionMap.get(category).get(brojElementaListe).getOPTB());
        rdc.setText(questionMap.get(category).get(brojElementaListe).getOPTC());
        rdd.setText(questionMap.get(category).get(brojElementaListe).getOPTD());
    }

    private void validateAnswerAndGoToNext(String answer, RadioGroup grp, String category){
        Log.d("odgovor", questionMap.get(category).get(listElementNumber +1).getQUESTION() + " answer " + answer);

        if (questionMap.get(category).get(listElementNumber -1).getANSWER().equals(answer)) {
            score++;
            showToastCorrect();
        }
        else
            showToastIncorrect();
        //GO TO NEXT ONE
        if (questionCounter < 5) {
            grp.clearCheck();
            setQuestionView(category, listElementNumber);
        } else {
            Intent intent = new Intent(QuizActivity.this, ResultActivity.class);
            Bundle b = new Bundle();
            b.putInt("score", score); //Your score
            intent.putExtras(b); //Put your score to your next Intent
            startActivity(intent);
            finish();
        }
        listElementNumber += 1;
        questionCounter++;
    }
}

