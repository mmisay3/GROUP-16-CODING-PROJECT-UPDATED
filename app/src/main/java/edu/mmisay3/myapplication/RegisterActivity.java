package edu.mmisay3.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.Serializable;

public class RegisterActivity extends AppCompatActivity{

    final static String USER_KEY = "USER";

    private Button submit_button;
    // R id: submitRegister_id
    // Submit button that stores the user inputs
    // to the vector database

    // User register info inputs. Edit: Mitchell 2/25/2021, 9pm
    private EditText register_username;
    private EditText register_password;
    private EditText register_first_name;
    private EditText register_last_name;
    private EditText register_age;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        submit_button = (Button) findViewById(R.id.submitRegister_id);
        register_username = (EditText)findViewById(R.id.editTextTextPersonName2);
        register_password = (EditText)findViewById(R.id.pwRegister_id);
        register_first_name = (EditText)findViewById(R.id.editTextTextPersonName4);
        register_last_name = (EditText)findViewById(R.id.editTextTextPersonName5);
        register_age = (EditText)findViewById(R.id.editTextNumber);

        submit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("t3", "Clicked Submit");
                String firstName = register_first_name.getText().toString();
                String lastName = register_last_name.getText().toString();
                Integer age = Integer.parseInt(register_age.getText().toString());
                String username = register_username.getText().toString();
                String password = register_password.getText().toString();

                Intent intent = new Intent(RegisterActivity.this, FinishedRegistration.class);
                intent.putExtra(USER_KEY, new User(firstName, lastName, age, username, password));
                startActivity(intent);
            }
        });
    }
}