package com.example.android.moviecatalogapp.dm.module;

import android.content.ContentProvider;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Lenovo on 11/4/2017.
 */
@Module
public class ProviderModule {

    private ContentProvider contentProvider;

    public ProviderModule(ContentProvider contentProvider){
        this.contentProvider = contentProvider;
    }

    @Provides
    ContentProvider provideContentProvider(){
        return contentProvider;
    }
}
