package com.example.android.moviecatalogapp.feature.alarm_reminder.upcoming;

import android.content.Context;

import com.google.android.gms.gcm.GcmNetworkManager;
import com.google.android.gms.gcm.PeriodicTask;
import com.google.android.gms.gcm.Task;


public class SchedulerTask {

    private GcmNetworkManager mGcmNetworkManager;

    public SchedulerTask(Context context){
        mGcmNetworkManager = GcmNetworkManager.getInstance(context);
    }

    public void createPeriodicTask(){
        Task periodicTask = new PeriodicTask.Builder()
                .setService(UpcomingMoviesJobService.class)
                .setPeriod(60)
                .setFlex(10)
                .setTag("UpComingMovies")
                .setPersisted(true)
                .build();
        mGcmNetworkManager.schedule(periodicTask);
    }

    public void cancelPeriodicTask(){
        if (mGcmNetworkManager != null){
            mGcmNetworkManager.cancelTask("UpcomingMovies",UpcomingMoviesJobService.class);
        }
    }
}
