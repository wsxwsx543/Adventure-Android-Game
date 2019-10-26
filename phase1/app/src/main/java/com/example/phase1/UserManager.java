package com.example.phase1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class UserManager {
    // The list that stores all the users
    public static ArrayList<User> users = new ArrayList<>();

    // Add a user in users
    public void addUser(User user){
        users.add(user);
    }
    // Rewrite users to the declared file name
    public void writeUsers(String usersFileName){
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(usersFileName));

            objectOutputStream.writeObject(users);
            objectOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void outputUsers(String usersFileName){
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(usersFileName));
            try {
                users = (ArrayList<User>) objectInputStream.readObject();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
