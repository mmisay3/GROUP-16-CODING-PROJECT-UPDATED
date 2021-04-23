package edu.mmisay3.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Vector;

public class MainLogInActivity extends AppCompatActivity {
    final static String MAIN_KEY = "MAIN_KEY";
    final static Integer REQUEST_CODE_REGISTER_ACTIVITY = 1;


    static SystemDatabase SystemDataBase = new SystemDatabase();
    // Stores the registering user to a vector data structure
    // User info contains the following:
    //  1. First Name   4. Username (email)
    //  2. Last Name    5. Password
    //  3. Age

    protected Button login_button,register_button;

    protected TextView SignUp;

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

        // Default Admin User
        createAdmin("admin", "password");

        // Log in button
        login_button = (Button)findViewById(R.id.signIn_id);
        login_button.setOnClickListener((login_buttonListener));

        // Register button
        // register_button = (Button)findViewById(R.id.register_id);
        SignUp = findViewById(R.id.SignUp);

        if(getIntent() != null && getIntent().getExtras() != null && getIntent().hasExtra(RegisterActivity.USER_KEY)){
            User user = (User) getIntent().getSerializableExtra(FinishedRegistration.USER_KEY);
            SystemDataBase.addUser(user);
        }


        SignUp.setOnClickListener(new View.OnClickListener() {
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

    public void openLogInActivity(User user){
        Intent i = new Intent(MainLogInActivity.this, CourseListActivity.class);
        i.putExtra(MainLogInActivity.MAIN_KEY, user);
        startActivity(i);
    }

    public void openAdminPage(){
        Intent i = new Intent(MainLogInActivity.this, AdminPage.class);
        startActivity(i);
    }

    public View.OnClickListener login_buttonListener = v -> {
        String _username = username.getText().toString();
        String _password = password.getText().toString();



        if(SystemDataBase.usernameIsFound(_username).equals(false)){
            Toast.makeText(MainLogInActivity.this, "Username is not found", Toast.LENGTH_LONG).show();
        }
        else{
            if(SystemDataBase.passwordValidation(_username, _password)) {
                int userIndex = SystemDataBase.searchUser(_username);
                if (SystemDataBase.newuser.elementAt(userIndex).isAdmin()) {
                    displayToast("Opening admin page . . . ");
                    openAdminPage();
                }
                else{
                    ArrayList<Course> arrayList = new ArrayList<>();
                    Vector<Course> courseList = new Vector<>();

                    if(getIntent() != null && getIntent().getExtras() != null && getIntent().hasExtra(AdminPage.ADMIN_KEY)){
                        arrayList = (ArrayList<Course>)getIntent().getSerializableExtra(AdminPage.ADMIN_KEY);
                        displayToast("MAIN courseList size = " + arrayList.size());
                        copyArrayListToVector(arrayList, courseList);
                    }

                    SystemDataBase.newuser.elementAt(userIndex).initializeVector(arrayList);
                    openLogInActivity(SystemDataBase.newuser.elementAt(userIndex));
                }
            }
            else{
                Toast.makeText(MainLogInActivity.this, "Invalid Password",Toast.LENGTH_LONG).show();
            }
        }
    };


    // Utility Methods -----------------------------------------------------------------------------
    public void displayToast(String str){
        Context context = getApplicationContext();
        CharSequence text= str;
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    public void createAdmin(String username, String password){
        SystemDataBase.addUser(new User("admin", "", 0, username, password));
        int adminIndex = SystemDataBase.searchUser("admin");
        SystemDataBase.newuser.elementAt(adminIndex).setAdmin(true);
    }

    public void copyArrayListToVector(ArrayList<Course> arrayList, Vector<Course> courseList){
        for(int i = 0; i < arrayList.size(); ++i){
            courseList.add(arrayList.get(i));
        }
    }
}