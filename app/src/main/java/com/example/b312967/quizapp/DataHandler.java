package com.example.b312967.quizapp;


import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by b312967 on 23.12.2015..
 */
public class DataHandler {

    List<String> list = new ArrayList<String>(); // lista stringova
    List<Question> quesList = new ArrayList<Question>(); // lista stringova
    HashMap<String, List<Question>> questionMap = new HashMap<String, List<Question>>();


    //GETERI SETERI, stvaraj nove objekte koji se spremaju u tablice
    public void addQuestions() {
        List<String> questionlist = new ArrayList<String>(); // lista pitanja i odgovora
        questionlist = ParseFile("res/raw/questions.txt");   // ime text filea
        String[] separated;  // array u kojem ce prvi clan bit pitanje, sljedeca 4 odgovori

        for (int i=0; i<questionlist.size(); i++) {
            separated = questionlist.get(i).split(";");
            try {
                Log.w("q raw", separated[0] + separated[1] + separated[2] + separated[3] + separated[4] + separated[5]+ separated[6]);
                fillQuestionMap(separated);
            }catch(Exception e) {
                Log.d("Neispravan format " , questionlist.get(i));
            }
        }
    }


    public List<String> ParseFile(String filename){
        InputStream in = this.getClass().getClassLoader().getResourceAsStream(filename);
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        String str; // string u kojem je pitanje (linija text filea)
        try {
            while ((str = reader.readLine()) != null) {
                list.add(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }
    public void fillQuestionMap(String[] separated){
        if(questionMap.containsKey(separated[6])){
            Question question = new Question(separated[0], separated[1], separated[2], separated[3], separated[4], separated[5], separated[6]);
            questionMap.get(separated[6]).add(question);
        }
        else{
            List<Question> questionList = new ArrayList<Question>();
            Question question = new Question(separated[0], separated[1], separated[2], separated[3], separated[4], separated[5], separated[6]);
            questionList.add(question);
            questionMap.put(separated[6],questionList);
        }
    }

    public HashMap<String, List<Question>> getQuestionMap() {
        return questionMap;
    }

}
