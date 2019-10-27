package com.example.phase1;

import java.io.Serializable;
import java.util.HashMap;

/*
 * Based on Singleton Design Pattern. https://www.geeksforgeeks.org/singleton-design-pattern/
 */
public class UserManager implements Serializable {
    private static UserManager userManager = null;

    // The list that stores all the users
    private HashMap<String, User> users;

    private User curUser = null;

    private UserManager(){

    }

    public User getCurUser(){return curUser;}
    public void setCurUser(User curUser){this.curUser = curUser;}

    public void setUsers(HashMap<String, User> users){
        this.users = users;
    }

    public HashMap<String, User> getUsers(){
        return users;
    }

    // Get the only instance in this class.
    public static UserManager getInstance(){
        if(userManager == null){
            userManager = new UserManager();
        }
        return userManager;
    }

    // Add a user in users
    public void addUser(User user){
        users.put(user.getUsername(), user);
    }
}
