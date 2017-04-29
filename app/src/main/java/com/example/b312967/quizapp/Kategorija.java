package com.example.b312967.quizapp;

import android.content.Context;

/**
 * Created by b312967 on 23.12.2015..
 */
public class Kategorija {
    private int ID;
    private String ime;

    public Kategorija(int ID, String ime){
        this.ID = ID;
        this.ime = ime;

    }

    public String getIme()
    {
        return ime;
    }

    public int getTmbImageId(Context context) {
        return context.getResources().getIdentifier("img"+ID, "drawable", "com.example.b312967.quizapp");
    }


}
