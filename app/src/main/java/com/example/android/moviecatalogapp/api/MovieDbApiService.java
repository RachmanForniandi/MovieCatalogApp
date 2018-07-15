package com.example.android.moviecatalogapp.api;

import com.example.android.moviecatalogapp.model.movie.detail.DetailMovie;
import com.example.android.moviecatalogapp.model.movie.nowplaying.NowPlaying;
import com.example.android.moviecatalogapp.model.movie.search.SearchMovie;
import com.example.android.moviecatalogapp.model.movie.upcoming.UpcomingMovie;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Lenovo on 9/18/2017.
 */


public interface MovieDbApiService {
    @GET("search/movie")
    Observable<SearchMovie> searchMovie(
           @Query("api_key")String apiKey,
           @Query("language") String language,
           @Query("query") String query
    );

    @GET("movie/{MovieId}")
    Observable<DetailMovie> getDetailMovie(
            @Path("MovieId") String movieId,
            @Query("api_key") String apiKey,
            @Query("language") String language
    );


    @GET("movie/upcoming")
    Observable<UpcomingMovie> getUpcomingMovie(
            @Query("api_key") String apiKey,
            @Query("language") String language
    );

    @GET("movie/now_playing")
    Observable<NowPlaying> getNowPlayingMovie(
            @Query("api_key") String apiKey,
            @Query("language") String language
    );
}
