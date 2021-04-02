package edu.mmisay3.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.Vector;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {
    private Vector<Course> CourseList;
    private OnItemClickListener OnClickListener;

    // Interface for OnItemClickListener
    public interface OnItemClickListener {void onItemClick(Integer position);}

    // Paramaterized Constructor for the private CourseList class data field
    public CustomAdapter(Vector<Course> _courseList){
        this.CourseList = _courseList;
    }

    // Assigns the listener argument to the CustomAdapter OnItemClickListener private data field
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.OnClickListener = listener;
    }

    // The static item, when created, that gets placed in the
    // RecyclerView list. The static keyword is what allows
    // the item to not get destroyed as the user scrolls down the list
    public static class ViewHolder extends RecyclerView.ViewHolder{
        // These fields refer to the views declared in the XML layout
        //  file that designs the item belonging to the RecyclerView
        public ImageView CourseImage;
        public TextView CourseName;
        public ProgressBar CourseProgressBar;
        public TextView CourseProgressPercentage;
        public TextView CourseLastVisited;

        public ViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            this.CourseImage = itemView.findViewById(R.id.CourseImage);
            this.CourseName  = itemView.findViewById(R.id.CourseName);
            this.CourseProgressBar = itemView.findViewById(R.id.progressBar);
            this.CourseProgressPercentage = itemView.findViewById(R.id.progress_percentage);
            this.CourseLastVisited = itemView.findViewById(R.id.lastVisitDate);

            itemView.setOnClickListener(v -> {
                if(listener != null){
                    Integer position = getAdapterPosition();
                    if(position != RecyclerView.NO_POSITION){
                        listener.onItemClick(position); // User-defined interface
                    }
                }
            });
        }
    }

    @NonNull
    @Override // Initializes the ViewHolder static class without filling its contents
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View listView = inflater.inflate(R.layout.recyclerview_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(listView, OnClickListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Course courseItem = this.CourseList.get(position);
        holder.CourseImage.setImageResource(courseItem.GetImageResource( ));
        holder.CourseName.setText(courseItem.GetCourseName( ));
        holder.CourseLastVisited.setText(courseItem.GetLastVisitDate( ));
        holder.CourseProgressPercentage.setText(String.valueOf(courseItem.GetProgressPercentage( )));
        holder.CourseProgressBar.setProgress(courseItem.GetProgressPercentage( ));
    }

    @Override
    public int getItemCount() {
        return CourseList.size();
    }
}

