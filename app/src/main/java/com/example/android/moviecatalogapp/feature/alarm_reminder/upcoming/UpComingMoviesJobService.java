package com.example.android.moviecatalogapp.feature.alarm_reminder.upcoming;

import com.google.android.gms.gcm.GcmNetworkManager;
import com.google.android.gms.gcm.GcmTaskService;
import com.google.android.gms.gcm.TaskParams;

/**
 * Created by Lenovo on 9/21/2017.
 */

public class UpComingMoviesJobService extends GcmTaskService{
    private final String TAG = getClass().getSimpleName();
    private int notifId;

    @Override
    public int onRunTask(TaskParams taskParams){
        int result = 0;
        if(taskParams.getTag().equalsIgnoreCase("UpComingMovies")){
            getUpComingMovies();
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

    private void getUpComingMovies() {
    }


}
