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
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Vector;

public class CourseListActivity extends AppCompatActivity {
    public static final String TAG = "CourseListActivity";
    private CustomAdapter MyAdapter;
    private RecyclerView MyRecyclerView;
    private RecyclerView.LayoutManager MyLayoutManager;
    public static Vector<Course> vectCourseList;

    private User user;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: ## ON CREATE!!");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Course Selection");

        vectCourseList = new Vector<>();


        // PROBLEM: When starting this activity from CPR_Activity, the 
        if(getIntent() != null && getIntent().getExtras() != null && getIntent().hasExtra(MainLogInActivity.MAIN_KEY)){
            user = (User)getIntent().getSerializableExtra(MainLogInActivity.MAIN_KEY);
            // displayToast("COURSE! size = " + user.CourseList.size());
            copyArrayListToVector(user.CourseList, vectCourseList);
        }

        if(getIntent() != null && getIntent().getExtras() != null && getIntent().hasExtra(CPR_Activity.TAG)){
            Log.d(TAG, "onCreate: ## PROGRESS RECEIVED FROM CPR!!!");
            initializeVectorCourseList( );
            Intent intent = getIntent();
            Integer progressPercentage = intent.getIntExtra(CPR_Activity.TAG, 0);

//            Bundle bundle = getIntent().getExtras();
//            Integer progressPercentage = bundle.getInt(CPR_Activity.TAG, -1);
            displayToast("progress = " + progressPercentage);
            Log.d(TAG, ">> Received ProgressCount = " + progressPercentage);
            vectCourseList.elementAt(CourseIndex.CPR.index).setProgressPercentage(progressPercentage);
            vectCourseList.elementAt(CourseIndex.CPR.index).setLastVisitDate("Last Visit: " + LocalDate.now().toString());
        }
        else{
            displayToast("No progress received.");
        }

        // Initializes the Vector containing each individual course types
        // initializeVectorCourseList( );

        // Instantiates the Recycler View
        buildRecyclerView( );

        // vectCourseList.elementAt(CourseIndex.CPR.index).setProgressPercentage(100);
    }

    @Override
    protected void onStart() {
        Log.d(TAG, "onStart: ## ON START");
        super.onStart();
    }

    @Override
    protected void onRestart() {
        Log.d(TAG, "onRestart: ## ON RESTART");
        super.onRestart();
    }

    @Override
    protected void onResume() {
        Log.d(TAG, "onResume: ## ON RESUME");
        super.onResume();
    }

    @Override
    protected void onPause() {
        Log.d(TAG, "onPause: ## ON PAUSE!!");
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.d(TAG, "onStop: ## STOPPING!!!");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.d(TAG, "onDestroy: ## DESTROYING!!");
        super.onDestroy();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void buildRecyclerView( ){
        MyRecyclerView = findViewById(R.id.MainActivity_recyclerView);
        MyRecyclerView.setHasFixedSize(true); // Increases performance of the app
        MyLayoutManager = new LinearLayoutManager(this);
        MyAdapter = new CustomAdapter(vectCourseList);
        MyRecyclerView.setLayoutManager(MyLayoutManager);
        MyRecyclerView.setAdapter(MyAdapter);

        // Clicking the item on the list updates the Last Visit then proceeds to the specified Course
        // indicated by the CourseIndex Integer positions which ranges from CPR(0), AED(1), WFS(2), SSA(3), CC(4)
        MyAdapter.setOnItemClickListener(position -> {
            vectCourseList.elementAt(position).setLastVisitDate("Last Visit: " + LocalDate.now().toString());
            MyAdapter.notifyItemChanged(position);

            if(position >= vectCourseList.size()){
                displayToast("Invalid");
            }
            else if(position.equals(CourseIndex.CPR.index)){
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
         vectCourseList = new Vector< >( );

        vectCourseList.add(new Course("CPR", R.drawable.courseimage1));
        vectCourseList.add(new Course("AED", R.drawable.aedimage));
        vectCourseList.add(new Course("Water and Fire Safety", R.drawable.firesafety));
        vectCourseList.add(new Course("Scene and Safety Assessment", R.drawable.sceneandsafety));
        vectCourseList.add(new Course("Child Care", R.drawable.childcare));

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
        for(int i = 0; i < vectCourseList.size(); ++i){
            if(vectCourseList.elementAt(i).GetCourseName().equals(name)){
                vectCourseList.removeElementAt(i);
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

    public void copyArrayListToVector(ArrayList<Course> arrayList, Vector<Course> courseList){
        for(int i = 0; i < arrayList.size(); ++i){
            courseList.add(arrayList.get(i));
        }
    }

}
