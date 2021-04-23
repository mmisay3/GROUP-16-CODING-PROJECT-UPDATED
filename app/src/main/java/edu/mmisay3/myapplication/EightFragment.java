package edu.mmisay3.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class EightFragment extends Fragment {

    public static final String TAG = "EightFragment";
    Button button;
    Button button2;
    Button button3;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_eigth, container, false);

        button = view.findViewById(R.id.video);
        button2 = view.findViewById(R.id.wiki);
        button3 = view.findViewById(R.id.exit);

        button.setOnClickListener(v -> {
            Uri uri = Uri.parse("https://www.youtube.com/watch?v=1lwRQTGzKcw");
            Intent intent = new Intent(Intent.ACTION_VIEW,uri);
            startActivity(intent);
        });

        button2.setOnClickListener(v -> {
            Uri uri = Uri.parse("https://en.wikipedia.org/wiki/Cardiopulmonary_resuscitation");
            Intent intent = new Intent(Intent.ACTION_VIEW,uri);
            startActivity(intent);
        });

        button3.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), CPR_Activity.class);
            Boolean isDone = true;
            intent.putExtra(TAG, isDone);
            startActivity(intent);
        });

        return view;
    }
}
