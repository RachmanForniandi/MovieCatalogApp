package com.example.android.moviecatalogapp.dm.module;

import android.app.Activity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Lenovo on 11/4/2017.
 */
@Module
public class ActivityModule {

    private Activity activity;

    public ActivityModule(Activity activity){
        this.activity = activity;
    }

    @Provides
    Activity provideActivity(){
        return activity;
    }
}
