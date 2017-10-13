package com.example.android.moviecatalogapp.primary_ui.activities.details;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.android.moviecatalogapp.BuildConfig;
import com.example.android.moviecatalogapp.R;
import com.example.android.moviecatalogapp.model.movie.detail.DetailMovie;


public class DetailMovieActivity extends AppCompatActivity implements DetailMovieView{

    private final String TAG = getClass().getSimpleName();
    private DetailMoviePresenter detailMoviePresenter;

    private ImageView imgViewPosterDetailMovie;
    private TextView tViewTitleDetailMovie;
    private TextView tVOverviewDetailMovie;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);
        initPresenter();
        onAttachView();
        initViews();
        onLoadData();
    }

    private void initViews() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        imgViewPosterDetailMovie = (ImageView)findViewById(R.id.img_view_poster_item_detail_movie);
        tViewTitleDetailMovie = (TextView)findViewById(R.id.tv_title_item_detail_movie);
        tVOverviewDetailMovie = (TextView)findViewById(R.id.tv_overview_detail_movie);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case android.R.id.home:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void onLoadData() {
        Bundle bundle = getIntent().getExtras();
        long idMovie = bundle.getLong("idMovie");

        if (progressDialog == null){
            progressDialog = new ProgressDialog(this);
        }
        progressDialog.setMessage("Loading... Please Wait");
        progressDialog.setCancelable(false);
        progressDialog.show();
        detailMoviePresenter.onLoadData(idMovie);
    }

    private void initPresenter() {
        detailMoviePresenter = new DetailMoviePresenter();
    }

    @Override
    public void onAttachView(){
        detailMoviePresenter.onAttach(this);
    }

    @Override
    public void onDetachView(){
        detailMoviePresenter.onDetach();
    }

    @Override
    public void loadData(DetailMovie detailMovie){
        progressDialog.dismiss();
        tViewTitleDetailMovie.setText(detailMovie.getOriginalTitle());
        tVOverviewDetailMovie.setText(detailMovie.getOverview());
        Glide.with(this)
                .load(BuildConfig.BASE_URL_IMAGE + "" +detailMovie.getPosterPath())
                .placeholder(R.drawable.ic_image_black_24dp)
                .error(R.drawable.ic_broken_image_black_24dp)
                .into(imgViewPosterDetailMovie);
    }

    @Override
    public void loadDataFailed(){
        progressDialog.dismiss();
        Toast.makeText(this, "Load data failed", Toast.LENGTH_LONG)
                .show();
    }


}
