package com.example.android.moviecatalogapp.primary_ui.fragments.favorite;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
import com.example.android.moviecatalogapp.model.movie.detail.DetailMovie;
import com.example.android.moviecatalogapp.primary_ui.activities.details.DetailMovieActivity;
import com.example.android.moviecatalogapp.primary_ui.fragments.favorite.AdapterFavoriteMovie.AdapterFavoriteMovie;

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
        progressBarLoadingFragmentFavoriteMovie.setVisibility(View.VISIBLE);
        recyclerViewDataFragmentFavoriteMovie.setVisibility(View.GONE);
        favoriteMoviePresenter.onLoadData(
                getContext(),
                dataManager
        );
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

    @Override
    public void loadData(AdapterFavoriteMovie adapterFavoriteMovie){
        progressBarLoadingFragmentFavoriteMovie.setVisibility(View.VISIBLE);
        recyclerViewDataFragmentFavoriteMovie.setVisibility(View.GONE);

        recyclerViewDataFragmentFavoriteMovie.setLayoutManager(
                new LinearLayoutManager(getContext())
        );
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(
                getContext(),
                DividerItemDecoration.VERTICAL
        );
        recyclerViewDataFragmentFavoriteMovie.addItemDecoration(dividerItemDecoration);
        recyclerViewDataFragmentFavoriteMovie.setAdapter(adapterFavoriteMovie);
    }

    @Override
    public void itemClickDetail(DetailMovie detailMovie){
        Intent intentDetailMovieActivity = new Intent(getContext(), DetailMovieActivity.class);
        Log.d(TAG, "idMovie" + detailMovie.getId());
        intentDetailMovieActivity.putExtra("idMovie", detailMovie.getTitle());
        startActivity(intentDetailMovieActivity);
    }

    @Override
    public void itemClickShare(DetailMovie detailMovie){
        Intent intentSharingMovie = new Intent(Intent.ACTION_SEND);
        intentSharingMovie.setType("text/plain");
        String bodyMessage = "Favorite Movie: " + detailMovie.getTitle();
        intentSharingMovie.putExtra(Intent.EXTRA_SUBJECT, "Favorite Movie");
        intentSharingMovie.putExtra(Intent.EXTRA_TEXT, bodyMessage);
        startActivity(intentSharingMovie);

    }
}
