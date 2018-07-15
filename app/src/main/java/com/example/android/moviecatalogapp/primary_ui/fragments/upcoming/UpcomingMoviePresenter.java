package com.example.android.moviecatalogapp.primary_ui.fragments.upcoming;

import android.content.Context;

import com.example.android.moviecatalogapp.BuildConfig;
import com.example.android.moviecatalogapp.api.MovieDbApiService;
import com.example.android.moviecatalogapp.model.movie.upcoming.ResultUpcomingMovie;
import com.example.android.moviecatalogapp.model.movie.upcoming.UpcomingMovie;
import com.example.android.moviecatalogapp.primary_ui.base.MvpPresenter;
import com.example.android.moviecatalogapp.primary_ui.fragments.upcoming.adapter.AdapterUpcomingMovie;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;



public class UpcomingMoviePresenter implements MvpPresenter<UpcomingMovieView>{

    private final String TAG = getClass().getSimpleName();
    private UpcomingMovieView upcomingMovieView;

    @Override
    public void onAttach(UpcomingMovieView mvpView){
        upcomingMovieView = mvpView;
    }

    @Override
    public void onDetach(){
        upcomingMovieView = null;
    }

    void onLoadData(final Context context) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        MovieDbApiService movieDbApiService = retrofit.create(MovieDbApiService.class);
        movieDbApiService.getUpcomingMovie(BuildConfig.API_KEY, BuildConfig.LANGUAGE)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UpcomingMovie>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        /* not yet*/
                    }

                    @Override
                    public void onNext(@NonNull UpcomingMovie upcomingMovie) {
                        AdapterUpcomingMovie adapterUpcomingMovie = new AdapterUpcomingMovie(
                                context,
                                upcomingMovie.getResults(),
                                new AdapterUpcomingMovie.ListenerAdapterUpcomingMovie() {
                                    @Override
                                    public void onItemClickDetail(ResultUpcomingMovie resultUpcomingMovie) {
                                        upcomingMovieView.itemClickDetail(resultUpcomingMovie);
                                    }

                                    @Override
                                    public void onItemClickShare(ResultUpcomingMovie resultUpcomingMovie) {
                                        upcomingMovieView.itemClickShare(resultUpcomingMovie);
                                    }
                                }
                        );
                        upcomingMovieView.loadData(adapterUpcomingMovie);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        e.printStackTrace();
                        upcomingMovieView.loadDataFailed(e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        /*not yet*/
                    }
                });
    }
}
