package com.example.android.moviecatalogapp.dm.component;

import com.example.android.moviecatalogapp.dm.PerProvider;
import com.example.android.moviecatalogapp.dm.module.ProviderModule;
import com.example.android.moviecatalogapp.provider.FavoriteMovieProvider;

import dagger.Component;

/**
 * Created by Lenovo on 11/4/2017.
 */
@PerProvider
@Component(dependencies = TheAppComponent.class, modules = ProviderModule.class)
public interface ProviderComponent {

    void inject(FavoriteMovieProvider favoriteMovieProvider);
}
