package edu.mmisay3.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class SeventhFragment extends Fragment {

    public QuestionLibrary mQuestionLibrary = new QuestionLibrary();

    private TextView mScoreView;
    private TextView mQuestionView;
    private Button mButtonChoice1;
    private Button mButtonChoice2;
    private Button mButtonChoice3;

    private String mAnswer;
    public int mScore = 0;

    private int mQuestionNumber = 0;

    public Integer GetScore(){
        return this.mScore;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    // Called when fragment must generate its visual display for the first time (returns a View)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_seventh, container, false);


        mScoreView = view.findViewById(R.id.score);
        mQuestionView = view.findViewById(R.id.question);
        mButtonChoice1 = view.findViewById(R.id.choice1);
        mButtonChoice2 = view.findViewById(R.id.choice2);
        mButtonChoice3 = view.findViewById(R.id.choice3);

        updateQuestion();

        mButtonChoice1.setOnClickListener(v-> {
            if(mButtonChoice1.getText() == mAnswer){
                mScore++;
                updateScore(mScore);

                if(mQuestionNumber == QuestionLibrary.mQuestions.length){
                    Intent i = new Intent(getActivity(),ResultsActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putInt("finalScore",mScore);
                    i.putExtras(bundle);
                    getActivity().finish();
                    startActivity(i);


                    Toast.makeText(getActivity(),"Done",Toast.LENGTH_SHORT).show();

                }
                else{
                    updateQuestion();
                }
            }
            else{
                updateQuestion();
            }
        });



        mButtonChoice2.setOnClickListener(v-> {
            if(mButtonChoice2.getText() == mAnswer){
                mScore++;
                updateScore(mScore);

                if(mQuestionNumber == QuestionLibrary.mQuestions.length){

                    Intent i = new Intent(getActivity(),ResultsActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putInt("finalScore",mScore);
                    i.putExtras(bundle);
                    getActivity().finish();
                    startActivity(i);
                    Toast.makeText(getActivity(),"Done",Toast.LENGTH_SHORT).show();
                }
                else{
                    updateQuestion();
                }
            }
            else{
                updateQuestion();
            }
        });


        // ERROR: program crashes when wrong answer is chosen, only works when right answer is picked for the last question
        mButtonChoice3.setOnClickListener(v-> {
            if(mButtonChoice3.getText() == mAnswer){
                mScore++;
                updateScore(mScore);

                if(mQuestionNumber == QuestionLibrary.mQuestions.length){
                    Intent i = new Intent(getActivity(),ResultsActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putInt("finalScore",mScore);
                    i.putExtras(bundle);
                    getActivity().finish();
                    startActivity(i);

                    Toast.makeText(getActivity(),"Done",Toast.LENGTH_SHORT).show();
                }
                else{
                    updateQuestion();
                }
            }
            else{
                updateQuestion();
            }
        });

        return view;
    }

    private void updateQuestion(){
        mQuestionView.setText(mQuestionLibrary.getQuestion(mQuestionNumber));
        mButtonChoice1.setText(mQuestionLibrary.getChoice1(mQuestionNumber));
        mButtonChoice2.setText(mQuestionLibrary.getChoice2(mQuestionNumber));
        mButtonChoice3.setText(mQuestionLibrary.getChoice3(mQuestionNumber));
        mAnswer = mQuestionLibrary.getCorrectAnswer(mQuestionNumber);
        mQuestionNumber++;
    }

    private void updateScore(int point){
        mScoreView.setText(""+mScore);
    }
}
