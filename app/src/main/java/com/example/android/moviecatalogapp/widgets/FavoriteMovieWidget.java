package com.example.android.moviecatalogapp.widgets;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.widget.RemoteViews;

import com.example.android.moviecatalogapp.R;
import com.example.android.moviecatalogapp.primary_ui.activities.details.DetailMovieActivity;

import java.util.Arrays;


/**
 * Implementation of App Widget functionality.
 */
public class FavoriteMovieWidget extends AppWidgetProvider {
    private final String TAG = getClass().getSimpleName();
    public static final String TOAST_ACTION = "TOAST_ACTION";
    public static final String EXTRA_ITEM = "EXTRA_ITEM";

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        Log.d("FavoriteMovieWidget", "updateAppWidget");
        Intent intent = new Intent(context, StackWidgetService.class);
        intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
        intent.setData(Uri.parse(intent.toUri(Intent.URI_INTENT_SCHEME)));

        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.favorite_movie_widget);
        views.setRemoteAdapter(R.id.stack_view_movie, intent);
        views.setEmptyView(R.id.stack_view_movie, R.id.empty_txt_view_favorite_movie_widget);

        Intent toastIntent = new Intent(context, FavoriteMovieWidget.class);
        toastIntent.setAction(FavoriteMovieWidget.TOAST_ACTION);
        toastIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);

        intent.setData(Uri.parse(intent.toUri(Intent.URI_INTENT_SCHEME)));
        PendingIntent toastPendingIntent = PendingIntent.getBroadcast(
                context,
                0,
                toastIntent,
                PendingIntent.FLAG_UPDATE_CURRENT
        );
        views.setPendingIntentTemplate(
                R.id.stack_view_movie,
                toastPendingIntent
        );
        appWidgetManager.notifyAppWidgetViewDataChanged(
                appWidgetId,
                R.id.stack_view_movie
        );
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        Log.d(TAG, "appWidgetId: " + Arrays.toString(appWidgetIds));
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }


    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    @Override
    public void onReceive(Context context, Intent intent){
        Log.d(TAG, "onReceive");
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
        if (intent.getAction().equals(TOAST_ACTION)){
            int appWidgetId = intent.getIntExtra(
                    AppWidgetManager.EXTRA_APPWIDGET_ID,
                    AppWidgetManager.INVALID_APPWIDGET_ID
            );

            Intent detailMovieActivity = new Intent(context, DetailMovieActivity.class);
            long idMovie = intent.getLongExtra(EXTRA_ITEM, 0);
            detailMovieActivity.putExtra("idMovie", idMovie);
            context.startActivity(detailMovieActivity);

        }else if (intent.getAction().equals(AppWidgetManager.ACTION_APPWIDGET_UPDATE)){
            ComponentName thisAppWidget = new ComponentName(
                    context.getPackageName(),
                    FavoriteMovieWidget.class.getName()
            );
            int[] appWidgetIds = appWidgetManager.getAppWidgetIds(thisAppWidget);
            appWidgetManager.notifyAppWidgetViewDataChanged(
                    appWidgetIds,
                    R.id.stack_view_movie
            );
        }
        super.onReceive(context, intent);
    }
}

