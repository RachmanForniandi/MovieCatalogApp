package com.example.android.moviecatalogapp;

import android.app.Application;
import android.content.Context;

import com.example.android.moviecatalogapp.dm.component.DaggerTheAppComponent;
import com.example.android.moviecatalogapp.dm.component.TheAppComponent;
import com.example.android.moviecatalogapp.dm.module.TheAppModule;

/**
 * Created by Lenovo on 11/3/2017.
 */

public class TheApp extends Application {

    private final String TAG = getClass().getSimpleName();
    protected TheAppComponent component;

    public static TheApp get(Context context){
        return (TheApp)context.getApplicationContext();
    }

    @Override
    public void onCreate(){
        super.onCreate();
        component = DaggerTheAppComponent
                .builder()
                .theAppModule(new TheAppModule(this))
                .build();
        component.inject(this);
    }

    public TheAppComponent getAppComponent(){
        return component;
    }
}
