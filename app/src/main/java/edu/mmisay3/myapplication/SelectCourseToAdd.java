package edu.mmisay3.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Vector;

public class SelectCourseToAdd extends AppCompatActivity {

    final static String USER_KEY = "USER_KEY";
    ArrayList<Course> arrayCourse;
    CheckBox cpr;
    CheckBox aed;
    CheckBox wfs;
    CheckBox ssa;
    CheckBox cc;
    Button btnDone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_course_to_add);

        arrayCourse = new ArrayList<>();

        cpr = (CheckBox) findViewById(R.id.checkBoxCPR);
        aed = (CheckBox) findViewById(R.id.checkBoxAED);
        wfs = (CheckBox) findViewById(R.id.checkBoxWFS);
        ssa = (CheckBox) findViewById(R.id.checkBoxSSA);
        cc = (CheckBox) findViewById(R.id.checkBoxCC);
        btnDone = (Button) findViewById(R.id.btnDone);

        btnDone.setOnClickListener(v -> {
            if(cpr.isChecked())
                arrayCourse.add(new Course("CPR", R.drawable.courseimage1));
            if(aed.isChecked())
                arrayCourse.add(new Course("AED", R.drawable.aedimage));
            if(wfs.isChecked())
                arrayCourse.add(new Course("Water and Fire Safety", R.drawable.firesafety));
            if(ssa.isChecked())
                arrayCourse.add(new Course("Scene and Safety Assessment", R.drawable.sceneandsafety));
            if(cc.isChecked())
                arrayCourse.add(new Course("Child Care", R.drawable.childcare));

            displayToast("SIZE is " + arrayCourse.size());

            Intent send = new Intent(SelectCourseToAdd.this, AdminPage.class);
            send.putExtra(USER_KEY, arrayCourse);
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
}