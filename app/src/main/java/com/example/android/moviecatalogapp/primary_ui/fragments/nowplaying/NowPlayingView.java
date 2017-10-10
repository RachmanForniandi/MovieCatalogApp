package com.example.android.moviecatalogapp.primary_ui.fragments.nowplaying;

import com.example.android.moviecatalogapp.model.movie.nowplaying.ResultNowPlaying;
import com.example.android.moviecatalogapp.primary_ui.base.MvpView;
import com.example.android.moviecatalogapp.primary_ui.fragments.nowplaying.adapter.AdapterNowPlayingMovie;

/**
 * Created by Lenovo on 10/10/2017.
 */

interface NowPlayingView extends MvpView{

    void itemClickDetail(ResultNowPlaying resultNowPlaying);

    void  itemClickShare(ResultNowPlaying resultNowPlaying);

    void loadData(AdapterNowPlayingMovie adapterNowPlayingMovie);

    void loadDataFailed(String message);
}
