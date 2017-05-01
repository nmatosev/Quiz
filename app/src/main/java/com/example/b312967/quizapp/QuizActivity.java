package com.example.b312967.quizapp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Created by b312967 on 23.12.2015..
 */
public class QuizActivity extends Activity {

    List<Category> quesList;
    List<Povijest> quesListPovijest;
    List<Sport> quesListSport;
    List<Zemljopis> quesListZemljopis;
    List<Znanost> quesListZnanost;
    List<Film> quesListFilm;
    List<Glazba> quesListGlazba;
    List<IT> quesListIT;
    int score=0;
    int qid=0;
    int brojac_pitanja=0;
    int id1=0;

    Povijest currentQPovijest;
    Sport currentQSport;
    Zemljopis currentQZemljopis;
    Znanost currentQZnanost;
    Film currentQFilm;
    Glazba currentQGlazba;
    IT currentQIT;
    Category currentQ;

    TextView txtQuestion;
    RadioButton rda, rdb, rdc, rdd;
    Button butNext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        final ArrayList<Integer> integerArrayList = new ArrayList<Integer>();
        for (int i=0; i<5; i++) {
            integerArrayList.add(new Integer(i));
        }
        Collections.shuffle(integerArrayList);
        int random=0;

        DataHandler dataHandler = new DataHandler();
        dataHandler.addQuestions();
        /////////////////////////////////////////PRAZNE LISTE KOJE SE PUNE POMOCU SELECT UPITA u datahandleru
        quesList = dataHandler.getAllQuestions();
        Log.d("quesList",quesList.toString());
        for (Category c : quesList) {
            if(c.getCATEGORY().equals("history")){
                currentQPovijest = (Povijest) c;
                quesListPovijest.add(currentQPovijest);
            }
            else if(c.getCATEGORY().equals("geography")){
                currentQZemljopis = (Zemljopis) c;
                quesListPovijest.add(currentQPovijest);

            }
            else if(c.getCATEGORY().equals("science")){
                currentQZnanost = (Znanost) c;
                quesListPovijest.add(currentQPovijest);

            }
            else if(c.getCATEGORY().equals("music")){
                currentQGlazba = (Glazba) c;
                quesListPovijest.add(currentQPovijest);

            }
            else if(c.getCATEGORY().equals("movie")){
                currentQFilm = (Film) c;
                quesListPovijest.add(currentQPovijest);

            }
            else if(c.getCATEGORY().equals("sport")){
                currentQSport = (Sport) c;
                quesListPovijest.add(currentQPovijest);

            }

        }




        currentQ=quesList.get(integerArrayList.get(random + 4));

        txtQuestion=(TextView)findViewById(R.id.tv);
        rda=(RadioButton)findViewById(R.id.radioButton);
        rdb=(RadioButton)findViewById(R.id.radioButton2);
        rdc=(RadioButton)findViewById(R.id.radioButton3);
        rdd=(RadioButton)findViewById(R.id.radioButton4);

        butNext=(Button)findViewById(R.id.btnnext);
        Bundle extras = getIntent().getExtras();
        String value =extras.getString("pozicija");
        final Integer pozicija=Integer.valueOf(value);//kastaj extra iz maina u int
        Log.d("position", pozicija + "");

        setQuestionView(pozicija);

        butNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioGroup grp=(RadioGroup)findViewById(R.id.radioGroup1);
                RadioButton answer=(RadioButton)findViewById(grp.getCheckedRadioButtonId());

