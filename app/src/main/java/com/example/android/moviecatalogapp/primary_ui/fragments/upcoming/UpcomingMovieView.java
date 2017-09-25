package com.example.android.moviecatalogapp.primary_ui.fragments.upcoming;

import com.example.android.moviecatalogapp.model.movie.upcoming.ResultUpcomingMovie;
import com.example.android.moviecatalogapp.primary_ui.base.MvpView;
import com.example.android.moviecatalogapp.primary_ui.fragments.upcoming.adapter.AdapterUpcomingMovie;

/**
 * Created by Lenovo on 9/24/2017.
 */

interface UpcomingMovieView extends MvpView{

    void itemClickDetail(ResultUpcomingMovie resultUpcomingMovie);

    void itemClickShare(ResultUpcomingMovie resultUpcomingMovie);

    void loadData(AdapterUpcomingMovie adapterUpcomingMovie);

    void loadDataFailed(String message);

}
