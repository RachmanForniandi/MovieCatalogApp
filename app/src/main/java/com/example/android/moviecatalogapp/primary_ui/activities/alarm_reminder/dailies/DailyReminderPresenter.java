package com.example.android.moviecatalogapp.primary_ui.activities.alarm_reminder.dailies;

import android.content.Context;

import com.example.android.moviecatalogapp.R;
import com.example.android.moviecatalogapp.feature.alarm_reminder.daily.DailyAlarmPreference;
import com.example.android.moviecatalogapp.feature.alarm_reminder.daily.DailyAlarmReceiver;
import com.example.android.moviecatalogapp.primary_ui.base.MvpPresenter;

/**
 * Created by Lenovo on 9/24/2017.
 */

public class DailyReminderPresenter implements MvpPresenter<DailyReminderView>{

    private final String TAG = getClass().getSimpleName();
    private DailyReminderView dailyReminderView;

    @Override
    public void onAttach(DailyReminderView mvpView){
        dailyReminderView = mvpView;
    }

    @Override
    public void onDetach(){
        dailyReminderView = null;
    }

    void onSave(Context context, String strTime){
        DailyAlarmPreference dailyAlarmPreference = new DailyAlarmPreference(context);
        dailyAlarmPreference.setRepeatingTime(strTime);
        dailyAlarmPreference.setRepeatingMessage(context.getString(R.string.message_daily_reminder));

        DailyAlarmReceiver dailyAlarmReceiver = new DailyAlarmReceiver();
        dailyAlarmReceiver.setRepeatingAlarm(
                context,
                dailyAlarmPreference.getRepeatingTime(),
                dailyAlarmPreference.getRepeatingMessage(),
                true);
        dailyReminderView.save();
    }

    void onLoadData(Context context){
        DailyAlarmPreference dailyAlarmPreference = new DailyAlarmPreference(context);
        String time = dailyAlarmPreference.getRepeatingTime();
        dailyReminderView.loadData(time);
    }
}
