package edu.mmisay3.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Vector;

public class CourseListActivity extends AppCompatActivity {
    private CustomAdapter MyAdapter;
    private RecyclerView MyRecyclerView;
    private RecyclerView.LayoutManager MyLayoutManager;
    public Vector<Course> CourseList;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Course Items");

        // Initializes the Vector containing each individual course types
        initializeVectorCourseList( );

        // IF data received is not null
            // CourseList.add(new Course("String Received", R.drawable.ic_android_black_24dp));

        // IF admin has deleted a course (this activity receives boolean value  that wants to delete a course and name of the course)
        deleteCourseByName("AED");


        // Instantiates the Recycler View
        buildRecyclerView( );

        CourseList.elementAt(CourseIndex.CPR.index).setProgressPercentage(100);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void buildRecyclerView( ){
        MyRecyclerView = findViewById(R.id.MainActivity_recyclerView);
        MyRecyclerView.setHasFixedSize(true); // Increases performance of the app
        MyLayoutManager = new LinearLayoutManager(this);
        MyAdapter = new CustomAdapter(CourseList);
        MyRecyclerView.setLayoutManager(MyLayoutManager);
        MyRecyclerView.setAdapter(MyAdapter);

        // Clicking the item on the list updates the Last Visit then proceeds to the specified Course
        // indicated by the CourseIndex Integer positions which ranges from CPR(0), AED(1), WFS(2), SSA(3), CC(4)
        MyAdapter.setOnItemClickListener(position -> {
            CourseList.elementAt(position).setLastVisitDate("Last Visit: " + LocalDate.now().toString());
            MyAdapter.notifyItemChanged(position);

            if(position.equals(CourseIndex.CPR.index)){
                displayToast("Opening CPR Activity");
                Intent intent = new Intent(CourseListActivity.this, CPR_Activity.class);
                startActivity(intent);
            }
            else if(position.equals(CourseIndex.AED.index)){
                displayToast("Opening AED Activity");
            }
            else if(position.equals(CourseIndex.WFS.index)){
                displayToast("Opening WFS Activity");
            }
            else if(position.equals(CourseIndex.SSA.index)){
                displayToast("Opening SSA Activity");
            }
            else if(position.equals(CourseIndex.CC.index)){
                displayToast("Opening Child Care Activity");
            }
        });
    }

    public void initializeVectorCourseList( ){
        CourseList = new Vector< >( );
        CourseList.add(new Course("CPR", R.drawable.ic_android_black_24dp));
        CourseList.add(new Course("AED", R.drawable.ic_android_black_24dp));
        CourseList.add(new Course("Water and Fire Safety", R.drawable.ic_android_black_24dp));
        CourseList.add(new Course("Scene and Safety Assessment", R.drawable.ic_android_black_24dp));
        CourseList.add(new Course("Child Care", R.drawable.ic_android_black_24dp));

    }

    // Utility Methods -----------------------------------------------------------------------------
    public void displayToast(String str){
        Context context = getApplicationContext();
        CharSequence text= str;
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    void deleteCourseByName(String name){
        for(int i = 0; i < CourseList.size(); ++i){
            if(CourseList.elementAt(i).GetCourseName().equals(name)){
                CourseList.removeElementAt(i);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.example_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.help:
                Toast.makeText(this,"Select a course and complete it",Toast.LENGTH_LONG).show();
                return true;
            case R.id.mode:
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                return true;
            case R.id.mode2:
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                return true;
            case R.id.logout:
                Intent intent = new Intent(this, MainLogInActivity.class);
                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
