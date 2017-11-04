package com.example.android.moviecatalogapp.dm.component;

import android.app.Application;
import android.content.Context;

import com.example.android.moviecatalogapp.TheApp;
import com.example.android.moviecatalogapp.data.db.DatabaseHelper;
import com.example.android.moviecatalogapp.data.manager.DataManager;
import com.example.android.moviecatalogapp.dm.TheApplicationContext;
import com.example.android.moviecatalogapp.dm.module.TheAppModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Lenovo on 11/3/2017.
 */
@Singleton
@Component(modules = TheAppModule.class)
public interface TheAppComponent {

    void inject(TheApp app);

    @TheApplicationContext
    Context getContext();

    Application getApplication();

    DatabaseHelper getDatabaseHelper();

    DataManager getDataManager();
}
