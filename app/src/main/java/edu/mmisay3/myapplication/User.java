package edu.mmisay3.myapplication;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Vector;

public class User implements Serializable {
    String firstName; //User's First name
    String lastName; //User's last name
    int age;
    String username; //User's name
    String password; //User's password

    String orgName; //Name of org or "none" if individual
    String position; //Admin, employee, or individual

    private Boolean IsAdmin;
    public ArrayList<Course> CourseList;

    public User(String firstName, String lastName, int age, String username, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.username = username;
        this.password = password;
        this.IsAdmin = false;
        // CourseList = new Vector< >( );
    }

    public void setDefaultCourseList( ){
        CourseList = new ArrayList< >( );
        CourseList.add(new Course("CPR", R.drawable.ic_android_black_24dp));
        CourseList.add(new Course("AED", R.drawable.ic_android_black_24dp));
        CourseList.add(new Course("Water and Fire Safety", R.drawable.ic_android_black_24dp));
        CourseList.add(new Course("Scene and Safety Assessment", R.drawable.ic_android_black_24dp));
        CourseList.add(new Course("Child Care", R.drawable.ic_android_black_24dp));
    }

    public void initializeVector(ArrayList<Course> v){
        CourseList = v;
    }

    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }

    public void setAdmin(Boolean val){
        this.IsAdmin = val;
    }
    public boolean isAdmin( ){
        return this.IsAdmin;
    }

    //Only do the following if they are part of an org
    public void setOrg(String orgName){
        this.orgName = orgName;
    }
    public void  setPosition(String position){
        this.position = position;
    }

    public String getOrg(){
        return orgName;
    }
    public String getPosition(){
        return position;
    }
}
