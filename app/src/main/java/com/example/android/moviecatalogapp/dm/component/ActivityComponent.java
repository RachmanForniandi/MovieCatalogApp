package com.example.android.moviecatalogapp.dm.component;

import com.example.android.moviecatalogapp.dm.PerActivity;
import com.example.android.moviecatalogapp.dm.module.ActivityModule;
import com.example.android.moviecatalogapp.primary_ui.activities.details.DetailMovieActivity;

import dagger.Component;

/**
 * Created by Lenovo on 11/3/2017.
 */
@PerActivity
@Component(dependencies = TheAppComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(DetailMovieActivity detailMovieActivity);
}
