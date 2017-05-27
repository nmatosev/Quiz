package com.example.b312967.quizapp;

import java.util.HashMap;

/**
 * Created by b312967 on 23.12.2015..
 */
public class DataStorage {
    private static String[] imena = {"Povijest", "Sport", "Zemljopis", "Znanost","Umjetnost"};

    public static HashMap<Integer, Kategorija> listViewData = new HashMap<Integer,
            Kategorija>();

    public static void fillData() {
        for(int i = 0; i <imena.length; i++){
            Kategorija akategorija = new Kategorija(i , imena[i]);
            listViewData.put(i, akategorija);
        }
    }
}
