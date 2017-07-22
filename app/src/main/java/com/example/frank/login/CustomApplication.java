package com.example.frank.login;

import android.app.Application;

import com.firebase.client.Firebase;

/**
 * Created by Frank on 15/07/2017.
 */

public class CustomApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Firebase.setAndroidContext(this);
    }
}