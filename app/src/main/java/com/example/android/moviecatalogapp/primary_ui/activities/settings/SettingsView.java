package com.example.android.moviecatalogapp.primary_ui.activities.settings;

import com.example.android.moviecatalogapp.primary_ui.base.MvpView;


interface SettingsView extends MvpView {

    void loadData(boolean isDailyReminderNotificationActive,
                  boolean isUpcomingReminderNotificationActive);


    void updateData();
}
