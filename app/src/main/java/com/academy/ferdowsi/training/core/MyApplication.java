package com.academy.ferdowsi.training.core;

import android.app.Application;
import android.content.Context;

import com.academy.ferdowsi.training.core.di.component.ApiComponent;
import com.academy.ferdowsi.training.core.di.component.DaggerApiComponent;
import com.academy.ferdowsi.training.core.di.module.ContextModule;


public class MyApplication extends Application {

    private static volatile MyApplication instance;
    private static ApiComponent mApiComponent;
    private volatile Context mContext;

    public static synchronized MyApplication getInstance() {
        return instance;
    }

    public static ApiComponent getApiComponent() {
        return mApiComponent;
    }

    @Override
    protected void attachBaseContext(Context base) {
        instance = this;
        mContext = base;
        super.attachBaseContext(/*CalligraphyContextWrapper.wrap(*/base/*)*/);
    }

    public Context getContext() {
        return mContext;
    }

    public void setContext(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        mApiComponent = DaggerApiComponent.builder().contextModule(new ContextModule(this)).build();

    }
}