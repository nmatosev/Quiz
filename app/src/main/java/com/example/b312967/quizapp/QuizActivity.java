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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by b312967 on 23.12.2015..
 */
public class QuizActivity extends Activity {

    List<Category> quesList;
    List<Povijest> quesListPovijest = new ArrayList<Povijest>();
    List<Sport> quesListSport = new ArrayList<Sport>();
    List<Zemljopis> quesListZemljopis =new ArrayList<Zemljopis>();
    List<Znanost> quesListZnanost  = new ArrayList<Znanost>();
    List<Glazba> quesListGlazba = new ArrayList<Glazba>();
    ArrayList<Integer> randomListHistory = new ArrayList<Integer>();
    ArrayList<Integer> randomListGeography = new ArrayList<Integer>();
    ArrayList<Integer> randomListSport = new ArrayList<Integer>();
    ArrayList<Integer> randomListScience = new ArrayList<Integer>();
    ArrayList<Integer> randomListMusic = new ArrayList<Integer>();

    int score=0;
    int brojacPitanja = 1;
    int brojElementa = 1;

    Povijest currentQPovijest;
    Sport currentQSport;
    Zemljopis currentQZemljopis;
    Znanost currentQZnanost;
    Glazba currentQGlazba;

    TextView txtQuestion;
    RadioButton rda, rdb, rdc, rdd;
    Button butNext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        DataHandler dataHandler = new DataHandler();
        dataHandler.addQuestions();
        /////////////////////////////////////////PRAZNE LISTE KOJE SE PUNE POMOCU SELECT UPITA u datahandleru
        quesList = dataHandler.getAllQuestions();
        Log.d("quesList",quesList.toString());
        //SORT ALL QUESTIONS PER CATEGORY
        for (Category c : quesList) {
            if(c.getCATEGORY().equals("history")){
                currentQPovijest = historyList(c);
                quesListPovijest.add(currentQPovijest);
            }
            else if(c.getCATEGORY().equals("geography")){
                currentQZemljopis = geographyList(c);
                quesListZemljopis.add(currentQZemljopis);
            }
            else if(c.getCATEGORY().equals("sport")){
                currentQSport = sportList(c);
                quesListSport.add(currentQSport);
            }
            else if(c.getCATEGORY().equals("music")){
                currentQGlazba = musicList(c);
                quesListGlazba.add(currentQGlazba);
            }
            else if(c.getCATEGORY().equals("science")){
                currentQZnanost = scienceList (c);
                quesListZnanost.add(currentQZnanost);
            }
        }

        for(int i = 0; i<quesListPovijest.size(); i++){
            randomListHistory.add(i);
        }
        for(int i = 0; i<quesListZemljopis.size(); i++){
            randomListGeography.add(i);
        }
        for(int i = 0; i<quesListSport.size(); i++){
            randomListSport.add(i);
        }
        for(int i = 0; i<quesListZnanost.size(); i++){
            randomListScience.add(i);
        }
        for(int i = 0; i<quesListGlazba.size(); i++){
            randomListMusic.add(i);
        }
        Collections.shuffle(randomListHistory);//[3,2,4,5,1,6]
        Collections.shuffle(randomListGeography);
        Collections.shuffle(randomListSport);
        Collections.shuffle(randomListScience);
        Collections.shuffle(randomListMusic);


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

        setQuestionView(pozicija,0);

        butNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioGroup grp=(RadioGroup)findViewById(R.id.radioGroup1);
                RadioButton answer=(RadioButton)findViewById(grp.getCheckedRadioButtonId());

