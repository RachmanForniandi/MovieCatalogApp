package com.example.android.moviecatalogapp;

import android.app.Application;
import android.content.Context;

import com.example.android.moviecatalogapp.dm.component.TheAppComponent;

/**
 * Created by Lenovo on 11/3/2017.
 */

public class TheApp extends Application {

    protected TheAppComponent component;

    public static TheApp get(Context context){
        return (TheApp)context.getApplicationContext();
    }

    @Override
    public void onCreate(){
        super.onCreate();
    }
}
