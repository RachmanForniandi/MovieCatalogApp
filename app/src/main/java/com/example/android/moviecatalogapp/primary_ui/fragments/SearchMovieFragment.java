package com.example.android.moviecatalogapp.primary_ui.fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android.moviecatalogapp.model.movie.search.SearchMovie;
import com.example.android.moviecatalogapp.primary_ui.fragments.adapter.AdapterSearchMovie;
import com.example.android.moviecatalogapp.production.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class SearchMovieFragment extends Fragment implements SearchMovie{

    private final String TAG = getClass().getSimpleName();
    private SearchMoviePresenter searchMoviePresenter;

    @BindView(R.id.et_keyword_search_movie_fragment)
    EditText eTKeywordSearchMovieFragment;
    @BindView(R.id.rcView_search_movie_fragment)
    RecyclerView rcViewSearchMovieFragment;

    private ProgressDialog progressDialog;
    private View view;
    private Context context;

    public SearchMovieFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search_movie, container, false);
        ButterKnife.bind(this, view);
        initPresenter();
        onAttachView();
        doLoadData();
        return view;
    }

    private void initPresenter() {
        searchMoviePresenter = new SearchMoviePresenter();
    }

    public void doLoadData() {
        if (context != null) {
            context = getActivity();
        }
        rcViewSearchMovieFragment.setLayoutManager(new LinearLayoutManager(context));
        rcViewSearchMovieFragment.addItemDecoration(
                new DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        );
    }
    @OnClick(R.id.btn_search_movie_fragment)
    public void onClick(){
        String keyword = eTKeywordSearchMovieFragment.getText().toString().trim();
        if (keyword.isEmpty()){
            Toast.makeText(
                    context,
                    getString(R.string.keyword_validation_message),
                    Toast.LENGTH_SHORT
            ).show();
        }else {
            initProgressDialog();
            searchMoviePresenter.onSearchMovie(context, keyword);
        }
    }



    @Override
    public void onAttachView() {
        searchMoviePresenter.onAttach(this);
    }

    @Override
    public void onDetachView() {
        searchMoviePresenter.onDetach();
    }

    private void initProgressDialog() {
        if (progressDialog == null){
            progressDialog = new ProgressDialog(context);
        }
        progressDialog.setMessage("Loading...Please wait");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    @Override
    public void searchMovie(AdapterSearchMovie adapterSearchMovie){
        dismissProgressDialog();
        rcViewSearchMovieFragment.setAdapter(adapterSearchMovie);
    }

    private void dismissProgressDialog() {
        progressDialog.dismiss();
    }

    @Override
    public void searchMovieFailed(){
        dismissProgressDialog();
        Toast.makeText(getContext(), "Search Movie Failed", Toast.LENGTH_SHORT).show();
    }
}
