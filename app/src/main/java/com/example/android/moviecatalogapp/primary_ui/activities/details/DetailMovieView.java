package com.example.android.moviecatalogapp.primary_ui.activities.details;

import com.example.android.moviecatalogapp.model.movie.detail.DetailMovie;
import com.example.android.moviecatalogapp.primary_ui.activities.base.MvpView;

/**
 * Created by Lenovo on 9/21/2017.
 */

public interface DetailMovieView extends MvpView {
    void loadData(DetailMovie detailMovie);

    void loadDataFailed();
}
