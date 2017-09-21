package com.example.android.moviecatalogapp.primary_ui.activities.fragments;

import com.example.android.moviecatalogapp.primary_ui.activities.base.MvpView;
import com.example.android.moviecatalogapp.primary_ui.activities.fragments.adapter.AdapterSearchMovie;

/**
 * Created by Lenovo on 9/20/2017.
 */

interface SearchMovieView extends MvpView {
    void searchMovie(AdapterSearchMovie adapterSearchMovie);

    void searchMovieFailed();
}
