package com.example.android.moviecatalogapp.dm.component;

import com.example.android.moviecatalogapp.dm.PerFragment;
import com.example.android.moviecatalogapp.dm.module.FragmentModule;
import com.example.android.moviecatalogapp.primary_ui.fragments.favorite.FavoriteMovieFragment;

import dagger.Component;

/**
 * Created by Lenovo on 11/5/2017.
 */
@PerFragment
@Component(dependencies = TheAppComponent.class, modules = FragmentModule.class)

public interface FragmentComponent {
    void inject(FavoriteMovieFragment favoriteMovieFragment);
}
