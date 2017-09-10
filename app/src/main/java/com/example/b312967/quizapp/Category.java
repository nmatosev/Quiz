package com.example.b312967.quizapp;

import android.content.Context;

/**
 * Created by neno on 10.9.2017..
 */
public class Category {
    private int ID;
    private String ime;

    public Category(int ID, String ime){
        this.ID = ID;
        this.ime = ime;
    }

    public String getIme() {
        return ime;
    }
    public int getTmbImageId(Context context) {
        return context.getResources().getIdentifier("img"+ID, "drawable", "com.example.b312967.quizapp");
    }
}
