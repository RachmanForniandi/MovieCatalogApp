package com.example.android.moviecatalogapp.primary_ui.activities.details;

import com.example.android.moviecatalogapp.model.movie.detail.DetailMovie;
import com.example.android.moviecatalogapp.primary_ui.base.MvpView;



public interface DetailMovieView extends MvpView {

    void loadData(DetailMovie detailMovie, boolean isFavoriteMovie);

    void loadDataFailed();

    void addToFavoriteMovie();

    void addToFavoriteMovieFailed(String message);

    void deleteFromFavoriteMovie();

    void deleteFromFavoriteMovieFailed(String message);
}
