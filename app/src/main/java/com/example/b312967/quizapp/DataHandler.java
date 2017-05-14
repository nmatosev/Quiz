package com.example.b312967.quizapp;


import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.FileNameMap;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by b312967 on 23.12.2015..
 */
public class DataHandler {

    List<String> list = new ArrayList<String>(); // lista stringova
    List<Category> quesList = new ArrayList<Category>(); // lista stringova

    //GETERI SETERI, stvaraj nove objekte koji se spremaju u tablice
    public void addQuestions() {
        List<String> questionlist = new ArrayList<String>(); // lista pitanja i odgovora
        questionlist = ParseFile("res/raw/questions.txt");   // ime text filea

        String[] separated;  // array u kojem ce prvi clan bit pitanje, sljedeca 4 odgovori

        for (int i=0; i<questionlist.size(); i++) {
            separated = questionlist.get(i).split(",");
            try {
                Log.w("q raw", separated[0] + separated[1] + separated[2] + separated[3] + separated[4] + separated[5]+separated[6]);
                Category c = new Category(separated[0], separated[1], separated[2], separated[3], separated[4], separated[5], separated[6]);
                quesList.add(c);
            }catch(Exception e) {
                Log.d("Neispravan format " , questionlist.get(i));
            }
            //dodavaj objekte u listu
        }
    }


    public List<String> ParseFile(String filename) // filename triba bit tipa "raw/ime.text" u res/raw folderu
    {
        InputStream in = this.getClass().getClassLoader().getResourceAsStream(filename);
        Log.w("LOG", "test2");
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));

        String str; // string u kojem je pitanje (linija text filea)
        //String[] separated;  // array u kojem ce prvi clan bit pitanje, sljedeca 4 odgovori
        try {
            while ((str = reader.readLine()) != null) {
                Log.w("dodani string", str);
                list.add(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }


    public List<Category> getAllQuestions() {
        //String selectQuery = "SELECT  * FROM " + TABLE_IT;
        return  quesList;
    }

    public List<String> getAllQuestions1() {
        return list;
    }


}
