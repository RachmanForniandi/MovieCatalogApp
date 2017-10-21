package com.example.android.moviecatalogapp.primary_ui.fragments.search;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android.moviecatalogapp.R;
import com.example.android.moviecatalogapp.primary_ui.fragments.search.adapter.AdapterSearchMovie;


public class SearchMovieFragment extends Fragment implements SearchMovieView, View.OnClickListener{

    private final String TAG = getClass().getSimpleName();
    private SearchMoviePresenter searchMoviePresenter;
    private static String STATE_RESULT = "state_result";
    private String mChecked;

    private EditText etKeywordSearchMovieFragment;
    private Button btnSearchMovieFragment;
    private RecyclerView recyclerViewSearchMovieFragment;
    private ProgressDialog progressDialog;
    private View view;

    public SearchMovieFragment() {
        // not yet
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search_movie, container, false);
        initViews(view);
        initListener();
        initPresenter();

        if (savedInstanceState != null){
            mChecked = savedInstanceState.getString(STATE_RESULT);
        }
        return view;
    }
    @Override
    public void onResume(){
        super.onResume();
        onAttachView();
    }

    private void initPresenter() {
        searchMoviePresenter = new SearchMoviePresenter();
    }

    private void initListener(){
        btnSearchMovieFragment.setOnClickListener(this);
    }


    @Override
    public void onAttachView() {
        searchMoviePresenter.onAttach(this);
    }

    @Override
    public void onDetachView() {
        searchMoviePresenter.onDetach();
    }

    public void initViews(View view) {
        etKeywordSearchMovieFragment = (EditText)view.findViewById(R.id.et_keyword_search_movie_fragment);
        btnSearchMovieFragment = (Button)view.findViewById(R.id.btn_search_movie_fragment);
        recyclerViewSearchMovieFragment =(RecyclerView)view.findViewById(R.id.rcView_search_movie_fragment);
        recyclerViewSearchMovieFragment.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewSearchMovieFragment.addItemDecoration(
                new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL)
        );
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_search_movie_fragment:
                String keyword = etKeywordSearchMovieFragment.getText().toString().trim();
                if (keyword.isEmpty()){
                    Toast.makeText(
                            getContext(),
                            getString(R.string.keyword_validation_message),
                            Toast.LENGTH_SHORT
                    ).show();
                }else {
                    initProgressDialog();
                    searchMoviePresenter.onSearchMovie(getContext(), keyword);
                }
                break;
        }
    }

    private void initProgressDialog() {
        if (progressDialog == null){
            progressDialog = new ProgressDialog(getContext());
        }
        progressDialog.setMessage("Loading...Please wait");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    @Override
    public void searchMovie(AdapterSearchMovie adapterSearchMovie){
        dismissProgressDialog();
        recyclerViewSearchMovieFragment.setAdapter(adapterSearchMovie);
    }

    private void dismissProgressDialog() {
        progressDialog.dismiss();
    }

    @Override
    public void searchMovieFailed(){
        dismissProgressDialog();
        Toast.makeText(getContext(), "Search Movie Failed", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClickItem(Intent intentDetailMovieActivity){
        startActivity(intentDetailMovieActivity);
    }

    @Override
    public void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putString(STATE_RESULT, mChecked);
    }
}
