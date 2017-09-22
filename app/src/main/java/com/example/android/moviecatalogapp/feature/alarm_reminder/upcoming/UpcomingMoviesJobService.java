package com.example.android.moviecatalogapp.feature.alarm_reminder.upcoming;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.example.android.moviecatalogapp.BuildConfig;
import com.example.android.moviecatalogapp.R;
import com.example.android.moviecatalogapp.api.MovieDbApiService;
import com.example.android.moviecatalogapp.model.movie.upcoming.ResultUpcomingMovie;
import com.example.android.moviecatalogapp.model.movie.upcoming.UpcomingMovie;
import com.example.android.moviecatalogapp.primary_ui.activities.details.DetailMovieActivity;
import com.google.android.gms.gcm.GcmNetworkManager;
import com.google.android.gms.gcm.GcmTaskService;
import com.google.android.gms.gcm.TaskParams;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class UpcomingMoviesJobService extends GcmTaskService{

    private final String TAG = getClass().getSimpleName();
    private int notifId;

    @Override
    public int onRunTask(TaskParams taskParams){
        int result = 0;
        if(taskParams.getTag().equalsIgnoreCase("UpComingMovies")){
            getUpcomingMovies();
            result = GcmNetworkManager.RESULT_SUCCESS;
        }
        return result;
    }

    @Override
    public void onInitializeTasks(){
        super.onInitializeTasks();
        SchedulerTask schedulerTask = new SchedulerTask(this);
        schedulerTask.createPeriodicTask();
    }

    private void getUpcomingMovies(){
        notifId = 200;
        Log.d(TAG, "getUpcomingMovies");
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        MovieDbApiService movieDbApiService = retrofit.create(MovieDbApiService.class);
        movieDbApiService.getUpcomingMovie(BuildConfig.API_KEY, BuildConfig.LANGUAGE)
                .map(new Function<UpcomingMovie, List<ResultUpcomingMovie>>(){
                    @Override
                    public List<ResultUpcomingMovie> apply(@NonNull UpcomingMovie upcomingMovie)throws Exception{
                        return upcomingMovie.getResults();
                    }
                })
                .flatMap(new Function<List<ResultUpcomingMovie>, Observable<ResultUpcomingMovie>>() {
                    @Override
                    public Observable<ResultUpcomingMovie> apply(@NonNull List<ResultUpcomingMovie> resultUpcomingMovies) throws Exception {
                        return Observable.fromIterable(resultUpcomingMovies);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResultUpcomingMovie>(){
                    @Override
                    public void onSubscribe(@NonNull Disposable d){
                        /* not yet */
                    }

                    @Override
                    public void onNext(@NonNull ResultUpcomingMovie resultUpcomingMovie){
                        Log.d(TAG, "onNext");
                        long id = resultUpcomingMovie.getId();
                        String originalTitle = resultUpcomingMovie.getOriginalTitle();
                        showNotificationUpComingMovie(notifId, id, originalTitle);
                        notifId += 1;
                    }

                    @Override
                    public void onError(@NonNull Throwable e){
                        e.printStackTrace();
                        Log.d(TAG, "getUpComingMoviesJobService on Error: " + e.getMessage());
                    }

                    @Override
                    public void onComplete(){
                        Log.d(TAG, "getUpComingMoviesJobService onComplete");
                    }
                });
        }

    private void showNotificationUpComingMovie(int notifId, long id, String originalTitle) {
        Context context = getApplicationContext();
        NotificationManager notificationManagerCompat = (NotificationManager) context
                .getSystemService(context.NOTIFICATION_SERVICE);
        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        Intent intentDetailMovieActivity = new Intent(context, DetailMovieActivity.class);
        intentDetailMovieActivity.putExtra("idMovie", id);
        PendingIntent pendingIntentDetailMovieActivity = PendingIntent.getActivity(
                context,
                120,
                intentDetailMovieActivity,
                PendingIntent.FLAG_UPDATE_CURRENT
        );

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                .setContentTitle(originalTitle)
                .setSmallIcon(R.drawable.ic_access_alarm_black_24dp)
                .setContentText("Hari ini " + originalTitle + "release")
                .setContentIntent(pendingIntentDetailMovieActivity)
                .setColor(ContextCompat.getColor(context, android.R.color.white))
                .setVibrate(new long[]{900, 900, 900, 900})
                .setSound(alarmSound);
        notificationManagerCompat.notify(notifId, builder.build());
    }
}
