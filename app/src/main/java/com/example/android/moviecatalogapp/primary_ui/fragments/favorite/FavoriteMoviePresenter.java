package com.example.android.moviecatalogapp.primary_ui.fragments.favorite;

import android.content.Context;

import com.example.android.moviecatalogapp.data.manager.DataManager;
import com.example.android.moviecatalogapp.model.movie.detail.DetailMovie;
import com.example.android.moviecatalogapp.primary_ui.base.MvpPresenter;
import com.example.android.moviecatalogapp.primary_ui.fragments.favorite.AdapterFavoriteMovie.AdapterFavoriteMovie;

import java.util.List;

/**
 * Created by Lenovo on 11/4/2017.
 */

class FavoriteMoviePresenter implements MvpPresenter<FavoriteMovieView>{

    private final String TAG = getClass().getSimpleName();
    private FavoriteMovieView favoriteMovieView;

    @Override
    public void onAttach(FavoriteMovieView mvpView) {
        favoriteMovieView = mvpView;
    }

    @Override
    public void onDetach() {
        favoriteMovieView = null;
    }

    void onLoadData(Context context, DataManager dataManager){
        List<DetailMovie> listFavoriteMovie = dataManager.getAll();
        AdapterFavoriteMovie adapterFavoriteMovie = new AdapterFavoriteMovie(
                context,
                listFavoriteMovie,
                new AdapterFavoriteMovie.ListenerAdapterFavoriteMovie() {
                    @Override
                    public void onItemClickDetail(DetailMovie detailMovie) {
                        favoriteMovieView.itemClickDetail(detailMovie);
                    }

                    @Override
                    public void onItemClickShare(DetailMovie detailMovie) {
                        favoriteMovieView.itemClickShare(detailMovie);
                    }
                }
        );
        favoriteMovieView.loadData(adapterFavoriteMovie);
    }
}
