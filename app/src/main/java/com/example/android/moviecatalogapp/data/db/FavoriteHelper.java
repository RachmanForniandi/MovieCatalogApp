package com.example.android.moviecatalogapp.data.db;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.android.moviecatalogapp.model.movie.detail.DetailMovie;

import java.util.List;

import static com.example.android.moviecatalogapp.data.db.DatabaseContract.*;
/**
 * Created by Lenovo on 11/6/2017.
 */

public class FavoriteHelper {

    private final String TAG = getClass().getSimpleName();
    private static final String FAVORITE_TABLE_NAME = TABLE_FAVORITE;
    private Context context;
    private DatabaseHelper databaseHelper;
    SQLiteDatabase sqLiteDatabase;

    public FavoriteHelper(Context context){
        this.context = context;
    }

    public FavoriteHelper open() throws Exception{
        databaseHelper = new DatabaseHelper(context);
        sqLiteDatabase = databaseHelper.getWritableDatabase();
        return this;
    }

    public SQLiteDatabase getWritableDatabase(){
        return databaseHelper.getWritableDatabase();
    }

    public Long insertDataFavorite(DetailMovie detailMovie)throws Exception{
        return databaseHelper.insertDataFavorite(context,detailMovie);
    }

    public int deleteDataFavorite(long idMovie) throws Exception{
        return databaseHelper.deleteDataFavorite(context, idMovie);
    }

    public int getSizeItemDataFavorite(){
        return databaseHelper.itemCountDataFavorite(context);
    }

    public boolean isItemDataAlready(long idMovie){
        return databaseHelper.itemDataAlreadyAdded(context, idMovie);
    }

    public List<DetailMovie>getAll(Context context){
        return databaseHelper.getAll(context);
    }

}
