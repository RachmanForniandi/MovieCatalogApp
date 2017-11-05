package com.example.android.moviecatalogapp.primary_ui.fragments.favorite;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.android.moviecatalogapp.R;
import com.example.android.moviecatalogapp.TheApp;
import com.example.android.moviecatalogapp.data.manager.DataManager;
import com.example.android.moviecatalogapp.dm.component.DaggerFragmentComponent;
import com.example.android.moviecatalogapp.dm.component.FragmentComponent;
import com.example.android.moviecatalogapp.dm.module.FragmentModule;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class FavoriteMovieFragment extends Fragment implements FavoriteMovieView{

    private final String TAG = getClass().getSimpleName();
    private FavoriteMoviePresenter favoriteMoviePresenter;

    @BindView(R.id.pb_loading_fragment_favorite_movie)
    ProgressBar progressBarLoadingFragmentFavoriteMovie;

    @BindView(R.id.rc_view_data_fragment_favorite_movie)
    RecyclerView recyclerViewDataFragmentFavoriteMovie;

    @Inject
    DataManager dataManager;
    FragmentComponent fragmentComponent;

    public FragmentComponent getFragmentComponent(){
        if (fragmentComponent == null){
            fragmentComponent = DaggerFragmentComponent
                    .builder()
                    .fragmentModule(new FragmentModule(this))
                    .theAppComponent(TheApp.get(getContext()).getAppComponent())
                    .build();
        }
        return fragmentComponent;
    }
    public FavoriteMovieFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View viewRoot = inflater.inflate(R.layout.fragment_favorite_movie, container, false);
        ButterKnife.bind(this, viewRoot);
        getFragmentComponent().inject(this);
        initPresenter();
        onAttachView();
        doLoadData();
        return viewRoot;
    }

    private void doLoadData() {
        favoriteMoviePresenter.onLoadData();
    }

    private void initPresenter() {
        favoriteMoviePresenter = new FavoriteMoviePresenter();
    }

    @Override
    public void onAttachView() {
        favoriteMoviePresenter.onAttach(this);
    }

    @Override
    public void onDetachView() {
        favoriteMoviePresenter.onDetach();
    }
}
