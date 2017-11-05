package com.example.android.moviecatalogapp.primary_ui.fragments.favorite;

import com.example.android.moviecatalogapp.data.manager.DataManager;
import com.example.android.moviecatalogapp.primary_ui.base.MvpPresenter;

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

    void onLoadData(DataManager dataManager){
        dataManager.getAll();
    }
}
