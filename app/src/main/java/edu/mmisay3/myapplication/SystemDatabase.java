package edu.mmisay3.myapplication;


import java.util.Vector;

//The system database will hold all every user's information
public class SystemDatabase {
    Vector<User> newuser = new Vector<>(); //Array holding all user's data
    int usersStored; //Number of users stored in user's database

    // Add more users to database
    public void createAccount(String firstName, String lastName, int age, String username, String password) {
        newuser.add(new User(firstName, lastName, age, username, password));
        usersStored = newuser.size();
    }

    // Adds a user to the database where the arguments is of User object type
    public void addUser(User user){
        newuser.add(user);
        usersStored = newuser.size();
    }

    // Returns the index of the username (if found)
    // otherwise, returns -999 (not found)
    public Integer searchUser(String _username){
        _username = _username.toLowerCase();
        for(int i = 0; i < usersStored; ++i){
            String username_dataBase = newuser.get(i).getUsername().toLowerCase();
            if(username_dataBase.equals(_username))
                return i;
        }
        return -999;
    }

    // Searches for the username (email) in the Vector<User> newuser (Runtime O(n))
    // Returns true if username is found, otherwise, returns false
    public Boolean usernameIsFound(String _username){
        if(searchUser(_username) < 0) // User name is not found
            return false;
        return true;
    }

    // Verifies whether the password input is valid based on the username entry
    // Returns true for valid password, else, returns false
    public Boolean passwordValidation(String _username, String _password){
        if(usernameIsFound(_username).equals(false)){
            return false; // Username is not found
        }
        else{
            Integer index = searchUser(_username);
            String password = this.newuser.elementAt(index).getPassword( );
            if(_password.equals(password)){
                return  true;
            }
        }
        return false; // Password is NOT valid
    }
}
