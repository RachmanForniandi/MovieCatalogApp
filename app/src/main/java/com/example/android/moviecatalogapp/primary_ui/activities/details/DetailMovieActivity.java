package com.example.android.moviecatalogapp.primary_ui.activities.details;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.android.moviecatalogapp.BuildConfig;
import com.example.android.moviecatalogapp.R;
import com.example.android.moviecatalogapp.TheApp;
import com.example.android.moviecatalogapp.data.manager.DataManager;
import com.example.android.moviecatalogapp.dm.component.ActivityComponent;
import com.example.android.moviecatalogapp.dm.component.DaggerActivityComponent;
import com.example.android.moviecatalogapp.dm.module.ActivityModule;
import com.example.android.moviecatalogapp.model.movie.detail.DetailMovie;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class DetailMovieActivity extends AppCompatActivity implements DetailMovieView{

    private final String TAG = getClass().getSimpleName();
    private DetailMoviePresenter detailMoviePresenter;

    @BindView(R.id.img_view_poster_activity_detail_movie)
    ImageView imgViewPosterDetailMovie;

    @BindView(R.id.tv_title_item_detail_movie)
    TextView tViewTitleDetailMovie;

    @BindView(R.id.tv_overview_detail_movie)
    TextView tViewOverviewDetailMovie;

    @BindView(R.id.img_view_add_to_favorite_movie_activity_detail_movie)
    ImageView imgViewAddToFavoriteMovie;

    @BindView(R.id.tv_value_release_date_activity_detail_movie)
    TextView tViewValueReleaseDateMovie;

    private ProgressDialog progressDialog;

    @Inject
    DataManager dataManager;
    private ActivityComponent activityComponent;
    private DetailMovie detailMovie;
    private long idMovie;

    public ActivityComponent getActivityComponent(){
        if (activityComponent == null){
            activityComponent = DaggerActivityComponent
                    .builder()
                    .activityModule(new ActivityModule(this))
                    .theAppComponent(TheApp.get(this).getAppComponent())
                    .build();
        }
        return activityComponent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);
        ButterKnife.bind(this);
        getActivityComponent().inject(this);
        initPresenter();
        onAttachView();
        setToolbar();
        onLoadData();
    }

    private void setToolbar() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_detail_movie_activity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                return true;
            case R.id.menu_refresh_item_menu_detail_movie_activity:
                doRefreshData();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @OnClick({
            R.id.img_view_add_to_favorite_movie_activity_detail_movie
    })

    public void onClick(View view){
        switch (view.getId()){
            case R.id.img_view_add_to_favorite_movie_activity_detail_movie:
                if (imgViewAddToFavoriteMovie.getTag().equals("star border")) {
                    if (detailMovie == null) {
                        Toast.makeText(
                                this,
                                R.string.refresh_data,
                                Toast.LENGTH_LONG
                        ).show();
                    } else {
                        detailMoviePresenter.onAddToFavoriteMovie(detailMovie, dataManager);
                    }
                }else{
                    if (detailMovie == null){
                        Toast.makeText(
                                this,
                                R.string.refresh_data,
                                Toast.LENGTH_LONG
                        ).show();
                    }else {
                        detailMoviePresenter.onDeleteFromFavoriteMovie(detailMovie, dataManager);
                    }
                }
                break;
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
        detailMoviePresenter.onLoadData(this, idMovie, dataManager);
    }

    private void doRefreshData() {
        progressDialog.show();
        detailMoviePresenter.onLoadData(this, idMovie, dataManager);
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
    public void loadData(DetailMovie detailMovie, boolean isFavoriteMovie){
        progressDialog.dismiss();
        tViewTitleDetailMovie.setText(detailMovie.getOriginalTitle());
        tViewOverviewDetailMovie.setText(detailMovie.getOverview());
        tViewValueReleaseDateMovie.setText(detailMovie.getReleaseDate());
        Glide.with(this)
                .load(BuildConfig.BASE_URL_IMAGE + "" +detailMovie.getPosterPath())
                .placeholder(R.drawable.ic_image_black_24dp)
                .error(R.drawable.ic_broken_image_black_24dp)
                .into(imgViewPosterDetailMovie);
        imgViewAddToFavoriteMovie.setBackgroundResource(
                isFavoriteMovie
                ? R.drawable.ic_star_black_24dp : R.drawable.ic_star_border_black_24dp
        );
        imgViewAddToFavoriteMovie.setTag(
                isFavoriteMovie
                ? "star full" : "star border"
        );
    }

    @Override
    public void loadDataFailed(){
        progressDialog.dismiss();
        Toast.makeText(this, "Load data failed", Toast.LENGTH_LONG)
                .show();
    }

    @Override
    public void addToFavoriteMovie(){
        imgViewAddToFavoriteMovie.setBackgroundResource(R.drawable.ic_star_black_24dp);
        imgViewAddToFavoriteMovie.setTag("star full");
    }

    @Override
    public void addToFavoriteMovieFailed(String message){
        AlertDialog alertDialogError = new AlertDialog.Builder(this)
                .setTitle("Error")
                .setMessage(message)
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int x) {
                        dialogInterface.dismiss();
                    }
                })
                .create();
        alertDialogError.show();
    }

    @Override
    public void deleteFromFavoriteMovie(){
        imgViewAddToFavoriteMovie.setBackgroundResource(R.drawable.ic_star_black_24dp);
        imgViewAddToFavoriteMovie.setTag("star border");
    }

    @Override
    public void deleteFromFavoriteMovieFailed(String message){
        AlertDialog alertDialogError = new AlertDialog.Builder(this)
                .setTitle("Error")
                .setMessage(message)
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int x) {
                        dialogInterface.dismiss();
                    }
                })
                .create();
        alertDialogError.show();
    }
}
