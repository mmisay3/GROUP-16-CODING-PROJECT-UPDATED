package edu.mmisay3.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;

public class ResultsActivity extends AppCompatActivity {

    TextView mScore;
    TextView award;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        mScore = (TextView)findViewById(R.id.score);
        award = (TextView)findViewById(R.id.eligible);

        Bundle bundle = getIntent().getExtras();
        int score = bundle.getInt("finalScore");

        mScore.setText(score +" / " + QuestionLibrary.mQuestions.length);

        if(score > 5){
            award.setText("You are eligible for a certificate!");
        }
        else{
            award.setText("Not eligible for a certificate. Try again.");
        }


    }
}