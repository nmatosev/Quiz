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
 * Class responsible for parsing questions from file, verifying them and storing them in question map.
 */
public class DataHandler {

    HashMap<String, List<Question>> questionMap = new HashMap<String, List<Question>>();

    /**
     * Creates new questions and stores them in question map
     */
    public void addQuestions() {
        List<String> questionsList = new ArrayList<String>();
        questionsList = parseFile("res/raw/questions.txt");
        String[] question;

        for (int i = 0; i < questionsList.size(); i++) {
            question = questionsList.get(i).split(";");
            try {
                Log.w("Question log: ", question[0] + question[1] + question[2] + question[3] + question[4] + question[5] + question[6]);
                verifyQuestion(question);
                fillQuestionMap(question);
            } catch (Exception e) {
                Log.d("Neispravan format ", questionsList.get(i));
            }
        }
    }

    /**
     * Verifies that question instance has 4 options, question, proper category and correct answer.
     *
     * @param question Question instance broken down to array of strings.
     */
    private void verifyQuestion(String[] question) {
        String[] categories = {"geography", "history", "sport", "art", "science", "music", "movie"};
        try {
            checkNumberOfElementsInList(question);
            checkIfCategoryIsCorrect(question[6], categories);
            checkIfCorrectAnswerExists(question);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Checks if there is an correct answer.
     *
     * @param question Question instance broken down to array of strings.
     */
    private void checkIfCorrectAnswerExists(String[] question) {
        boolean isCorrectAnswerFound = false;
        for (int i = 1; i < 5; i++) {
            if (question[i].equals(question[5])) {
                isCorrectAnswerFound = true;
            }
        }
        if (!isCorrectAnswerFound) {
            Log.d("Tocnog odgovora nema", "Tocnog odgovora nema u " + question[0]);
        }
    }

    /**
     * Check if question array has correct size.
     *
     * @param question Question instance broken down to array of strings.
     */
    private void checkNumberOfElementsInList(String[] question) {
        if (question.length != 7) {
            Log.d("Neispravna velicina", "Neispravna velicina pitanja za " + question[0].toString());
        }
    }

    /**
     * Verifies parsed category.
     *
     * @param parsedCategory
     * @param categories
     */
    private void checkIfCategoryIsCorrect(String parsedCategory, String[] categories) {
        boolean isCategoryCorrect = false;
        for (String category : categories) {
            if (category.equals(parsedCategory)) {
                isCategoryCorrect = true;
            }
        }
        if (!isCategoryCorrect) {
            Log.d("Nepoznata kategorija", "Nepoznata kateogorija " + parsedCategory);
        }
    }


    /**
     * Parses raw file with questions and stores them in a list.
     *
     * @param fileName source file where questions are stored.
     * @return parsedQuestions
     */
    public List<String> parseFile(String fileName) {
        List<String> parsedQuestions = new ArrayList<String>();
        InputStream in = this.getClass().getClassLoader().getResourceAsStream(fileName);
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        String lineInFile;
        try {
            while ((lineInFile = reader.readLine()) != null) {
                parsedQuestions.add(lineInFile);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return parsedQuestions;
    }


    /**
     * @param questionArray used for creating key value structure.
     */
    public void fillQuestionMap(String[] questionArray) {
        if (questionMap.containsKey(questionArray[6])) {
            Question question = new Question(questionArray[0], questionArray[1], questionArray[2], questionArray[3], questionArray[4], questionArray[5], questionArray[6]);
            questionMap.get(questionArray[6]).add(question);
        } else {
            List<Question> questionList = new ArrayList<Question>();
            Question question = new Question(questionArray[0], questionArray[1], questionArray[2], questionArray[3], questionArray[4], questionArray[5], questionArray[6]);
            questionList.add(question);
            questionMap.put(questionArray[6], questionList);
        }
    }

    public HashMap<String, List<Question>> getQuestionMap() {
        return questionMap;
    }

}
