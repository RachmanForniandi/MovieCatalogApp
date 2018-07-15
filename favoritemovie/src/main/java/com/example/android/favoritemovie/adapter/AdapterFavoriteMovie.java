package com.example.android.favoritemovie.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.android.favoritemovie.BuildConfig;
import com.example.android.favoritemovie.FavoriteMovie;
import com.example.android.favoritemovie.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Lenovo on 11/6/2017.
 */

public class AdapterFavoriteMovie extends RecyclerView.Adapter<AdapterFavoriteMovie.ViewHolderFavoriteMovie>{

    private final String TAG = getClass().getSimpleName();
    private Context context;
    private List<FavoriteMovie>favoriteMovieList;
    private ListenerAdapterFavoriteMovie listenerAdapterFavoriteMovie;

    public AdapterFavoriteMovie(Context context, List<FavoriteMovie> favoriteMovieList,
                                ListenerAdapterFavoriteMovie listenerAdapterFavoriteMovie){
        this.context = context;
        this.favoriteMovieList = favoriteMovieList;
        this.listenerAdapterFavoriteMovie = listenerAdapterFavoriteMovie;
    }
    @Override
    public ViewHolderFavoriteMovie onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_favorite_movie, null);
        return new ViewHolderFavoriteMovie(view);
    }

    @Override
    public void onBindViewHolder(ViewHolderFavoriteMovie holder, int position) {
        FavoriteMovie favoriteMovie = favoriteMovieList.get(position);
        Log.d(TAG, "favoriteMovie: "+ favoriteMovie);
        Glide.with(context)
                .load(BuildConfig.BASE_URL_IMAGE + favoriteMovie.getmPosterPath())
                .placeholder(R.drawable.ic_image_black_24dp)
                .error(R.drawable.ic_broken_image_black_24dp)
                .into(holder.imgViewPosterItemFavoriteMovie);
        holder.txtViewTitleMovieFavoriteMovie.setText(favoriteMovie.getmTitle());
        holder.txtViewDescriptionFavoriteMovie.setText(favoriteMovie.getmOverview());
        holder.txtViewDateReleaseFavoriteMovie.setText(favoriteMovie.getmReleaseDate());
    }

    @Override
    public int getItemCount() {
        return favoriteMovieList.size();
    }

    class ViewHolderFavoriteMovie extends RecyclerView.ViewHolder{
        @BindView(R.id.img_view_poster_item_favorite_movie)
        ImageView imgViewPosterItemFavoriteMovie;
        @BindView(R.id.tv_title_item_movie_favorite)
        TextView txtViewTitleMovieFavoriteMovie;
        @BindView(R.id.tv_description_item_movie_favorite)
        TextView txtViewDescriptionFavoriteMovie;
        @BindView(R.id.tv_date_release_item_movie_favorite)
        TextView txtViewDateReleaseFavoriteMovie;

        public ViewHolderFavoriteMovie(View itemView){
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @OnClick({
                R.id.btn_detail_item_favorite_movie,
                R.id.btn_share_item_favorite_movie
        })
        public void onClick(View view){
            switch (view.getId()){
                case R.id.btn_detail_item_favorite_movie:
                //not yet
                    break;
                case R.id.btn_share_item_favorite_movie:
                    //not yet
                    break;
            }
        }
    }

    public interface ListenerAdapterFavoriteMovie{

        void onItemClickDetail(FavoriteMovie favoriteMovie);

        void onItemClickShare(FavoriteMovie favoriteMovie);
    }
}
