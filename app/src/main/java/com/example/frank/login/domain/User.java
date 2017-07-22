package com.example.frank.login.domain;

import android.content.Context;


import com.example.frank.login.domain.util.CryptWithMD5;
import com.example.frank.login.domain.util.LibraryClass;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;

import static android.os.Build.ID;


public class User {
    public static String TOKEN = "com.example.frank.login.domain.User.TOKEN";

    @Exclude
    private String id;
    private String name;
    private String email;
    @Exclude
    private String password;


    public User(){}


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void generateCryptPassword() {
        password = CryptWithMD5.cryptWithMD5(password);
    }



    public void saveTokenSP(Context context, String token ){
        LibraryClass.saveSP( context, TOKEN, token );
    }

    public String getTokenSP(Context context ){
        String token = LibraryClass.getSP( context, TOKEN );
        return( token );
    }

    public void saveIdSP(Context context, String token ){
        LibraryClass.saveSP( context, ID, token );
    }


    public void saveDB( DatabaseReference.CompletionListener... completionListener ) {
        DatabaseReference firebase = LibraryClass.getFirebase().child("users").child(getId());

        if (completionListener.length == 0) {
            firebase.setValue(this);
        } else {
            firebase.setValue(this, completionListener[0]);
        }
    }
}