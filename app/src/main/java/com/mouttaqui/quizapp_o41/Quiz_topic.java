package com.mouttaqui.quizapp_o41;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class Quiz_topic extends AppCompatActivity {

    private String selectedTopicName = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_topic);

        final LinearLayout science = findViewById(R.id.ScienceLayout);
        final LinearLayout geo= findViewById(R.id.GeoLayout);
        final LinearLayout literature = findViewById(R.id.LitLayout);
        final LinearLayout pop = findViewById(R.id.PopLayout);

        final Button startBtn = findViewById(R.id.BstartQuiz);
        //final Button Bmaps = findViewById(R.id.Bmaps);


      /* Bmaps.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent intent = new Intent(Quiz_topic.this,MapsActivity.class);
           }
       });*/

        science.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedTopicName = "science";

                science.setBackgroundResource(R.drawable.round_back_white_stroke10);

                geo.setBackgroundResource(R.drawable.round_back_white10);
                literature.setBackgroundResource(R.drawable.round_back_white10);
                pop.setBackgroundResource(R.drawable.round_back_white10);


            }
        });

        geo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedTopicName = "geography";

                geo.setBackgroundResource(R.drawable.round_back_white_stroke10);

                science.setBackgroundResource(R.drawable.round_back_white10);
                literature.setBackgroundResource(R.drawable.round_back_white10);
                pop.setBackgroundResource(R.drawable.round_back_white10);
            }
        });

        literature.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedTopicName = "literature";

                literature.setBackgroundResource(R.drawable.round_back_white_stroke10);

                science.setBackgroundResource(R.drawable.round_back_white10);
                geo.setBackgroundResource(R.drawable.round_back_white10);
                pop.setBackgroundResource(R.drawable.round_back_white10);
            }
        });
        pop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedTopicName = "pop culture";

                pop.setBackgroundResource(R.drawable.round_back_white_stroke10);

                science.setBackgroundResource(R.drawable.round_back_white10);
                geo.setBackgroundResource(R.drawable.round_back_white10);
                literature.setBackgroundResource(R.drawable.round_back_white10);
            }
        });

        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(selectedTopicName.isEmpty()){
                    Toast.makeText(Quiz_topic.this, "Please select a topic!", Toast.LENGTH_SHORT).show();
                }
                else{
                    Intent intent = new Intent(Quiz_topic.this, QuizActivity.class);
                    intent.putExtra("selectedTopic", selectedTopicName);
                    startActivity(intent);
                }
            }
        });
    }
}