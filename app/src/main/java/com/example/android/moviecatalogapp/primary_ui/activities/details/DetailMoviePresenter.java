package com.example.android.moviecatalogapp.primary_ui.activities.details;

import android.content.Context;
import android.util.Log;

import com.example.android.moviecatalogapp.BuildConfig;
import com.example.android.moviecatalogapp.api.MovieDbApiService;
import com.example.android.moviecatalogapp.data.manager.DataManager;
import com.example.android.moviecatalogapp.model.movie.detail.DetailMovie;
import com.example.android.moviecatalogapp.primary_ui.base.MvpPresenter;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class DetailMoviePresenter implements MvpPresenter<DetailMovieView> {

    private final String TAG = getClass().getSimpleName();
    private DetailMovieView detailMovieView;
    private DetailMovie detailMovie;
    private boolean isFavoriteMovie;

    @Override
    public void onAttach(DetailMovieView mvpView) {
        detailMovieView = mvpView;
    }

    @Override
    public void onDetach() {

    }

    void onLoadData(final Context context, long idMovie, final DataManager dataManager) {
        detailMovie = new DetailMovie();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        MovieDbApiService movieDbApiService = retrofit.create(MovieDbApiService.class);
        movieDbApiService
                .getDetailMovie(
                        String.valueOf(idMovie),
                        BuildConfig.API_KEY,
                        BuildConfig.LANGUAGE
                )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<DetailMovie>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        /** nothing to do in here */
                    }

                    @Override
                    public void onNext(@NonNull DetailMovie detailMovie) {
                        DetailMoviePresenter.this.detailMovie = detailMovie;
                        isFavoriteMovie = dataManager.isItemDataAlready(context,detailMovie.getId());
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        e.printStackTrace();
                        Log.d(TAG, "onLoadData onError: " + e.getMessage());
                        detailMovieView.loadDataFailed();
                    }

                    @Override
                    public void onComplete() {
                        detailMovieView.loadData(detailMovie, isFavoriteMovie);
                    }
                });

    }

    void onAddToFavoriteMovie(DetailMovie detailMovie, DataManager dataManager){
        try {
            dataManager.insertDataFavorite(detailMovie);
            detailMovieView.addToFavoriteMovie();
        }catch (Exception e){
            e.printStackTrace();
            detailMovieView.addToFavoriteMovieFailed(e.getMessage());
        }
    }

    void onDeleteFromFavoriteMovie(DetailMovie detailMovie, DataManager dataManager){
        Log.d(TAG, "onDeleteFromFavoriteMovie");
        try {
            dataManager.deleteDataFavorite(detailMovie.getId());
            detailMovieView.deleteFromFavoriteMovie();
        }catch (Exception e){
            e.printStackTrace();
            detailMovieView.deleteFromFavoriteMovieFailed(e.getMessage());
        }
    }
}



