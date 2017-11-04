package com.example.android.moviecatalogapp.data.manager;

import android.content.Context;

import com.example.android.moviecatalogapp.data.db.DatabaseHelper;
import com.example.android.moviecatalogapp.dm.TheApplicationContext;
import com.example.android.moviecatalogapp.model.movie.nowplaying.ResultNowPlaying;
import com.example.android.moviecatalogapp.model.movie.upcoming.ResultUpcomingMovie;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by Lenovo on 11/3/2017.
 */
@Singleton
public class DataManager {

    private Context context;
    private DatabaseHelper databaseHelper;

    @Inject
    public DataManager(@TheApplicationContext Context context,
                        DatabaseHelper databaseHelper){
        this.context = context;
        this.databaseHelper = databaseHelper;
    }

    public Long insertDataFavorite(ResultNowPlaying resultNowPlaying)throws Exception{
        return databaseHelper.insertDataFavorite(resultNowPlaying);
    }

    public Long insertDataFavorite(ResultUpcomingMovie resultUpcomingMovie)throws Exception{
        return databaseHelper.insertDataFavorite(resultUpcomingMovie);
    }

    public int deleteDataFavorite(long idMovie)throws Exception{
        return databaseHelper.deleteDataFavorite(idMovie);
    }

    public int getSizeItemDataFavorite(){
        return databaseHelper.itemCountDataFavorite();
    }
}
