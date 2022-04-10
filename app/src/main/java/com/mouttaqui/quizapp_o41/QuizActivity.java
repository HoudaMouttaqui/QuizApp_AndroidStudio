package com.mouttaqui.quizapp_o41;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class QuizActivity extends AppCompatActivity {
private TextView questions;
private TextView question;
private AppCompatButton option1, option2, option3;
private AppCompatButton nextBtn;
private Timer quizTimer;
private int totalTimeInMins = 1;
private int seconds = 0;
private List<QuestionsList> questionsLists;
private int currentQuestionPostion = 0;
private String selectedOptionByUser = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        final String getSelectedTopicName = getIntent().getStringExtra("selectedTopic");
        final TextView timer = findViewById(R.id.tvTimer);
        final ImageView backBtn = findViewById(R.id.Btnback);
        final TextView selectedTopicName = findViewById(R.id.topicName);
        questions = findViewById(R.id.tvQuestions);
        question = findViewById(R.id.tvQuestion);
        option1 = findViewById(R.id.option1);
        option2 = findViewById(R.id.option2);
        option3 = findViewById(R.id.option3);
        nextBtn = findViewById(R.id.BtnNext);

        selectedTopicName.setText(getSelectedTopicName);

        questionsLists = QuestionsBank.getQuestions(getSelectedTopicName);


        startTimer(timer);

        questions.setText((currentQuestionPostion+1)+"/"+questionsLists.size());
        question.setText(questionsLists.get(0).getQuestion());
        option1.setText(questionsLists.get(0).getOption1());
        option2.setText(questionsLists.get(0).getOption2());
        option3.setText(questionsLists.get(0).getOption3());

        option1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(selectedOptionByUser.isEmpty()){
                    selectedOptionByUser = option1.getText().toString();

                    option1.setBackgroundResource(R.drawable.round_back_red10);
                    option1.setTextColor(Color.WHITE);

                    revealAnswer();

                    questionsLists.get(currentQuestionPostion).setUserSelectedAnswer(selectedOptionByUser);
                }
            }
        });

        option2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(selectedOptionByUser.isEmpty()){
                    selectedOptionByUser = option2.getText().toString();

                    option2.setBackgroundResource(R.drawable.round_back_red10);
                    option2.setTextColor(Color.WHITE);

                    revealAnswer();

                    questionsLists.get(currentQuestionPostion).setUserSelectedAnswer(selectedOptionByUser);
                }
            }
        });

        option3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(selectedOptionByUser.isEmpty()){
                    selectedOptionByUser = option3.getText().toString();

                    option3.setBackgroundResource(R.drawable.round_back_red10);
                    option3.setTextColor(Color.WHITE);

                    revealAnswer();

                    questionsLists.get(currentQuestionPostion).setUserSelectedAnswer(selectedOptionByUser);
                }

            }
        });

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(selectedOptionByUser.isEmpty()){
                    Toast.makeText(QuizActivity.this,"Please select an option!",Toast.LENGTH_SHORT);
                }
                else{
                    changeNextQuestion();

                }

            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                quizTimer.purge();
                quizTimer.cancel();

                startActivity(new Intent(QuizActivity.this,Quiz_topic.class));

                finish();
            }
        });
    }
private void changeNextQuestion(){
        currentQuestionPostion++;
        if((currentQuestionPostion+1) == questionsLists.size()){
            nextBtn.setText("Submit Quiz");
        }
        if(currentQuestionPostion<questionsLists.size()){
            selectedOptionByUser ="";

            option1.setBackgroundResource(R.drawable.round_back_white_stroke2);
            option1.setTextColor(Color.parseColor("#1F6BB8"));

            option2.setBackgroundResource(R.drawable.round_back_white_stroke2);
            option2.setTextColor(Color.parseColor("#1F6BB8"));

            option3.setBackgroundResource(R.drawable.round_back_white_stroke2);
            option3.setTextColor(Color.parseColor("#1F6BB8"));

            questions.setText((currentQuestionPostion+1)+"/"+questionsLists.size());
            question.setText(questionsLists.get(currentQuestionPostion).getQuestion());
            option1.setText(questionsLists.get(currentQuestionPostion).getOption1());
            option2.setText(questionsLists.get(currentQuestionPostion).getOption2());
            option3.setText(questionsLists.get(currentQuestionPostion).getOption3());
        }
        else{
            Intent intent = new Intent(QuizActivity.this,QuizResults.class);
            intent.putExtra("correct",getCorrectAnswer());
            intent.putExtra("incorrect",getIncorrectAnswer());
            startActivity(intent);

            finish();
        }
}
    private void startTimer(TextView timerTextView){
        quizTimer = new Timer();
        quizTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if(seconds==0){
                    totalTimeInMins --;
                    seconds = 59;
                }
                else if(seconds ==0 && totalTimeInMins==0){
                    quizTimer.purge();
                    quizTimer.cancel();
                    Toast.makeText(QuizActivity.this, "Time Over!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(QuizActivity.this, QuizResults.class);
                    intent.putExtra("correct",getCorrectAnswer());
                    intent.putExtra("incorrect",getIncorrectAnswer());
                    startActivity(intent);

                    finish();

                }
                else {
                    seconds--;
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                         String  finalMinutes= String.valueOf(totalTimeInMins);
                         String finalSeconds= String.valueOf(seconds);

                         if(finalMinutes.length() == 1){
                             finalMinutes = "0" + finalMinutes;
                         }
                         if(finalSeconds.length() == 1){
                             finalSeconds = "0" + finalSeconds;
                         }
                         timerTextView.setText(finalMinutes +":"+finalSeconds);
                    }
                });
            }
        }, 1000, 1000);
    }
    private int getCorrectAnswer(){
        int correctAnswers = 0;
        for (int i=0; i<questionsLists.size();i++){
            final String getUserSelectedAnswer = questionsLists.get(i).getUserSelectedAnswer();
            final String getAnswer = questionsLists.get(i).getAnswer();

            if(getUserSelectedAnswer.equals(getAnswer)){
                correctAnswers++;
            }

        }
        return correctAnswers;
    }

    private int getIncorrectAnswer(){
        int correctAnswers = 0;
        for (int i=0; i<questionsLists.size();i++){
            final String getUserSelectedAnswer = questionsLists.get(i).getUserSelectedAnswer();
            final String getAnswer = questionsLists.get(i).getAnswer();

            if(!getUserSelectedAnswer.equals(getAnswer)){
                correctAnswers++;
            }

        }
        return correctAnswers;
    }

    @Override
    public void onBackPressed() {
        quizTimer.purge();
        quizTimer.cancel();

        startActivity(new Intent(QuizActivity.this,Quiz_topic.class));

        finish();

    }

    private void revealAnswer(){
        final String getAnswer = questionsLists.get(currentQuestionPostion).getAnswer();

        if(option1.getText().toString().equals(getAnswer)){
            option1.setBackgroundResource(R.drawable.round_back_green10);
            option1.setTextColor(Color.WHITE);
        }
        else if(option2.getText().toString().equals(getAnswer)){
            option2.setBackgroundResource(R.drawable.round_back_green10);
            option2.setTextColor(Color.WHITE);
        }
        else if(option3.getText().toString().equals(getAnswer)){
            option3.setBackgroundResource(R.drawable.round_back_green10);
            option3.setTextColor(Color.WHITE);
        }
    }
}
