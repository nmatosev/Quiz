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
 * Created by neno on 23.12.2015.
 * Class responsible for parsing questions from file, verifying them and storing them in question map
 */
public class DataHandler {

    List<String> list = new ArrayList<String>(); // lista stringova
    List<Question> quesList = new ArrayList<Question>(); // lista stringova
    HashMap<String, List<Question>> questionMap = new HashMap<String, List<Question>>();

    /**
     * Creates new questions and stores them in question map
     * */
    public void addQuestions() {
        List<String> questionslist = new ArrayList<String>(); // lista pitanja i odgovora
        questionslist = ParseFile("res/raw/questions.txt");   // ime text filea
        String[] question;  // array u kojem ce prvi clan bit pitanje, sljedeca 4 odgovori

        for (int i=0; i<questionslist.size(); i++) {
            question = questionslist.get(i).split(";");
            try {
                Log.w("question raw: ", question[0] + question[1] + question[2] + question[3] + question[4] + question[5]+ question[6]);
                verifyQuestion(question);
                fillQuestionMap(question);
            }catch(Exception e) {
                Log.d("Neispravan format " , questionslist.get(i));
            }
        }
    }

    /**
     * Verifies that question has a 4 answers and an question
     * @param question
     */
    private void verifyQuestion(String[] question) {
        String[] categories = {"geography", "history", "sport", "art", "science"};
        try {
            checkNumberOfElementsInList(question);
            checkIfCategoryIsCorrect(question[6], categories);
            checkIfCorrectAnswerExists(question);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Checks if there is an correct answer.
     * @param question
     */
    private void checkIfCorrectAnswerExists(String[] question) {
        boolean isCorrectAnswerFound = false;
        for(int i=1;i<5;i++){
            if(question[i].equals(question[5])){
                isCorrectAnswerFound = true;
            }
        }
        if(!isCorrectAnswerFound){
            Log.d("Tocnog odgovora nema","Tocnog odgovora nema u " + question[0]);
        }
    }

    /**
     * Check if question array has correct size.
     * @param question
     */
    private void checkNumberOfElementsInList(String[] question) {
        if(question.length!=7){
            Log.d("Neispravna velicina", "Neispravna velicina pitanja za " + question[0].toString());
        }
    }

    /**
     * Checks that verified category is parsed.
     * @param parsedCategory
     * @param categories
     */
    private void checkIfCategoryIsCorrect(String parsedCategory, String[] categories) {
        boolean isCategoryCorrect = false;
        for(String category : categories){
            if(category.equals(parsedCategory)){
                isCategoryCorrect = true;
            }
        }
        if(!isCategoryCorrect){
            Log.d("Nepoznata kategorija", "Nepoznata kateogorija " + parsedCategory);
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
