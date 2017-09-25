package com.example.android.moviecatalogapp.primary_ui.activities.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.android.moviecatalogapp.R;
import com.example.android.moviecatalogapp.primary_ui.activities.alarm_reminder.dailies.DailyReminderActivity;
import com.example.android.moviecatalogapp.primary_ui.fragments.search.SearchMovieFragment;

public class MainActivity extends AppCompatActivity implements MainView{

    private final String TAG = getClass().getSimpleName();
    private MainPresenter mainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initPresenter();
        onAttachView();
        loadView();
        doLoadData();
    }

    private void doLoadData() {
        mainPresenter.onLoadData(this);
    }


    private void loadView() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fr_layout_container_content_main, new SearchMovieFragment())
                .commit();
    }

    private void initPresenter() {
        mainPresenter = new MainPresenter();
    }

    @Override
    public void onAttachView() {
        mainPresenter.onAttach(this);
    }

    @Override
    public void onDetachView(){
        mainPresenter.onDetach();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main_activity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item_daily_reminder_menu_activity:
            startActivity(new Intent(this, DailyReminderActivity.class));
            return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void loadData(){
        Log.d(TAG, "loadData Success");
    }
}




