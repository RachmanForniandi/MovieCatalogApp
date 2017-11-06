package com.example.android.moviecatalogapp.data.manager;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.android.moviecatalogapp.data.db.DatabaseHelper;
import com.example.android.moviecatalogapp.dm.TheApplicationContext;
import com.example.android.moviecatalogapp.model.movie.detail.DetailMovie;

import java.util.List;

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

    public SQLiteDatabase getWritableDatabase(){
        return databaseHelper.getWritableDatabase();
    }


    public Long insertDataFavorite(DetailMovie detailMovie)throws Exception{
        return databaseHelper.insertDataFavorite(detailMovie);
    }

    public int deleteDataFavorite(long idMovie)throws Exception{
        return databaseHelper.deleteDataFavorite(idMovie);
    }

    public int getSizeItemDataFavorite(){
        return databaseHelper.itemCountDataFavorite();
    }

    public boolean isItemDataAlready(Context context, long idMovie){
        return databaseHelper.itemDataAlreadyAdded(idMovie);
    }

    public List<DetailMovie> getAll(){
        return databaseHelper.getAll();
    }

}
