package com.example.b312967.quizapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Neno on 23.12.2015..
 * Activity where questions are shown and answers verified.
 */
public class QuizActivity extends Activity {

    HashMap<String, List<Question>> questionMap = new HashMap<String, List<Question>>();
    String[] categories = {"history", "sport", "geography", "science", "art", "music", "movie"};

    int score = 0;
    int questionCounter = 1;
    int listElementNumber = 1;

    TextView txtQuestion;
    RadioButton radioButtonA, radioButtonB, radioButtonC, radioButtonD;
    Button butNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        DataHandler dataHandler = new DataHandler();
        dataHandler.addQuestions();
        questionMap = dataHandler.getQuestionMap();
        for (Map.Entry<String, List<Question>> entry : questionMap.entrySet()) {
            Collections.shuffle(entry.getValue());
        }

        txtQuestion = (TextView) findViewById(R.id.tv);
        radioButtonA = (RadioButton) findViewById(R.id.radioButton);
        radioButtonB = (RadioButton) findViewById(R.id.radioButton2);
        radioButtonC = (RadioButton) findViewById(R.id.radioButton3);
        radioButtonD = (RadioButton) findViewById(R.id.radioButton4);

        butNext = (Button) findViewById(R.id.btnnext);
        Bundle extras = getIntent().getExtras();
        final Integer position = Integer.valueOf(extras.getString("listElement"));
        switch (position) {
            case 0:
                setQuestionView("history", 0);
                break;
            case 1:
                setQuestionView("sport", 0);
                break;
            case 2:
                setQuestionView("geography", 0);
                break;
            case 3:
                setQuestionView("science", 0);
                break;
            case 4:
                setQuestionView("art", 0);
                break;
            case 5:
                setQuestionView("music", 0);
                break;
            case 6:
                setQuestionView("movie", 0);
                break;
        }

        butNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioGroup1);
                RadioButton answer = (RadioButton) findViewById(radioGroup.getCheckedRadioButtonId());

                if (answer != null) {
                    for (int categoryNum = 0; categoryNum < categories.length; categoryNum++) {
                        if (position == categoryNum) {
                            validateAnswerAndGoToNext(answer.getText().toString(), radioGroup, categories[position]);
                        }
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Odgovor nije označen!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void showToastForCorrectAnswer() {
        Toast.makeText(getApplicationContext(), "Točan odgovor!", Toast.LENGTH_SHORT).show();
    }

    private void showToastForIncorrectAnswer(String answer) {
        Toast.makeText(getApplicationContext(), "Netočan odgovor! Točan odgovor je " + answer, Toast.LENGTH_SHORT).show();
    }

    private void setQuestionView(String category, int questionNumber) {
        txtQuestion.setText(questionMap.get(category).get(questionNumber).getQuestion());
        radioButtonA.setText(questionMap.get(category).get(questionNumber).getOptionA());
        radioButtonB.setText(questionMap.get(category).get(questionNumber).getOptionB());
        radioButtonC.setText(questionMap.get(category).get(questionNumber).getOptionC());
        radioButtonD.setText(questionMap.get(category).get(questionNumber).getOptionD());
    }

    private void validateAnswerAndGoToNext(String answer, RadioGroup grp, String category) {
        Log.d("Log Answer", questionMap.get(category).get(listElementNumber + 1).getQuestion() + " answer " + answer);

        if (questionMap.get(category).get(listElementNumber - 1).getAnswer().equals(answer)) {
            score++;
            showToastForCorrectAnswer();
        } else {
            showToastForIncorrectAnswer(questionMap.get(category).get(listElementNumber - 1).getAnswer());
        }

        if (questionCounter < 5) {
            grp.clearCheck();
            setQuestionView(category, listElementNumber);
        } else {
            Intent intent = new Intent(QuizActivity.this, ResultActivity.class);
            Bundle b = new Bundle();
            b.putInt("score", score);
            intent.putExtras(b);
            startActivity(intent);
            finish();
        }
        listElementNumber += 1;
        questionCounter++;
    }
}

