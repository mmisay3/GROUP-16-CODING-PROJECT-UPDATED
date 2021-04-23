package edu.mmisay3.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Vector;

public class AdminPage extends AppCompatActivity {
    final static String ADMIN_KEY = "ADMIN_KEY";
    Button btnAddorDelCourse;
    Button btnSignOut;
    Vector<Course> courseList;
    ArrayList<Course> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_page);

        courseList = new Vector<>();
        arrayList = new ArrayList<>();

        btnAddorDelCourse = (Button) findViewById(R.id.addorDelCourse);
        btnSignOut = (Button) findViewById(R.id.signOut);

        if(getIntent() != null && getIntent().getExtras() != null && getIntent().hasExtra(SelectCourseToAdd.USER_KEY)){
            arrayList = (ArrayList<Course>)getIntent().getSerializableExtra(SelectCourseToAdd.USER_KEY);
            // displayToast("courseList size = " + arrayList.size());
            // copyArrayListToVector(arrayList, courseList);
        }

        btnAddorDelCourse.setOnClickListener(v->{
            Intent intent = new Intent(AdminPage.this, SelectCourseToAdd.class);
            startActivity(intent);
        });

        btnSignOut.setOnClickListener(v->{
            Intent send = new Intent(AdminPage.this, MainLogInActivity.class);
            send.putExtra(ADMIN_KEY, arrayList);
            startActivity(send);
        });

    }

    // Utility Methods -----------------------------------------------------------------------------
    public void displayToast(String str){
        Context context = getApplicationContext();
        CharSequence text= str;
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    public void copyArrayListToVector(ArrayList<Course> arrayList, Vector<Course> courseList){
        for(int i = 0; i < arrayList.size(); ++i){
            courseList.add(arrayList.get(i));
        }
    }
}