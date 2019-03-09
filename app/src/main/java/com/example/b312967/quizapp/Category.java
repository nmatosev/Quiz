package com.example.b312967.quizapp;

import android.content.Context;

/**
 * Created by neno on 10.9.2017.
 * Question category class.
 */
public class Category {
    private int ID;
    private String categoryName;

    public Category(int ID, String ime) {
        this.ID = ID;
        this.categoryName = ime;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public int getTmbImageId(Context context) {
        return context.getResources().getIdentifier("img" + ID, "drawable", "com.example.b312967.quizapp");
    }
}
