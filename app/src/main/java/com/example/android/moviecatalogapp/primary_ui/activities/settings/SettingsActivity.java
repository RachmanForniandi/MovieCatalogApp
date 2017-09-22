package com.example.android.moviecatalogapp.primary_ui.activities.settings;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SwitchCompat;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CompoundButton;

import com.example.android.moviecatalogapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class SettingsActivity extends AppCompatActivity implements SettingsView, CompoundButton.OnCheckedChangeListener{

    private final String TAG = getClass().getSimpleName();
    private SettingsPresenter settingsPresenter;

    @BindView(R.id.sw_compat_daily_reminder_notification)
    SwitchCompat switchCompatDailyReminderNotificationSettings;

    @BindView(R.id.sw_compat_upcoming_reminder_notification)
    SwitchCompat switchCompatUpcomingReminderNotificationSettings;

    private boolean isDailyReminderNotificationActive;
    private boolean isUpcomingReminderNotificationActive;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        ButterKnife.bind(this);
        initPresenter();
        onAttachView();
        doLoadData();
        initToolbar();
        initListeners();
    }

    private void initPresenter() {
        settingsPresenter = new SettingsPresenter();
    }

    private void initToolbar() {
        getSupportActionBar().setTitle(getString(R.string.settings));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void initListeners() {
        switchCompatDailyReminderNotificationSettings.setOnCheckedChangeListener(this);
        switchCompatUpcomingReminderNotificationSettings.setOnCheckedChangeListener(this);
    }

    private void doLoadData() {
        settingsPresenter.onLoadData(this);
    }

    @Override
    public void onAttachView() {
        settingsPresenter.onAttach(this);
    }

    @Override
    public void onDetachView() {
        settingsPresenter.onDetach();
    }

    @OnClick(R.id.relative_layout_container_settings_locale)
    public void onClick(){
        Intent intentSettingsLocale =new Intent(Settings.ACTION_LOCALE_SETTINGS);
        startActivity(intentSettingsLocale);
    }

    @Override
    public void loadData(boolean isDailyReminderNotificationActive, boolean isUpcomingReminderNotificationActive) {
        this.isDailyReminderNotificationActive = isDailyReminderNotificationActive;
        this.isUpcomingReminderNotificationActive = isUpcomingReminderNotificationActive;

        switchCompatDailyReminderNotificationSettings.setChecked(this
                .isDailyReminderNotificationActive);
        switchCompatUpcomingReminderNotificationSettings.setChecked(this
                .isUpcomingReminderNotificationActive);
    }


    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        switch (compoundButton.getId()){
            case R.id.sw_compat_daily_reminder_notification:
                isDailyReminderNotificationActive = switchCompatDailyReminderNotificationSettings
                        .isChecked();
                settingsPresenter.onUpdateData(
                        isDailyReminderNotificationActive,
                        isUpcomingReminderNotificationActive
                );
                break;
            case R.id.sw_compat_upcoming_reminder_notification:
                isUpcomingReminderNotificationActive = switchCompatUpcomingReminderNotificationSettings
                        .isChecked();
                settingsPresenter.onUpdateData(
                        isDailyReminderNotificationActive,
                        isUpcomingReminderNotificationActive
                );
                break;
        }
    }
    @Override
    public void updateData() {
        /**not yet*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}
