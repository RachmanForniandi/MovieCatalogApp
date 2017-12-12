package com.example.android.moviecatalogapp.primary_ui.fragments.nowplaying;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.android.moviecatalogapp.R;
import com.example.android.moviecatalogapp.model.movie.nowplaying.ResultNowPlaying;
import com.example.android.moviecatalogapp.primary_ui.activities.details.DetailMovieActivity;
import com.example.android.moviecatalogapp.primary_ui.fragments.nowplaying.adapter.AdapterNowPlayingMovie;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class NowPlayingFragment extends Fragment implements NowPlayingView{

    private final String TAG = getClass().getSimpleName();
    private NowPlayingPresenter nowPlayingPresenter;

    @BindView(R.id.progressbar_loading_now_playing)
    ProgressBar progressBarLoadingFragmentNowPlaying;

    @BindView(R.id.rcView_data_now_playing_fragment)
    RecyclerView recyclerViewDataFragmentNowPlaying;


    public NowPlayingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View viewRoot = inflater.inflate(R.layout.fragment_now_playing, container, false);
        ButterKnife.bind(this, viewRoot);
        initPresenter();
        onAttachView();
        return viewRoot;
    }

    @Override
    public void onResume(){
        doLoadData();
        super.onResume();
    }

    private void initPresenter(){
        nowPlayingPresenter = new NowPlayingPresenter();
    }

    @Override
    public void onAttachView() {
        nowPlayingPresenter.onAttach(this);
    }

    @Override
    public void onDetachView() {
        nowPlayingPresenter.onDetach();
    }

    private void doLoadData(){
        progressBarLoadingFragmentNowPlaying.setVisibility(View.VISIBLE);
        recyclerViewDataFragmentNowPlaying.setVisibility(View.GONE);
        nowPlayingPresenter.onLoadData(getContext());
    }

    @Override
    public void itemClickDetail(ResultNowPlaying resultNowPlaying) {
        Intent intentDetailMovie = new Intent(getContext(), DetailMovieActivity.class);
        intentDetailMovie.putExtra("idMovie", resultNowPlaying.getId());
        startActivity(intentDetailMovie);
    }

    @Override
    public void itemClickShare(ResultNowPlaying resultNowPlaying) {
        Intent intentSharingMovie = new Intent(Intent.ACTION_SEND);
        intentSharingMovie.setType("text/plain");
        String bodyMessage = "Now Playing in Cinemas: " + resultNowPlaying.getTitle();
        intentSharingMovie.putExtra(Intent.EXTRA_SUBJECT, "NowPlaying Movie");
        intentSharingMovie.putExtra(Intent.EXTRA_TEXT, bodyMessage);
        startActivity(intentSharingMovie);
    }


    @Override
    public void loadData(AdapterNowPlayingMovie adapterNowPlayingMovie) {
        progressBarLoadingFragmentNowPlaying.setVisibility(View.GONE);
        recyclerViewDataFragmentNowPlaying.setVisibility(View.VISIBLE);

        recyclerViewDataFragmentNowPlaying.setLayoutManager(new LinearLayoutManager(getContext()));
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL);
        recyclerViewDataFragmentNowPlaying.addItemDecoration(dividerItemDecoration);
        recyclerViewDataFragmentNowPlaying.setAdapter(adapterNowPlayingMovie);
    }

    @Override
    public void loadDataFailed(String message) {
        progressBarLoadingFragmentNowPlaying.setVisibility(View.GONE);
        recyclerViewDataFragmentNowPlaying.setVisibility(View.VISIBLE);
        Toast.makeText(
             getContext(),message,
                Toast.LENGTH_LONG
        ).show();
    }

}
