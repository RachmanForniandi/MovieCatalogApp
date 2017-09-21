package com.example.android.moviecatalogapp.primary_ui.activities.main;

import com.example.android.moviecatalogapp.primary_ui.activities.base.MvpPresenter;

/**
 * Created by Lenovo on 9/20/2017.
 */

public class MainPresenter implements MvpPresenter<MainView>{
    private final String TAG = getClass().getSimpleName();
    private MainView mainView;

    @Override
    public void onAttach(MainView mvpView){
        mainView = mvpView;
    }

    @Override
    public void onDetach(){
        mainView = null;
    }
}
