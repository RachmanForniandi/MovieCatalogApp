package com.example.android.moviecatalogapp.feature.alarm_reminder.upcoming;

import android.content.Context;

import com.google.android.gms.gcm.GcmNetworkManager;
import com.google.android.gms.gcm.PeriodicTask;
import com.google.android.gms.gcm.Task;

/**
 * Created by Lenovo on 9/21/2017.
 */

public class SchedulerTask {

    private GcmNetworkManager mGcmNetworkManager;

    public SchedulerTask(Context context){
        mGcmNetworkManager = GcmNetworkManager.getInstance(context);
    }
    public void createPeriodicTask(){
        Task periodicTask = new PeriodicTask.Builder()
                .setService(UpComingMoviesJobService.class)
                .setPeriod(60)
                .setFlex(10)
                .setTag("UpComingMovies")
                .setPersisted(true)
                .build();
        mGcmNetworkManager.schedule(periodicTask);
    }
    public void cancelPeriodicTask(){
        if (mGcmNetworkManager != null){
            mGcmNetworkManager.cancelTask("UpComingMovies",UpComingMoviesJobService.class);
        }
    }
}
