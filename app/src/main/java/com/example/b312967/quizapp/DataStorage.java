package com.example.b312967.quizapp;

import java.util.HashMap;

/**
 * Created by neno on 10.9.2017..
 */
public class DataStorage {
    public static String[] categories = {"Povijest", "Sport", "Zemljopis", "Znanost", "Umjetnost", "Music", "Film"};
    public static HashMap<Integer, Category> listViewData = new HashMap<Integer, Category>();

    public static void fillData() {
        for(int i = 0; i < categories.length; i++){
            Category category = new Category(i , categories[i]);
            listViewData.put(i, category);
        }
    }
}