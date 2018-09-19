package com.academy.ferdowsi.training;

import android.content.Context;


public class Applications extends android.app.Application {
    public static final String TAG = Applications.class.getSimpleName();
    private static volatile Applications instance;
    private static Context context;

    public Applications() {
        context = this;
    }

    public static synchronized Applications getInstance() {
        return instance;
    }

    public static Context getContext() {
        return context;
    }

    public void onCreate() {
        super.onCreate();
        instance = this;
    }

}
