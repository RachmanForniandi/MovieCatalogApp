package com.example.android.moviecatalogapp.primary_ui.base;



public interface MvpPresenter<X extends MvpView> {

    void onAttach(X mvpView);

    void onDetach();
}
