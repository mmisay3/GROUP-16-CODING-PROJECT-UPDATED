package edu.mmisay3.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FinishedRegistration extends AppCompatActivity {

    final static String USER_KEY = "USER";
    User user;
    private Button sign_in;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finished_registration);

        sign_in = (Button) findViewById(R.id.signIn_id);

        if(getIntent() != null && getIntent().getExtras() != null && getIntent().hasExtra(RegisterActivity.USER_KEY)){
            user = (User) getIntent().getSerializableExtra(FinishedRegistration.USER_KEY);
        }

        sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FinishedRegistration.this, MainLogInActivity.class);
                intent.putExtra(USER_KEY, user);
                startActivity(intent);
            }
        });

    }
}