package edu.mmisay3.myapplication;


public class Course {
    private String Name;
    private int ProgressPercentage;
    private String Date_LastVisit;

    private int ImageResource;

    public Course(String name, int imageResource){
        this.Name = name;
        this.ImageResource = imageResource;
        this.ProgressPercentage = 0;
        this.Date_LastVisit = "Not yet accessed.";
    }

// Accessor Methods
    public final int GetImageResource( ){
        return this.ImageResource;
    }

    public final String GetCourseName( ){
        return this.Name;
    }

    public final int GetProgressPercentage( ){
        return this.ProgressPercentage;
    }

    public final String GetLastVisitDate( ){
        return this.Date_LastVisit;
    }

// Mutator Methods
    public void setProgressPercentage(int percent){
        this.ProgressPercentage = percent;
    }

    public void setLastVisitDate(String date){
        this.Date_LastVisit = date;
    }
}

