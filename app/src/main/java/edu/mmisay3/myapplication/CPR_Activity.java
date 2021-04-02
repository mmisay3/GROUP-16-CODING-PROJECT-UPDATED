package edu.mmisay3.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class CPR_Activity extends AppCompatActivity {
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    private TextView textViewFragmentCount;
    private Button btnNext;
    private Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_c_p_r_);
        fragmentManager = getSupportFragmentManager();



        textViewFragmentCount = (TextView) findViewById(R.id.textViewFragmentCount);
        textViewFragmentCount.setText("Fragment Count in Back Stack: " + fragmentManager.getBackStackEntryCount());

        // Registers (displays) any changes made on the fragment back stack
        fragmentManager.addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {
                textViewFragmentCount.setText("Fragment Count in Back Stack: " + fragmentManager.getBackStackEntryCount());
            }
        });

        btnNext = (Button) findViewById(R.id.btnNext);
        btnBack = (Button) findViewById(R.id.btnBack);

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

        Fragment fragment;
        switch(fragmentManager.getBackStackEntryCount()){
            case 0: fragment = new FirstFragment(); break; // If there is 0 fragment in the backstack, add FirstFragment
            case 1: fragment = new SecondFragment(); break; // If a fragment already exist, add secondFragment onto stack
            case 2: fragment = new ThirdFragment(); break;
            case 3: fragment = new FourthFragment(); break;
            case 4: fragment = new FifthFragment(); break;
            case 5: fragment = new SixthFragment(); break;
            case 6: fragment = new SeventhFragment();break;
            case 7: fragment = new EightFragment(); break;
            default: fragment = new FirstFragment(); break;
        }

        // Fragment Transaction has to be executed every time a fragment is creadted
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragmentContainerFrameLayout, fragment, "demoFragment");
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
}