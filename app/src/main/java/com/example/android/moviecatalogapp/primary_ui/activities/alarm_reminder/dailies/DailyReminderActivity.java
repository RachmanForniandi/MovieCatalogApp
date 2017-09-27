package com.example.android.moviecatalogapp.primary_ui.activities.alarm_reminder.dailies;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.android.moviecatalogapp.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class DailyReminderActivity extends AppCompatActivity implements DailyReminderView, View.OnClickListener {

    private final String TAG = getClass().getSimpleName();
    private DailyReminderPresenter dailyReminderPresenter;

    private TextView tvValueTimeDailyReminderActivity;
    private Button btnSetTimeDailyReminderActivity;

    private Calendar calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_reminder);
        initPresenter();
        onAttachView();
        initViews();
        initListeners();
        doLoadData();
    }

    private void doLoadData() {
        dailyReminderPresenter.onLoadData(this);
    }
    private void initListeners() {
        btnSetTimeDailyReminderActivity.setOnClickListener(this);
    }

    private void initViews() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        tvValueTimeDailyReminderActivity = (TextView)findViewById(R.id.tv_value_time_activity_daily_reminder);
        btnSetTimeDailyReminderActivity = (Button)findViewById(R.id.btn_set_time_activity_daily_reminder);
    }

    private void initPresenter() {
        dailyReminderPresenter = new DailyReminderPresenter();
    }

    @Override
    public void onAttachView() {
        dailyReminderPresenter.onAttach(this);
    }

    @Override
    public void onDetachView() {
        dailyReminderPresenter.onDetach();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_daily_reminder, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                return true;
            case R.id.menu_item_save_menu_daily_reminder:
                String strTime = tvValueTimeDailyReminderActivity.getText().toString();
                dailyReminderPresenter.onSave(this, strTime);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.btn_set_time_activity_daily_reminder:
                calendar = Calendar.getInstance();
                TimePickerDialog timePickerDialog = new TimePickerDialog(
                        DailyReminderActivity.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hour, int minute) {
                                calendar.set(Calendar.HOUR_OF_DAY, hour);
                                calendar.set(Calendar.MINUTE, minute);
                                Date dateSelected = calendar.getTime();
                                tvValueTimeDailyReminderActivity.setText(
                                        new SimpleDateFormat("HH:mm", Locale.US)
                                        .format(dateSelected)
                                );
                            }
                        },
                        calendar.get(Calendar.HOUR_OF_DAY),
                        calendar.get(Calendar.MINUTE),
                        true
                );
                timePickerDialog.show();
                break;
        }
    }

    @Override
    public void save() {
        /*not yet*/
    }

    @Override
    public void loadData(String time) {
        tvValueTimeDailyReminderActivity.setText(time);

    }
}