            try {
                Log.d("odgovor", " " + answer.getText());

                if (pozicija == 0) {
                    if (currentQ.getANSWER().equals(answer.getText())) {
                        score++;
                        Log.d("score", "" + score);
                        Toast.makeText(getApplicationContext(), "Točan odgovor!", Toast.LENGTH_SHORT).show();
                    }
                    if (brojac_pitanja < 5) {
                        Log.d("lista", " " + integerArrayList);
                        Log.d("ID TRENUTNOG PITANJA", " " + integerArrayList.get(id1));
                        currentQ = quesList.get(integerArrayList.get(id1));
                        id1++;
                        setQuestionView(pozicija);
                    } else {
                        Intent intent = new Intent(QuizActivity.this, ResultActivity.class);
                        Bundle b = new Bundle();
                        Log.d("quiz finished", "" + "");
                        b.putInt("score", score); //Your score
                        intent.putExtras(b); //Put your score to your next Intent
                        startActivity(intent);
                        finish();
                    }
                } else if (pozicija == 1) {

                    if (currentQSport.getANSWER().equals(answer.getText())) {
                        score++;
                        Log.d("score", "" + score);
                    }
                    if (brojac_pitanja < 5) {

                        Log.d("lista", " " + integerArrayList);
                        Log.d("ID TRENUTNOG PITANJA", " " + integerArrayList.get(id1));
                        currentQSport = quesListSport.get(integerArrayList.get(id1));
                        id1++;

                        setQuestionView(pozicija);
                    } else {
                        Intent intent = new Intent(QuizActivity.this, ResultActivity.class);
                        Bundle b = new Bundle();
                        Log.d("quiz finished", "");
                        b.putInt("score", score); //Your score
                        intent.putExtras(b); //Put your score to your next Intent
                        startActivity(intent);
                        finish();
                    }
                } else if (pozicija == 2) {
                    if (currentQZemljopis.getANSWER().equals(answer.getText())) {
                        score++;
                        Log.d("score", "Your score" + score);
                    }
                    if (brojac_pitanja < 5) {
                        Log.d("lista", " " + integerArrayList);
                        Log.d("ID TRENUTNOG PITANJA", " " + integerArrayList.get(id1));
                        currentQZemljopis = quesListZemljopis.get(integerArrayList.get(id1));
                        id1++;
                        setQuestionView(pozicija);
                    } else {
                        Intent intent = new Intent(QuizActivity.this, ResultActivity.class);
                        Bundle b = new Bundle();
                        Log.d("quiz finished", "" + "");
                        b.putInt("score", score); //Your score
                        intent.putExtras(b); //Put your score to your next Intent
                        startActivity(intent);
                        finish();
                    }
                } else if (pozicija == 3) {
                    if (currentQZnanost.getANSWER().equals(answer.getText())) {
                        score++;
                        Log.d("score", "Your score" + score);
                    }
                    if (brojac_pitanja < 5) {
                        Log.d("lista", " " + integerArrayList);
                        Log.d("ID TRENUTNOG PITANJA", " " + integerArrayList.get(id1));
                        currentQZnanost = quesListZnanost.get(integerArrayList.get(id1));
                        id1++;
                        setQuestionView(pozicija);
                    } else {
                        Intent intent = new Intent(QuizActivity.this, ResultActivity.class);
                        Bundle b = new Bundle();
                        Log.d("quiz finished", "" + "");
                        b.putInt("score", score); //Your score
                        intent.putExtras(b); //Put your score to your next Intent
                        startActivity(intent);
                        finish();
                    }
                } else if (pozicija == 4) {

                    if (currentQFilm.getANSWER().equals(answer.getText())) {
                        score++;
                        Log.d("score", "Your score" + score);
                    }
                    if (brojac_pitanja < 5) {
                        Log.d("lista", " " + integerArrayList);
                        Log.d("ID TRENUTNOG PITANJA", " " + integerArrayList.get(id1));
                        currentQFilm = quesListFilm.get(integerArrayList.get(id1));
                        id1++;
                        setQuestionView(pozicija);
                    } else {
                        Intent intent = new Intent(QuizActivity.this, ResultActivity.class);
                        Bundle b = new Bundle();
                        Log.d("quiz finished", "" + "");
                        b.putInt("score", score); //Your score
                        intent.putExtras(b); //Put your score to your next Intent
                        startActivity(intent);
                        finish();
                    }
                } else if (pozicija == 5) {

                    if (currentQGlazba.getANSWER().equals(answer.getText())) {
                        score++;
                        Log.d("score", "Your score" + score);
                    }
                    if (brojac_pitanja < 5) {
                        Log.d("lista", " " + integerArrayList);
                        Log.d("ID TRENUTNOG PITANJA", " " + integerArrayList.get(id1));
                        currentQGlazba = quesListGlazba.get(integerArrayList.get(id1));
                        id1++;
                        setQuestionView(pozicija);
                    } else {
                        Intent intent = new Intent(QuizActivity.this, ResultActivity.class);
                        Bundle b = new Bundle();
                        Log.d("quiz finished", "" + "");
                        b.putInt("score", score); //Your score
                        intent.putExtras(b); //Put your score to your next Intent
                        startActivity(intent);
                        finish();
                    }
                } else if (pozicija == 6) {

                    if (currentQIT.getANSWER().equals(answer.getText())) {
                        score++;
                        Log.d("score", " " + score);
                    }
                    if (brojac_pitanja < 5) {
                        Log.d("lista", " " + integerArrayList);
                        Log.d("ID TRENUTNOG PITANJA", " " + integerArrayList.get(id1));
                        currentQIT = quesListIT.get(integerArrayList.get(id1));
                        id1++;
                        setQuestionView(pozicija);
                    } else {
                        Intent intent = new Intent(QuizActivity.this, ResultActivity.class);
                        Bundle b = new Bundle();
                        Log.d("quiz finished", "");
                        b.putInt("score", score); //Your score
                        intent.putExtras(b); //Put your score to your next Intent
                        startActivity(intent);
                        finish();
                    }
                }

            }
            catch (NullPointerException e) {
                Toast.makeText(getApplicationContext(),"Odgovor nije označen!",Toast.LENGTH_SHORT).show();
            }

            }
        });
    }

    private void setQuestionView(int pozicija) {
        for(int i=0;i<7;i++){
            if(pozicija==i){
                txtQuestion.setText(currentQ.getQUESTION());
                rda.setText(currentQ.getOPTA());
                rdb.setText(currentQ.getOPTB());
                rdc.setText(currentQ.getOPTC());
                rdd.setText(currentQ.getOPTD());
                brojac_pitanja++;
            }
        }
    }
}

