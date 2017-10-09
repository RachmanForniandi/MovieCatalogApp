package com.example.android.moviecatalogapp.primary_ui.activities.main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.android.moviecatalogapp.R;
import com.example.android.moviecatalogapp.primary_ui.fragments.nowplaying.NowPlayingFragment;
import com.example.android.moviecatalogapp.primary_ui.fragments.upcoming.UpcomingMovieFragment;

public class MainActivity extends AppCompatActivity implements MainView, NavigationView.OnNavigationItemSelectedListener{

    private final String TAG = getClass().getSimpleName();
    private MainPresenter mainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initPresenter();
        onAttachView();
        initViews();
        /*loadView();
        doLoadData();*/
    }

    private void initViews(){
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar_app_main);
        setSupportActionBar(toolbar);

        DrawerLayout drwLayout = (DrawerLayout)findViewById(R.id.drawer_layout_activity_main);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this,drwLayout,toolbar,
                R.string.nav_draw_open,
                R.string.nav_draw_close
        );
        drwLayout.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView)findViewById(R.id.nv_activity_main);
        navigationView.setNavigationItemSelectedListener(this);

        TabLayout tabLayout = (TabLayout)findViewById(R.id.tab_layout_content_main);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fr_layout_content_main, new NowPlayingFragment())
                .commit();

        tabLayout.addTab(
                tabLayout.newTab().setText(getString(R.string.now_playing))
        );
        tabLayout.addTab(
                tabLayout.newTab().setText(getString(R.string.upcoming))
        );
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Fragment fragmentSelected = null;
                switch (tab.getPosition()){
                    case 0:
                        fragmentSelected = new NowPlayingFragment();
                        break;
                    case 1:
                        fragmentSelected = new UpcomingMovieFragment();
                        break;
                }
                if (fragmentSelected != null){
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fr_layout_content_main, fragmentSelected)
                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                            .commit();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                /** not yet */
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                /** not yet */
            }
        });

    }
    private void doLoadData() {
        mainPresenter.onLoadData(this);
    }


    private void loadView() {
        /*getSupportFragmentManager().beginTransaction()
                .replace(R.id.fr_layout_container_content_main, new SearchMovieFragment())
                .commit();*/
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
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        /*switch (item.getItemId()) {
            case R.id.menu_item_daily_reminder_menu_activity:
            startActivity(new Intent(this, DailyReminderActivity.class));
            return true;

            default:
                return super.onOptionsItemSelected(item);
        }*/
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void loadData(){
        Log.d(TAG, "loadData Success");
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        boolean selectedItem = false;
        switch(item.getItemId()){
            case R.id.nav_option_menu_now_playing_at_nav_draw:
                selectedItem = true;
                break;
            case R.id.nav_option_menu_upcoming_at_nav_draw:
                selectedItem = true;
                break;
        }

        DrawerLayout drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout_activity_main);
        drawerLayout.closeDrawer(GravityCompat.START);
        return selectedItem;
    }

    @Override
    public void onBackPressed(){
        DrawerLayout drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout_activity_main);
        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else {
            super.onBackPressed();
        }
    }
}




