package com.example.android.moviecatalogapp.primary_ui.fragments.nowplaying;

import android.content.Context;

import com.example.android.moviecatalogapp.BuildConfig;
import com.example.android.moviecatalogapp.api.MovieDbApiService;
import com.example.android.moviecatalogapp.model.movie.nowplaying.NowPlaying;
import com.example.android.moviecatalogapp.model.movie.nowplaying.ResultNowPlaying;
import com.example.android.moviecatalogapp.primary_ui.base.MvpPresenter;
import com.example.android.moviecatalogapp.primary_ui.fragments.nowplaying.adapter.AdapterNowPlayingMovie;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Lenovo on 10/10/2017.
 */

public class NowPlayingPresenter implements MvpPresenter<NowPlayingView>{

    private final String TAG = getClass().getSimpleName();
    private NowPlayingView nowPlayingView;


    @Override
    public void onAttach(NowPlayingView mvpView) {
        nowPlayingView = mvpView;
    }

    @Override
    public void onDetach() {
        nowPlayingView = null;
    }

    void onLoadData(final Context context){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        MovieDbApiService movieDbApiService = retrofit.create(MovieDbApiService.class);
        movieDbApiService.getNowPlayingMovie(BuildConfig.API_KEY, BuildConfig.LANGUAGE)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<NowPlaying>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        /* not yet*/
                    }

                    @Override
                    public void onNext(@NonNull NowPlaying nowPlaying) {
                        AdapterNowPlayingMovie adapterNowPlayingMovie = new AdapterNowPlayingMovie(
                                context,
                                nowPlaying.getResults(),
                                new AdapterNowPlayingMovie.ListenerViewHolderNowPlayingMovie() {

                                    @Override
                                    public void onItemClickDetail(ResultNowPlaying resultNowPlaying) {
                                        nowPlayingView.itemClickDetail(resultNowPlaying);
                                    }

                                    @Override
                                    public void onItemClickShare(ResultNowPlaying resultNowPlaying) {
                                        nowPlayingView.itemClickShare(resultNowPlaying);
                                    }
                                }
                        );
                        nowPlayingView.loadData(adapterNowPlayingMovie);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        e.printStackTrace();
                        nowPlayingView.loadDataFailed(e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        /*not yet*/
                    }
                });
    }
}
