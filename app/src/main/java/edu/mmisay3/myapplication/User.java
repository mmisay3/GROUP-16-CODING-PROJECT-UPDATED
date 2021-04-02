package edu.mmisay3.myapplication;


import java.io.Serializable;

public class User implements Serializable {
    String firstName; //User's First name
    String lastName; //User's last name
    int age;
    String username; //User's name
    String password; //User's password

    String orgName; //Name of org or "none" if individual
    String position; //Admin, employee, or individual


    public User(String firstName, String lastName, int age, String username, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.username = username;
        this.password = password;
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