            try {
                if (pozicija == 0) {
                    if (currentQPovijest.getANSWER().equals(answer.getText())) {
                        score++;
                        showToastCorrect();
                    }
                    else{
                        showToastIncorrect();
                    }
                    Log.d("Provjera ",  answer.getText() +" i " + currentQPovijest.getANSWER() + " brojac " + brojacPitanja + " score " + score);

                    if (brojacPitanja < 5) {
                        Log.d("Random lista", randomListHistory + "ID TRENUTNOG PITANJA " + randomListHistory.get(brojElementa));
                        setQuestionView(pozicija,brojElementa);
                    } else {
                        Intent intent = new Intent(QuizActivity.this, ResultActivity.class);
                        Bundle b = new Bundle();
                        Log.d("quiz finished", "");
                        b.putInt("score", score); //Your score
                        intent.putExtras(b); //Put your score to your next Intent
                        startActivity(intent);
                        finish();
                    }
                    brojElementa += 1;
                    brojacPitanja++;

                } else if (pozicija == 1) {
                    if (currentQSport.getANSWER().equals(answer.getText())) {
                        score++;
                        showToastCorrect();
                    }
                    else{
                        showToastIncorrect();
                    }
                    if (brojacPitanja < 5) {
                        currentQSport = quesListSport.get(randomListSport.get(brojElementa));
                        setQuestionView(pozicija,brojElementa);
                    } else {
                        Intent intent = new Intent(QuizActivity.this, ResultActivity.class);
                        Bundle b = new Bundle();
                        b.putInt("score", score); //Your score
                        intent.putExtras(b); //Put your score to your next Intent
                        startActivity(intent);
                        finish();
                    }
                    brojElementa += 1;
                    brojacPitanja++;

                } else if (pozicija == 2) {
                    if (currentQZemljopis.getANSWER().equals(answer.getText())) {
                        score++;
                        showToastCorrect();                    }
                    else{
                        showToastIncorrect();
                    }
                    if (brojacPitanja < 5) {
                        currentQZemljopis = quesListZemljopis.get(randomListGeography.get(brojElementa));
                        setQuestionView(pozicija,brojElementa);
                    } else {
                        Intent intent = new Intent(QuizActivity.this, ResultActivity.class);
                        Bundle b = new Bundle();
                        Log.d("quiz finished", "" + "");
                        b.putInt("score", score); //Your score
                        intent.putExtras(b); //Put your score to your next Intent
                        startActivity(intent);
                        finish();
                    }
                    brojElementa += 1;
                    brojacPitanja++;
                } else if (pozicija == 3) {
                    if (currentQZnanost.getANSWER().equals(answer.getText())) {
                        score++;
                        showToastCorrect();
                    }
                    else{
                        showToastIncorrect();
                    }
                    if (brojacPitanja < 5) {
                        Log.d("Random lista", randomListScience + "ID TRENUTNOG PITANJA " + randomListScience.get(brojElementa) + " brojac pitanja " + brojacPitanja + " br el " + brojElementa);
                        currentQZnanost = quesListZnanost.get(randomListScience.get(brojElementa));
                        setQuestionView(pozicija,brojElementa);
                    } else {
                        Intent intent = new Intent(QuizActivity.this, ResultActivity.class);
                        Bundle b = new Bundle();
                        b.putInt("score", score); //Your score
                        intent.putExtras(b); //Put your score to your next Intent
                        startActivity(intent);
                        finish();
                    }
                    brojElementa += 1;
                    brojacPitanja++;
                } else if (pozicija == 4) {
                    if (currentQGlazba.getANSWER().equals(answer.getText())) {
                        score++;
                        showToastCorrect();
                    }
                    else{
                        showToastIncorrect();
                    }
                    if (brojacPitanja < 5) {
                        Log.d("lista", " " + randomListMusic);
                        Log.d("ID TRENUTNOG PITANJA", " " + randomListMusic.get(brojElementa));
                        currentQGlazba = quesListGlazba.get(randomListMusic.get(brojElementa));
                        setQuestionView(pozicija,brojElementa);
                    } else {
                        Intent intent = new Intent(QuizActivity.this, ResultActivity.class);
                        Bundle b = new Bundle();
                        Log.d("quiz finished", "" + "");
                        b.putInt("score", score); //Your score
                        intent.putExtras(b); //Put your score to your next Intent
                        startActivity(intent);
                        finish();
                    }
                    brojElementa += 1;
                    brojacPitanja++;
                }
            }
            catch (NullPointerException e) {
                Toast.makeText(getApplicationContext(),"Odgovor nije označen!",Toast.LENGTH_SHORT).show();
            }
            }
        });
    }

    private Povijest historyList(Category c){
        Povijest povijest = new Povijest();
        povijest.setANSWER(c.getANSWER());
        povijest.setOPTA(c.getOPTA());
        povijest.setOPTB(c.getOPTB());
        povijest.setOPTC(c.getOPTC());
        povijest.setOPTD(c.getOPTD());
        povijest.setQUESTION(c.getQUESTION());
        return povijest;
    }
    private Sport sportList(Category c){
        Sport sport = new Sport();
        sport.setANSWER(c.getANSWER());
        sport.setOPTA(c.getOPTA());
        sport.setOPTB(c.getOPTB());
        sport.setOPTC(c.getOPTC());
        sport.setOPTD(c.getOPTD());
        sport.setQUESTION(c.getQUESTION());
        return sport;
    }
    private Zemljopis geographyList(Category c){
        Zemljopis zemljopis = new Zemljopis();
        zemljopis.setANSWER(c.getANSWER());
        zemljopis.setOPTA(c.getOPTA());
        zemljopis.setOPTB(c.getOPTB());
        zemljopis.setOPTC(c.getOPTC());
        zemljopis.setOPTD(c.getOPTD());
        zemljopis.setQUESTION(c.getQUESTION());
        return zemljopis;
    }
    private Znanost scienceList(Category c){
        Znanost znanost = new Znanost();
        znanost.setANSWER(c.getANSWER());
        znanost.setOPTA(c.getOPTA());
        znanost.setOPTB(c.getOPTB());
        znanost.setOPTC(c.getOPTC());
        znanost.setOPTD(c.getOPTD());
        znanost.setQUESTION(c.getQUESTION());
        return znanost;
    }
    private Glazba musicList(Category c){
        Glazba glazba = new Glazba();
        glazba.setANSWER(c.getANSWER());
        glazba.setOPTA(c.getOPTA());
        glazba.setOPTB(c.getOPTB());
        glazba.setOPTC(c.getOPTC());
        glazba.setOPTD(c.getOPTD());
        glazba.setQUESTION(c.getQUESTION());
        return glazba;
    }
    private void showToastCorrect(){
        Toast.makeText(getApplicationContext(), "Točan odgovor bijedo!", Toast.LENGTH_SHORT).show();
    }
    private void showToastIncorrect(){
        Toast.makeText(getApplicationContext(), "Netočan odgovor!", Toast.LENGTH_SHORT).show();

    }
    private void setQuestionView(int pozicija, int brojElementa) {
        if(pozicija==0){
            currentQPovijest = quesListPovijest.get(randomListHistory.get(brojElementa));
            txtQuestion.setText(currentQPovijest.getQUESTION());
            rda.setText(currentQPovijest.getOPTA());
            rdb.setText(currentQPovijest.getOPTB());
            rdc.setText(currentQPovijest.getOPTC());
            rdd.setText(currentQPovijest.getOPTD());
        }
        if(pozicija==1){
            currentQSport = quesListSport.get(randomListSport.get(brojElementa));
            txtQuestion.setText(currentQSport.getQUESTION());
            rda.setText(currentQSport.getOPTA());
            rdb.setText(currentQSport.getOPTB());
            rdc.setText(currentQSport.getOPTC());
            rdd.setText(currentQSport.getOPTD());
        }
        if(pozicija==2){
            currentQZemljopis = quesListZemljopis.get(randomListGeography.get(brojElementa));
            txtQuestion.setText(currentQZemljopis.getQUESTION());
            rda.setText(currentQZemljopis.getOPTA());
            rdb.setText(currentQZemljopis.getOPTB());
            rdc.setText(currentQZemljopis.getOPTC());
            rdd.setText(currentQZemljopis.getOPTD());
        }
        if(pozicija==3){
            currentQZnanost = quesListZnanost.get(randomListScience.get(brojElementa));
            txtQuestion.setText(currentQZnanost.getQUESTION());
            rda.setText(currentQZnanost.getOPTA());
            rdb.setText(currentQZnanost.getOPTB());
            rdc.setText(currentQZnanost.getOPTC());
            rdd.setText(currentQZnanost.getOPTD());
        }
        if(pozicija==4){
            currentQGlazba = quesListGlazba.get(randomListMusic.get(brojElementa));
            txtQuestion.setText(currentQGlazba.getQUESTION());
            rda.setText(currentQGlazba.getOPTA());
            rdb.setText(currentQGlazba.getOPTB());
            rdc.setText(currentQGlazba.getOPTC());
            rdd.setText(currentQGlazba.getOPTD());
        }
    }
}

