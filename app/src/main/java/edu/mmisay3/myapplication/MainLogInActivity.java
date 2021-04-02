package edu.mmisay3.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainLogInActivity extends AppCompatActivity {

    final static Integer REQUEST_CODE_REGISTER_ACTIVITY = 1;

    static SystemDatabase SystemDataBase = new SystemDatabase();
    // Stores the registering user to a vector data structure
    // User info contains the following:
    //  1. First Name   4. Username (email)
    //  2. Last Name    5. Password
    //  3. Age

    protected Button login_button,register_button;

    private EditText username;
    // Desc: User enters email address
    // ID: editTextTextEmailAddress

    private EditText password;
    // Desc: User enters password
    // ID: editTextTextPassword
    // Edit made by: Mitchell on 2/25/21, 3:30PM

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_log_in);

        // Log in button
        login_button = (Button)findViewById(R.id.signIn_id);
        login_button.setOnClickListener((login_buttonListener));

        // Register button
        register_button = (Button)findViewById(R.id.register_id);

        if(getIntent() != null && getIntent().getExtras() != null && getIntent().hasExtra(RegisterActivity.USER_KEY)){
            User user = (User) getIntent().getSerializableExtra(FinishedRegistration.USER_KEY);
            SystemDataBase.addUser(user);
        }

        register_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainLogInActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        // EditText Objects
        // Edit made by: Mitchell on 2/25/21, 3:30PM
        username = (EditText) findViewById(R.id.editTextTextEmailAddress);
        password = (EditText) findViewById(R.id.editTextTextPassword);
    }

    public void openLogInActivity(){
        Intent i = new Intent(MainLogInActivity.this, CourseListActivity.class);
        startActivity(i);
    }
    public View.OnClickListener login_buttonListener = v -> {


        String _username = username.getText().toString();
        String _password = password.getText().toString();

        if(SystemDataBase.usernameIsFound(_username).equals(false)){
            Toast.makeText(MainLogInActivity.this, "Username is not found", Toast.LENGTH_LONG).show();
        }
        else{
            if(SystemDataBase.passwordValidation(_username, _password))
                openLogInActivity();
            else{
                Toast.makeText(MainLogInActivity.this, "Invalid Password",Toast.LENGTH_LONG).show();
            }
        }
    };
}