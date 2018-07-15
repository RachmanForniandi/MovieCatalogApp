package com.example.android.moviecatalogapp.widgets;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.Target;
import com.example.android.moviecatalogapp.BuildConfig;
import com.example.android.moviecatalogapp.R;
import com.example.android.moviecatalogapp.data.db.DatabaseHelper;
import com.example.android.moviecatalogapp.model.movie.detail.DetailMovie;

import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by Lenovo on 11/21/2017.
 */

public class StackRemoteViewsFactory implements RemoteViewsService.RemoteViewsFactory {

    private final String TAG = getClass().getSimpleName();
    private Context context;
    private int mAppWidgetId;
    private List<DetailMovie> listDetailMovie;
    private DatabaseHelper databaseHelper;

    public StackRemoteViewsFactory(Context context, Intent intent) {
        this.context = context;
        mAppWidgetId = intent.getIntExtra(
                AppWidgetManager.EXTRA_APPWIDGET_ID,
                AppWidgetManager.INVALID_APPWIDGET_ID
        );
    }

    @Override
    public void onCreate(){
        databaseHelper = new DatabaseHelper(context);
    }

    @Override
    public void onDataSetChanged(){
        listDetailMovie = databaseHelper.getAll();
    }

    @Override
    public void onDestroy(){

    }

    @Override
    public int getCount(){
        return listDetailMovie.size();
    }

    @Override
    public RemoteViews getViewAt(int position){
        Bitmap bitmap = null;
        String posterPath = listDetailMovie.get(position).getPosterPath();
        try {
            bitmap = Glide.with(context)
                    .load(BuildConfig.BASE_URL_IMAGE + posterPath)
                    .asBitmap()
                    .error(R.drawable.ic_broken_image_black_24dp)
                    .placeholder(R.drawable.ic_image_black_24dp)
                    .into(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL )
                    .get();
        }catch (ExecutionException | InterruptedException e){
            e.printStackTrace();
        }
        RemoteViews remoteViewsItem = new RemoteViews(
                context.getPackageName(),
                R.layout.favorite_movie_item_widget
        );
        remoteViewsItem.setImageViewBitmap(
                R.id.img_view_poster_item_favorite_movie_item_widget,
                bitmap
        );
        remoteViewsItem.setTextViewText(
                R.id.tv_date_favorite_movie_item_widget,
                listDetailMovie.get(position).getReleaseDate()
        );

        Bundle bundle = new Bundle();
        bundle.putLong(FavoriteMovieWidget.EXTRA_ITEM, listDetailMovie.get(position).getId());
        Intent fillInIntent = new Intent();
        fillInIntent.putExtras(bundle);
        remoteViewsItem.setOnClickFillInIntent(
                R.id.img_view_poster_item_favorite_movie_item_widget,
                fillInIntent
        );
        return remoteViewsItem;
    }

    @Override
    public RemoteViews getLoadingView(){
        return null;
    }

    @Override
    public int getViewTypeCount(){
        return 1;
    }

    @Override
    public long getItemId(int i){
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

}
