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


    TextView txtQuestion;
    RadioButton rda, rdb, rdc, rdd;
    Button btna,btnb,btnc,btnd;
    Button butNext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        final ArrayList<Integer> list1 = new ArrayList<Integer>();
        for (int i=0; i<5; i++) {
            list1.add(new Integer(i));
        }
        Collections.shuffle(list1);
        int random=0;

        DataHandler db=new DataHandler(this);
        /////////////////////////////////////////PRAZNE LISTE KOJE SE PUNE POMOCU SELECT UPITA u datahandleru
        quesListPovijest=db.getAllQuestionsPovijest();  ////"SELECT  * FROM " + TABLE_POVIJEST;
        quesListSport=db.getAllQuestionsSport();
        quesListZemljopis=db.getAllQuestionsZemljopis();
        quesListZnanost=db.getAllQuestionsZnanost();
        quesListFilm=db.getAllQuestionsFilm();
        quesListGlazba=db.getAllQuestionsGlazba();
        quesListIT=db.getAllQuestionsIT();

        currentQPovijest=quesListPovijest.get(list1.get(random + 4));
        currentQSport = quesListSport.get(list1.get(random + 4));
        currentQZemljopis = quesListZemljopis.get(list1.get(random + 4) );
        currentQZnanost = quesListZnanost.get(list1.get(random + 4) );
        currentQFilm = quesListFilm.get(list1.get(random + 4) );
        currentQGlazba = quesListGlazba.get(list1.get(random + 4) );
        currentQIT = quesListIT.get(list1.get(random + 4) );

        txtQuestion=(TextView)findViewById(R.id.tv);
        rda=(RadioButton)findViewById(R.id.radioButton);
        rdb=(RadioButton)findViewById(R.id.radioButton2);
        rdc=(RadioButton)findViewById(R.id.radioButton3);
        rdd=(RadioButton)findViewById(R.id.radioButton4);

        butNext=(Button)findViewById(R.id.btnnext);
        Bundle extras = getIntent().getExtras();
        String value =extras.getString("pozicija");
        final Integer pozicija=Integer.valueOf(value);//kastaj extra iz maina u int
        Log.d("pozicijaaa", pozicija + "");



        setQuestionView(pozicija);


        butNext.setOnClickListener(new View.OnClickListener() {




            @Override
            public void onClick(View v) {

                RadioGroup grp=(RadioGroup)findViewById(R.id.radioGroup1);
                RadioButton answer=(RadioButton)findViewById(grp.getCheckedRadioButtonId());

            try {

                Log.d("odgovor", " " + answer.getText());
                if (pozicija == 0) {

                    if (currentQPovijest.getANSWER().equals(answer.getText())) {
                        score++;
                        Log.d("score", "" + score);
                        Toast.makeText(getApplicationContext(), "Točan odgovor!", Toast.LENGTH_SHORT).show();
                    }
                    if (brojac_pitanja < 5) {

                        Log.d("lista", " " + list1);
                        Log.d("ID TRENUTNOG PITANJA", " " + list1.get(id1));
                        currentQPovijest = quesListPovijest.get(list1.get(id1));
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

                        Log.d("lista", " " + list1);
                        Log.d("ID TRENUTNOG PITANJA", " " + list1.get(id1));
                        currentQSport = quesListSport.get(list1.get(id1));
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


                        Log.d("lista", " " + list1);
                        Log.d("ID TRENUTNOG PITANJA", " " + list1.get(id1));
                        currentQZemljopis = quesListZemljopis.get(list1.get(id1));
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
                        Log.d("lista", " " + list1);
                        Log.d("ID TRENUTNOG PITANJA", " " + list1.get(id1));
                        currentQZnanost = quesListZnanost.get(list1.get(id1));
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
                        Log.d("lista", " " + list1);
                        Log.d("ID TRENUTNOG PITANJA", " " + list1.get(id1));
                        currentQFilm = quesListFilm.get(list1.get(id1));
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
                        Log.d("lista", " " + list1);
                        Log.d("ID TRENUTNOG PITANJA", " " + list1.get(id1));
                        currentQGlazba = quesListGlazba.get(list1.get(id1));
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


                        Log.d("lista", " " + list1);
                        Log.d("ID TRENUTNOG PITANJA", " " + list1.get(id1));
                        currentQIT = quesListIT.get(list1.get(id1));
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
            catch (NullPointerException e)
            {
                Toast.makeText(getApplicationContext(),"Odgovor nije označen!",Toast.LENGTH_SHORT).show();
            }




            }
        });


    }

    private void setQuestionView(int pozicija)
    {
        if(pozicija == 0) {

            txtQuestion.setText(currentQPovijest.getQUESTION());
            rda.setText(currentQPovijest.getOPTA());
            rdb.setText(currentQPovijest.getOPTB());
            rdc.setText(currentQPovijest.getOPTC());
            rdd.setText(currentQPovijest.getOPTD());


            brojac_pitanja++;

        }
        else if (pozicija == 1)
        {
            txtQuestion.setText(currentQSport.getQUESTION());
            rda.setText(currentQSport.getOPTA());
            rdb.setText(currentQSport.getOPTB());
            rdc.setText(currentQSport.getOPTC());
            rdd.setText(currentQSport.getOPTD());
            brojac_pitanja++;

        }

        else if (pozicija == 2)
        {
            txtQuestion.setText(currentQZemljopis.getQUESTION());
            rda.setText(currentQZemljopis.getOPTA());
            rdb.setText(currentQZemljopis.getOPTB());
            rdc.setText(currentQZemljopis.getOPTC());
            rdd.setText(currentQZemljopis.getOPTD());
            brojac_pitanja++;

        }

        else if (pozicija == 3)
        {
            txtQuestion.setText(currentQZnanost.getQUESTION());
            rda.setText(currentQZnanost.getOPTA());
            rdb.setText(currentQZnanost.getOPTB());
            rdc.setText(currentQZnanost.getOPTC());
            rdd.setText(currentQZnanost.getOPTD());
            brojac_pitanja++;

        }

        else if (pozicija == 4)
        {
            txtQuestion.setText(currentQFilm.getQUESTION());
            rda.setText(currentQFilm.getOPTA());
            rdb.setText(currentQFilm.getOPTB());
            rdc.setText(currentQFilm.getOPTC());
            rdd.setText(currentQFilm.getOPTD());
            brojac_pitanja++;

        }

        else if (pozicija == 5)
        {
            txtQuestion.setText(currentQGlazba.getQUESTION());
            rda.setText(currentQGlazba.getOPTA());
            rdb.setText(currentQGlazba.getOPTB());
            rdc.setText(currentQGlazba.getOPTC());
            rdd.setText(currentQGlazba.getOPTD());
            brojac_pitanja++;

        }

        else if (pozicija == 6)
        {

            txtQuestion.setText(currentQIT.getQUESTION());
            rda.setText(currentQIT.getOPTA());
            rdb.setText(currentQIT.getOPTB());
            rdc.setText(currentQIT.getOPTC());
            rdd.setText(currentQIT.getOPTD());
            brojac_pitanja++;

        }




    }
}

