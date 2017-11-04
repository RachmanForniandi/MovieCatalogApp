package com.example.android.moviecatalogapp.dm.module;

import android.app.Application;
import android.content.Context;

import com.example.android.moviecatalogapp.dm.DatabaseInfo;
import com.example.android.moviecatalogapp.dm.TheApplicationContext;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Lenovo on 11/3/2017.
 */
@Module
public class TheAppModule {

    private final Application application;

    public TheAppModule(Application application){
        this.application = application;
    }

    @Provides
    @TheApplicationContext
    Context provideContext(){
        return application;
    }

    @Provides
    Application provideApplication(){
        return application;
    }

    @Provides
    @DatabaseInfo
    String provideDatabaseName(){
        return "MovieCatalog.db";
    }

    @Provides
    @DatabaseInfo
    Integer provideDatabaseVersion(){
        return 1;
    }
}
