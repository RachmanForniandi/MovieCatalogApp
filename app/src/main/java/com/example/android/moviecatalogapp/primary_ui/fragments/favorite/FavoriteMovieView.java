package com.example.android.moviecatalogapp.primary_ui.fragments.favorite;

import com.example.android.moviecatalogapp.model.movie.detail.DetailMovie;
import com.example.android.moviecatalogapp.primary_ui.base.MvpView;
import com.example.android.moviecatalogapp.primary_ui.fragments.favorite.AdapterFavoriteMovie.AdapterFavoriteMovie;

/**
 * Created by Lenovo on 11/4/2017.
 */

interface FavoriteMovieView extends MvpView {

    void loadData(AdapterFavoriteMovie adapterFavoriteMovie);

    void itemClickDetail(DetailMovie detailMovie);

    void itemClickShare(DetailMovie detailMovie);
}
