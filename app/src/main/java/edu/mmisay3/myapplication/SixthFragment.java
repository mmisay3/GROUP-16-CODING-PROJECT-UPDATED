package edu.mmisay3.myapplication;

import android.content.Context;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class SixthFragment extends Fragment {
    private TextView textView1;
    private TextView textView2;

    public Context main_context;

    //buttons
    private Button my_wrong_button1;
    private Button my_wrong_button2;
    private Button my_wrong_button3;
    private Button my_wrong_button4;
    private Button my_wrong_button5;
    private Button my_wrong_button6;
    private Button my_wrong_button7;
    private Button my_wrong_button8;

    private Button my_correct_button1;

    private Button my_music_on;
    private Button my_music_off;


    //media
    private MediaPlayer soundEffect;
    private MediaPlayer soundEffect_breathe;
    private MediaPlayer mySong;

    // Android calls when fragment created (a constructor?)
    // Initialize fragment components that must be retained when fragment is stopped, and then resumed
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);




    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sixth,container,false);

        textView1 = (TextView)view.findViewById(R.id.textView);
        textView2 = (TextView)view.findViewById(R.id.textView2);

        //mySong = MediaPlayer.create(SecondFragment.this,R.raw.bpm);
        my_wrong_button1 = (Button)view.findViewById(R.id.wrong_button1);
        my_wrong_button2 = (Button)view.findViewById(R.id.wrong_button2);
        my_wrong_button3 = (Button)view.findViewById(R.id.wrong_button3);
        my_wrong_button4 = (Button)view.findViewById(R.id.wrong_button4);
        my_wrong_button5 = (Button)view.findViewById(R.id.wrong_button5);
        my_wrong_button6 = (Button)view.findViewById(R.id.wrong_button6);
        my_wrong_button7 = (Button)view.findViewById(R.id.wrong_button7);
        my_wrong_button8 = (Button)view.findViewById(R.id.wrong_button8);
        my_correct_button1 = (Button)view.findViewById(R.id.correct_button1);

        my_music_on = (Button)view.findViewById(R.id.MusicOn);
        my_music_off = (Button)view.findViewById(R.id.MusicOff);


        my_correct_button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView1.setText("WOW GOOD JOB!");
                textView1.setTextColor(Color.BLUE);
                soundEffect_breathe.start();
            }
        });


        my_wrong_button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView1.setText("I CAN'T BREATHE!");
                textView1.setTextColor(Color.RED);
                soundEffect.start();
            }
        });


        my_wrong_button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView1.setText("WHAT ARE YOU DOING?!");
                textView1.setTextColor(Color.RED);
                soundEffect.start();

            }
        });

        my_wrong_button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView1.setText("I'M GOING TO DIE!");
                textView1.setTextColor(Color.RED);
                soundEffect.start();

            }
        });

        my_wrong_button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView1.setText("AHHH, YOU ARE NOT HELPING!");
                textView1.setTextColor(Color.RED);
                soundEffect.start();

            }
        });

        my_wrong_button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView1.setText("FIND SOMEONE ELSE!");
                textView1.setTextColor(Color.RED);
                soundEffect.start();

            }
        });

        my_wrong_button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView1.setText("THAT'S NOT DOING ANYTHING!");
                textView1.setTextColor(Color.RED);
                soundEffect.start();

            }
        });

        my_wrong_button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView1.setText("THAT'S MY ABDOMINAL!");
                textView1.setTextColor(Color.RED);
                soundEffect.start();

            }
        });

        my_wrong_button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView1.setText("THAT'S NOT EVEN MY BODY!");
                textView1.setTextColor(Color.RED);
                soundEffect.start();

            }
        });

        my_music_on.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mySong.start();
                textView2.setText("Follow the rythm!(100 bpm)");
            }
        });

        my_music_off.setOnClickListener(v -> {

            if(mySong.isPlaying())
                mySong.stop();

            mySong = MediaPlayer.create(this.main_context,R.raw.bpm);


            textView2.setText("");
        });




        //---------------------------------------------------------------------------------------------

        //soundEffect = MediaPlayer.create(this,R.raw.ouch);
        //soundEffect_breathe = MediaPlayer.create(this,R.raw.breathe);
        return view;
    }
    @Override
    public void onAttach(@NonNull Context context)
    {
        super.onAttach(context);
        this.main_context = context;
        mySong = MediaPlayer.create(main_context,R.raw.bpm);
        soundEffect = MediaPlayer.create(main_context,R.raw.ouch);
        soundEffect_breathe = MediaPlayer.create(main_context,R.raw.breathe);

    }



}
