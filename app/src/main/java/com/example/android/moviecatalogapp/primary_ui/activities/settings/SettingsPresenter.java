package com.example.android.moviecatalogapp.primary_ui.activities.settings;

import android.content.Context;

import com.example.android.moviecatalogapp.feature.alarm_reminder.daily.DailyAlarmPreference;
import com.example.android.moviecatalogapp.feature.alarm_reminder.daily.DailyAlarmReceiver;
import com.example.android.moviecatalogapp.feature.alarm_reminder.upcoming.SchedulerTask;
import com.example.android.moviecatalogapp.feature.setting.SettingsPreference;
import com.example.android.moviecatalogapp.primary_ui.base.MvpPresenter;

/**
 * Created by Lenovo on 9/22/2017.
 */

public class SettingsPresenter implements MvpPresenter<SettingsView>{

    private String TAG = getClass().getSimpleName();
    private SettingsView settingsView;
    private SettingsPreference settingsPreference;
    private Context context;

    @Override
    public void onAttach(SettingsView mvpView){
        settingsView = mvpView;
    }

    @Override
    public void onDetach(){
        settingsView = null;
    }

    void onLoadData(Context context){
        this.context = context;
        boolean isDailyReminderNotificationActive;
        boolean isUpcomingReminderNotificationActive;

        settingsPreference = new SettingsPreference(context);
        isDailyReminderNotificationActive = settingsPreference.getDailyReminderActive();
        isUpcomingReminderNotificationActive = settingsPreference.getUpcomingReminderActive();

        settingsView.loadData(isDailyReminderNotificationActive,
                isUpcomingReminderNotificationActive);
    }

    void onUpdateData(boolean isDailyReminderNotificationActive,
                      boolean isUpcomingReminderNotificationActive){
        settingsPreference.setDailyReminderActive(isDailyReminderNotificationActive);
        settingsPreference.setUpcomingReminderActive(isUpcomingReminderNotificationActive);

        /** utk Daily Reminder Notification */
        DailyAlarmPreference dailyAlarmPreference = new DailyAlarmPreference(context);
        DailyAlarmReceiver dailyAlarmReceiver = new DailyAlarmReceiver();
        if (isDailyReminderNotificationActive){
            String time = dailyAlarmPreference.getRepeatingTime();
            String message = dailyAlarmPreference.getRepeatingMessage();
            dailyAlarmReceiver.setRepeatingAlarm(
                    context,
                    time,
                    message,
                    true
            );
        }else {
            dailyAlarmReceiver.cancelAlarm(context);
        }

        /** utk Upcoming Reminder Notification */
        SchedulerTask schedulerTask = new SchedulerTask(context);
        if (isUpcomingReminderNotificationActive){
            schedulerTask.cancelPeriodicTask();
            schedulerTask.cancelPeriodicTask();
        }else {
            schedulerTask.cancelPeriodicTask();
        }
        settingsView.updateData();
    }
}
