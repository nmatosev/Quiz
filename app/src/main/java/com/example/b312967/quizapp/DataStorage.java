package com.example.b312967.quizapp;

import java.util.HashMap;

/**
 * Created by neno on 10.9.2017..
 */
public class DataStorage {
    private static String[] imena = {"Povijest", "Sport", "Zemljopis", "Znanost","Umjetnost"};

    public static HashMap<Integer, Category> listViewData = new HashMap<Integer, Category>();

    public static void fillData() {
        for(int i = 0; i <imena.length; i++){
            Category category = new Category(i , imena[i]);
            listViewData.put(i, category);
        }
    }
}