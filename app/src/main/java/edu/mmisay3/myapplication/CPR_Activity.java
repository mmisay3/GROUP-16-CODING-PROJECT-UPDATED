package edu.mmisay3.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class CPR_Activity extends AppCompatActivity {
    public static final String TAG = "CPR_Activity";
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    private TextView textViewFragmentCount;
    private Button btnNext;
    private Button btnBack;
    private Double fragmentCount = 0.0;
    private final Double maxFragmentCount = 8.0;
    private static Integer progressCount;
    public Boolean isDone = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_c_p_r_);
        fragmentManager = getSupportFragmentManager();

        textViewFragmentCount = (TextView) findViewById(R.id.textViewFragmentCount);
        textViewFragmentCount.setText("Fragment Count in Back Stack: " + fragmentManager.getBackStackEntryCount());

        // Registers (displays) any changes made on the fragment back stack
        fragmentManager.addOnBackStackChangedListener(() -> {
            textViewFragmentCount.setText("Fragment Count in Back Stack: " + fragmentManager.getBackStackEntryCount());

            if(fragmentManager.getBackStackEntryCount() == 1){
                btnBack.setEnabled(false);
            }

            if(fragmentManager.getBackStackEntryCount() > 1 && fragmentManager.getBackStackEntryCount() < 8){
                btnBack.setEnabled(true);
                btnNext.setEnabled(true);
            }

            if(fragmentManager.getBackStackEntryCount() >= 8){
                btnNext.setEnabled(false);
            }


        });

        if(getIntent() != null && getIntent().getExtras() != null && getIntent().hasExtra(EightFragment.TAG)){
            Intent intent = getIntent();
            if(intent.getBooleanExtra(EightFragment.TAG, true)){
                this.isDone = true;
            }
            else {
                this.isDone = false;
            }

            if(this.isDone.equals(true)){
                displayToast("User exits . . . ");
                Intent progressIntent = new Intent(CPR_Activity.this, CourseListActivity.class);
                progressIntent.putExtra(TAG, progressCount);
                displayToast("Sending progressCount = " + progressCount);
                Log.d(TAG, " >> Sending progressCount = " + progressCount);

                startActivity(progressIntent);

            }
            else{
                displayToast("fail . . . ");
            }
        }




        btnNext = findViewById(R.id.btnNext);
        btnBack = findViewById(R.id.btnBack);

        btnBack.setEnabled(false);
        addFragment();

        btnNext.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                addFragment( );
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                fragmentManager.popBackStack();
            }
        });
    }

    private void addFragment(){
        Boolean finalFragmentAdded = false;

        Fragment fragment = new Fragment();
        switch(fragmentManager.getBackStackEntryCount()){
            case 0: fragment = new FirstFragment();   break;
            case 1: fragment = new SecondFragment();  break;
            case 2: fragment = new ThirdFragment();   break;
            case 3: fragment = new FourthFragment();  break;
            case 4: fragment = new FifthFragment();   break;
            case 5: fragment = new SixthFragment();   break;
            case 6: fragment = new SeventhFragment(); break;
            case 7:
                EightFragment finalFrag = new EightFragment();
                fragmentTransactor(finalFrag);
                finalFragmentAdded = true;
                break;
        }

        ++this.fragmentCount;
        Double val = (fragmentCount/maxFragmentCount) * 100.0;
        this.progressCount = val.intValue();

        displayToast("PROGRESS COUNT = " + this.progressCount);
        Log.d(TAG, "addFragment: val = " + val);
        Log.d(TAG, "addFragment: FRAGMENT COUNT = " + this.fragmentCount);
        Log.d(TAG, "addFragment: maxFragmentCount COUNT = " + this.maxFragmentCount);
        Log.d(TAG, "addFragment: f/d = " + (fragmentCount/maxFragmentCount));
        Log.d(TAG, "addFragment: PROGRESS COUNT = " + this.progressCount);

        if(finalFragmentAdded.equals(false))
            fragmentTransactor(fragment);
    }

    void fragmentTransactor(Fragment frag){
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragmentContainerFrameLayout, frag, "demoFragment");
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    // When the default Back button on the android is pressed
    @Override
    public void onBackPressed() {
        Fragment fragment = fragmentManager.findFragmentById(R.id.fragmentContainerFrameLayout);

        if(fragment != null){ // IF a fragment exists in the container
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.detach(fragment); // or use replace
            fragmentTransaction.commit();
        }
        else{
            super.onBackPressed();
        }
    }

    public void displayToast(String str){
        Context context = getApplicationContext();
        CharSequence text= str;
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }
}